/**
 * 应用程序入口
 */

var app = angular.module('app', ['ui.router', 'oc.lazyLoad', 'ngDialog']);

//注册各服务
app.config(['$controllerProvider', '$compileProvider', '$filterProvider', '$provide', function ($controllerProvider, $compileProvider, $filterProvider, $provide) {
    app.add = {
        controller: $controllerProvider.register,
        directive: $compileProvider.directive,
        filter: $filterProvider.register,
        factory: $provide.factory,
        service: $provide.service
    };
}]);

//配置module常量
app.constant('modulesConfig', [
    {
        name: "ngTable",
        module: true,
        files: [
            "script/lib/ng-table/ng-table.min.css",
            "script/lib/ng-table/ng-table.min.js"
        ]
    },
    {
        name: "treeview",
        module: true,
        files: [
        	"script/lib/ivh-treeview/ivh-treeview.min.css",
            "script/lib/ivh-treeview/ivh-treeview-theme-basic.css",
            "script/lib/ivh-treeview/ivh-treeview.min.js"
        ]
    },
    {
        name: "placehoder",
        module: false,
        files: [
            'script/lib/placeholder/mdc-placehoder.js'
        ]
    },
    {
        name: "datepicker",
        module: false,
        files: [
            'script/lib/calendar/WdatePicker.js'
        ]
    },
    {
        name: "checklist",
        module: false,
        files: [
            'script/lib/checklist/checklist-model.js'
        ]
    },
    {
    	name: "treeControl",
        module: false,
        files: [
        	'script/lib/tree-control/tree-control.css',
            'script/lib/tree-control/angular-tree-control.js'
        ]
    }
]);

//配置延迟加载-----
app.config(['$ocLazyLoadProvider', '$locationProvider', 'modulesConfig', function ($ocLazyLoadProvider, $locationProvider, modulesConfig) {
    $ocLazyLoadProvider.config({
        debug: false,
        events: false,
        modules: modulesConfig
    });
}]);

//对http请求进行全局配置
app.config(function ($httpProvider, $stateProvider) {
    // 头信息配置,缺少X-Requested-With时解析错误
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
    $httpProvider.defaults.headers.post['Accept'] = 'application/json, text/javascript, */*; q=0.01';
    $httpProvider.defaults.headers.post['X-Requested-With'] = 'XMLHttpRequest';

    /**
     * 重写angular的param方法，使angular使用jquery一样的数据序列化方式  
     * The workhorse; converts an object to x-www-form-urlencoded serialization. 
     * @param {Object} obj
     * @return {String}
     */
    var param = function (obj) {
        var query = '', name, value, fullSubName, subName, subValue, innerObj, i;
        for (name in obj) {
            value = obj[name];
            if (value instanceof Array) {
                for (i = 0; i < value.length; ++i) {
                    subValue = value[i];
                    if (angular.isObject(subValue)) {
                        fullSubName = name + '[' + i + ']';
                    } else {
                        fullSubName = name;
                    }
                    innerObj = {};
                    innerObj[fullSubName] = subValue;
                    query += param(innerObj) + '&';
                }
            }
            else if (value instanceof Object) {
                for (subName in value) {
                    subValue = value[subName];
                    fullSubName = name + '.' + subName;
                    innerObj = {};
                    innerObj[fullSubName] = subValue;
                    query += param(innerObj) + '&';
                }
            }
            else if (value !== undefined && value !== null)
                query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
        }
        return query.length ? query.substr(0, query.length - 1) : query;
    };

    // 覆盖默认 transformRequest
    $httpProvider.defaults.transformRequest = [function (data) {
        return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
    }];

    //对所有请求统一拦截
    $httpProvider.interceptors.push(['$rootScope', '$q', function ($rootScope, $q) {
            return {
                'request': function (config) {
                    config.headers['X-Requested-With'] = 'XMLHttpRequest';
                    return config || $q.when(config);
                },
                'requestError': function (rejection) {
                    return rejection;
                },
                'response': function (response) {
                    return response || $q.when(response);
                },
                'responseError': function (response) {
                    if (response.status === 401) {
                    	$rootScope.$emit('responseError401', '401');
                        return false;
                    }
                    if (response.status === 403) {
                    	$rootScope.$emit('responseError403', '403');
                        return false;
                    }
                    if (response.status === 404) {
                    	$rootScope.$emit('responseError404', '404');
                    	return false;
                    }
                    if (response.status === 500) {
                    	console.log("内部服务器错误\r\n" + response.data);
                    	$rootScope.$emit('responseError500', '');
                        return false;
                    }
                    return $q.reject(response);
                }
            };
        }]);
});

//配置页面路由
app.config(['$urlRouterProvider', '$stateProvider', function ($urlRouterProvider, $stateProvider) {
	$urlRouterProvider.otherwise('/index');
    $stateProvider
        .state('sysrole', {
        	url: 'sysrole',
        	templateUrl: 'view/role.html',
        	controller : 'roleCtr',
        	resolve: {
        		deps: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                    	'treeview',
                        'script/controllers/role.js'
                    ]);
                }
        	}
        })
        .state('sysuser', {
        	url: 'sysuser',
        	templateUrl: 'view/user.html',
        	controller : 'userCtr',
        	resolve: {
        		deps: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                    	'ngTable','checklist',
                        'script/controllers/user.js'
                    ]);
                }
        	}
        })
        .state('syslog', {
        	url: 'syslog',
        	templateUrl: 'view/syslog.html',
        	controller : 'syslogCtr',
        	resolve: {
        		deps: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                    	'ngTable',
                        'script/controllers/syslog.js'
                    ]);
                }
        	}
        })
        .state('orgdept', { //主界面
            url: 'orgdept',
            templateUrl: 'view/dept.html',
            controller: 'deptCtr',
            resolve: {
                deps: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                    	'treeControl',
                        'script/controllers/dept.js'
                    ]);
                }
            }
        })
        .state('orgemp', { //主界面
            url: 'orgemp',
            templateUrl: 'view/empl.html',
            controller: 'emplCtr',
            resolve: {
                deps: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                    	'ngTable',
                        'script/controllers/empl.js'
                    ]);
                }
            }
        })
        .state('appup', { //主界面
            url: 'appup',
            templateUrl: 'view/appup.html',
            controller: 'appupCtr',
            resolve: {
                deps: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
						'ngTable',                    
                        'script/controllers/appup.js'
                    ]);
                }
            }
        })
        .state('404', { //主界面
            url: '404',
            template: '<div class="page404"></div>'//TODO 此处设置404页面的图片样式
        });
}]);

