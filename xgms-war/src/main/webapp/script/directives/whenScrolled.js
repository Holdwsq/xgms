/**
 * scroll滚动加载数据
 * feiyong
 * 2016/6/22.
 */
app.add.directive('whenScrolled', function ($timeout) {
    return {
        restrict: 'A',
        link:function(scope, elm, attr) {
            var raw = elm[0];
            elm.bind('scroll', function() {
                if (raw.scrollTop+raw.offsetHeight >= raw.scrollHeight) {
                    scope.$apply(attr.whenScrolled);
                }
            });
        }
    }
})
