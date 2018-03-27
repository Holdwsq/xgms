app.add.controller('emplCtr', function($scope, $http, ngTableParams) {
	$scope.totalCount = 0;
	
    $scope.tableParams = new ngTableParams({
	    page: 1,
	    count: 15
    },{
    	counts: [/*5,10,20*/], //����ÿҳ��ʾ��С
        paginationMaxBlocks: 5, //�����ʾҳ�밴ť����
        paginationMinBlocks: 2,//������ʾҳ�밴ť����
    	total: 0,
	    getData: function($defer, params) {
	    	var page = params.page();
            var size = params.count();
            var search = {};
            search.pageNum = page;
            search.pageSize = size;
	    	$http.post('admin/orgemp/list', search).success(function(data){
	        	$scope.totalCount = data.total;
	        	params.total(data.total);
	        	$defer.resolve(data.list);
	        });
	    }
    });
    
    $scope.refreshTable = function(){
		$scope.tableParams.page(1);
        $scope.tableParams.reload();
    };
});