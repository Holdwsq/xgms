/**
 * ie9及一下浏览器支持placeholder属性
 * 用法 <input type="text" placeholder="搜索管理员..." mdc-placeholder >
 * Created by mdc on 2016/6/12.
 */
app.add.directive('mdcPlaceholder', ['$compile', function ($compile) {

    var isSupportPlaceholder = 'placeholder' in document.createElement('input');

    return {
        restrict: 'A',
        scope: {},
        link: function (scope, ele, attr) {
            if (isSupportPlaceholder) {
                return;
            }

            (typeof $ != 'undefined') && (ele = $(ele));

            var fakePlaceholder = angular.element('<div class="plcaeholder">' + attr['placeholder'] + '</div>');
            fakePlaceholder.attr('onselectstart', 'return false');

            var hideOnFocus = !!attr['hideonfocus'];

            var hide = function () {
                fakePlaceholder.hide? fakePlaceholder.hide() : fakePlaceholder.css('display', 'none');
            };

            var is_empty = function () {
                return !ele.val();
            };

            var check = function () {
                is_empty() ? fakePlaceholder.show? fakePlaceholder.show() : fakePlaceholder.css('display', '') : hide();
            };

            fakePlaceholder.on('click', function (e) {
                e.stopPropagation();
                hideOnFocus && hide();
                ele[0].focus();
            });

            //使用元素本身样式
            angular.forEach(['marginTop', 'marginLeft', 'paddingTop', 'paddingLeft', 'paddingRight'], function (name) {
                fakePlaceholder.css(name, ele.css(name));
            });

            //默认样式
            var layer_style = {
                height:'auto',
                color: '#A3A3A3',
                cursor: 'text',
                textAlign: 'left',
                position: 'absolute',
                'z-index':2,
                whiteSpace: 'nowrap',
                cursor: 'text',
                width:ele.width? ele.width(): ele[0].offsetWidth + 'px',
                display: is_empty() ? 'block' : 'none',
                '-moz-user-select':'none',
                '-khtml-user-select':'none',
                '-webkit-user-select':'none'
            };

            fakePlaceholder.css(layer_style);

            //重新定位
            var position = function() {
                var pos = ele.position? ele.position() : {left:ele[0].offsetLeft, top:ele[0].offsetTop};
                fakePlaceholder.css(pos);
                 (!window.onresize && (window.onresize = position));
            };

            ele.after(fakePlaceholder);

            $compile(fakePlaceholder)(scope);

            if (hideOnFocus) {
                ele.on('focus', hide);
            } else {
                ele.on('keypress keydown', function (e) {
                    var key = e.keyCode;
                    if (e.charCode || (key >= 65 && key <= 90)) {
                        hide();
                    }
                }).on('keyup', check);
            }

            ele.on('blur', check);
            ele[0].onpropertychange = check;
            check();
            position();
        }
    };
}]);