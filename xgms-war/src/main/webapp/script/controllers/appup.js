app.add.controller('appupCtr', function($scope, $http, ngTableParams, ngDialog) {
	$scope.totalCount = 0;
	
    $scope.tableParams = new ngTableParams({
	    page: 1,
	    count: 15
    },{
    	counts: [/*5,10,20*/], //控制每页显示大小
        paginationMaxBlocks: 5, //最多显示页码按钮个数
        paginationMinBlocks: 2,//最少显示页码按钮个数
    	total: 0,
	    getData: function($defer, params) {
	    	var page = params.page();
            var size = params.count();
            var search = {};
			search.pageNum = page;
            search.pageSize = size;
	    	$http.post('admin/appup/list', search).success(function(data){
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
    
    $scope.del = function(app) {
    	var del = confirm("删除后，客户端将无法检测到此版本，确认删除？");
    	if(del) {
    		$http.get("admin/appup/del/" + app.id).success(function(data){
				$scope.refreshTable();
			})
    	}
    };
    
    $scope.add = function() {
    	ngDialog.open({
			template : 'addDialog',
            controller: 'appAddCtr',
            className: 'ngdialog-theme-default',
            scope:$scope
        });
    }
});

app.add.controller('appAddCtr', function ($scope, $http, ngDialog) {
	$scope.closeDialog = function(){
		ngDialog.close();
	}
	
	$scope.app = {};
	
	$scope.saveApp = function(valid) {
		var fd = new FormData();
        var file = document.querySelector('input[type=file]').files[0];
        fd.append('apkfile', file); 
        fd.append('versionCode', $scope.app.versionCode);
        fd.append('versionName', $scope.app.versionName);
        fd.append('description', $scope.app.description);
        
        $http({
              method:'post',
              url:"admin/appup/add",
              data: fd,
              headers: {
              	'Content-Type':undefined
              },
              transformRequest: angular.identity 
		}).success( function ( response ){
			//上传成功的操作
			$scope.refreshTable();
			ngDialog.close();
        }); 
	}
});