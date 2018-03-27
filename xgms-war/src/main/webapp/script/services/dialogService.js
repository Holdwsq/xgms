/**
 * 基本的对话框组件服务
 * Created by mdc on 2016/6/17.
 */
angular.module('uidialog', [])
    .factory('dialog', ['$modal', '$http', function ($modal, $http) {

        var template = '<div class="modal-header">\
                <p class="modal-title" ng-bind="mdcOpt.title"></p>\
                <button ng-show="isShow(mdcOpt.close.show)" ng-disabled="getDisabled(mdcOpt.close)" class="close" ng-class="mdcOpt.close.className" type="button" ng-click="btnClick(-1, mdcOpt.close)" >&times;</button>\
            </div>\
            <div class="modal-body" style="overflow: hidden;position: relative;">\
                <div class="dialogNormal" style="height:{{mdcOpt.height}};width:{{mdcOpt.width}};">\
                    <div class="faceLogo" ng-if="mdcOpt.icon" >\
                    <img ng-src="{{\'../themes/default/images/\' + mdcOpt.icon+\'.png\'}}" />\
                    </div>\
                    <div class="dialogContent {{mdcOpt.contentClass}}" as-html="mdcOpt.content" style="{{(!mdcOpt.icon) && \'float:none\'}}"></div>\
                </div>\
            </div>\
            <div class="modal-footer" ng-if="mdcOpt.buttons && mdcOpt.buttons.length">\
                <button ng-repeat="btn in mdcOpt.buttons track by $index" ng-show="isShow(btn.show)" ng-disabled="getDisabled(btn)" class="btn" ng-class="btn.className" type="button" ng-click="btnClick($index, btn)" ng-bind="btn.name" ></button>\
            </div>';


        return {
            //简单的对话框组件
            openDialog: function (options) {
                var opt = angular.extend({}, {
                    icon: '',
                    close: {}, //关闭按钮, 数据结构和button一致
                    width: 'auto',
                    height: 'auto',
                    title: '提示',
                    content: '',
                    contentClass: '',
                    keyboard: true, //启用ESC键关闭对话框
                    parent: {},
                    buttons: [], //数据结构
                    /* {
                     * name:'取消', //显示文本
                     * disabled:false //禁用状态
                     * show:true, //是否显示
                     * className:'', //类名
                     * callback:function($scope, $modalInstance, btn, mdcOpt) //回调函数
                     }
                     */
                    template: template,
                    controller: 'modalInstanceCtrl',
                    resolve: {}
                }, options);

                var userData = {};
                angular.extend(userData, opt.resolve);
                delete opt.resolve;

                opt.resolve = {
                    userData: function () {
                        return userData;
                    },
                    mdcOpt: function () {
                        return opt;
                    }
                };

                if (opt.url) {
                    $http.get(opt.url).success(function (data) {
                        opt.content = data;
                        return $modal.open(opt);
                    });
                    return;
                }
                return $modal.open(opt);
            },

            /**
             * 确认对话框
             * @param options
             */
            confirm: function (options) {

                var opt = angular.extend({}, {
                    title: '确认',
                    icon: 'smileFace',
                    contentClass: "w350",
                    cancelVal: '取消',
                    okVal: '确定'
                }, options);

                opt.buttons = [
                    {
                        name: opt.cancelVal,
                        className: 'btn-gray',
                        callback: opt.cancel
                    },
                    {
                        name: opt.okVal,
                        className: 'btn-green',
                        callback: opt.ok
                    }
                ];

                return this.openDialog(opt);
            },

            /**
             * 警告对话框
             * @param options
             * @returns {*}
             */
            alert: function (options) {
                var opt = angular.extend({}, {
                    title: '提示',
                    icon: 'smileFace',
                    contentClass: "w350",
                    okVal: '确定'
                }, options);

                opt.buttons = [
                    {
                        name: opt.okVal,
                        className: 'btn-green',
                        callback: opt.ok
                    }
                ];

                return this.openDialog(opt);
            }
        }
    }]);


angular.module('uidialog').controller('modalInstanceCtrl', uidialogCtr);

function uidialogCtr($scope, $modalInstance, $modal, $q, userData, mdcOpt) {
    $scope.mdcOpt = mdcOpt;

    $scope.getDisabled = function (btn) {
        if (typeof btn.disabled == 'function') {
            return btn.disabled.call($scope, $scope, $q, $modalInstance, mdcOpt);
        }

        if (typeof btn.disabled == 'string') {
            return $scope.$eval(btn.disabled);
        }

        return btn.disabled;
    };

    var fn, result;
    for (var it in userData) {
        fn = userData[it];

        if (typeof fn != 'function') {
            $scope[it] = userData[it];
            continue;
        }

        result = fn.call($scope, $scope, $q, $modalInstance, mdcOpt);

        if (typeof result == 'undefined') {
            $scope[it] = fn;
            continue ;
        }

        if (result.then) {
            (function (result, key) {
                result.then(function (data) {
                    $scope[key] = data;
                });
            })(result, it);
            continue;
        }

        $scope[it] = result;
    }

    /**
     * 点击按钮
     * @param index 按钮的索引
     */
    $scope.btnClick = function (index, btn) {
        var ret = (btn.callback || angular.noop).call(btn, $scope, $modalInstance, btn, mdcOpt);

        if (typeof ret == 'undefined' || ret) {
            $modalInstance.close(index);
        }
    };

    //是否显示
    $scope.isShow = function (show) {
        return typeof show == 'undefined' ? true : show;
    }
}

uidialogCtr.$inject = ['$scope', '$modalInstance', '$modal', '$q', 'userData', 'mdcOpt'];

/*把html编译双向绑定*/
angular.module('uidialog').directive('asHtml', ['$compile', function ($compile) {
    return {
        restrict: 'A',
        link: function ($scope, element, attrs) {

            $scope.$watch(function () {
                return $scope.$eval(attrs.asHtml);
            }, function (newv, oldv) {
                if (newv) {
                    element.html(newv);
                    $compile(element.contents())($scope);
                }
            });
        }
    }
}]);