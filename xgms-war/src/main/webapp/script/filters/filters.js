/**
 * 格式化字符串,例如:
 * 页面使用: {{"欢迎{name}给{content}留言"|format:{name:"马德成", content:"测试人员"}:'--'}}
 * controller使用: $filter('format')("欢迎{name}给{content}留言", {name:"马德成", content:"测试人员"}, '--');
 *
 */
angular.module('app.filter', [])
    .filter('format', function () {
        return function (src, data, defValue) {
            if (arguments.length == 0) return null;

            data = data || {};
            defValue = defValue || '';
            if (!src) return;

            return src.replace(/\{([\w\.]+)\}/g, function (m, ret) {
                if (ret.indexOf('.') != -1) {
                    var list = ret.split('\.'), dat = data;
                    for (var i = 0; i < list.length; i++) dat = dat[list[i]];
                    return dat || defValue;
                }

                return data[ret] || defValue;
            });
            
        };
    });
