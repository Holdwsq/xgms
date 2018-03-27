app.add.controller('userCtr', function($scope, $http, ngTableParams, ngDialog) {
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
	    	$http.post('admin/sysuser/list', search).success(function(data){
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
    
    $scope.add = function() {
    	ngDialog.open({
			template : 'addDialog',
            controller: 'userAddCtr',
            className: 'ngdialog-theme-default',
            scope:$scope
        });
    }
    
    $scope.edit = function(user){
    	$scope.editUser = angular.copy(user);
    	ngDialog.open({
			template : 'editDialog',
            controller: 'userEditCtr',
            className: 'ngdialog-theme-default',
            scope:$scope
        });
    }
    
    $scope.resetPwd = function(user) {
    	var reset = confirm("确认重置管理员【" + user.name + "】的密码？");
    	if(reset) {
    		$http.get("admin/sysuser/reset/" + user.id).success(function(data){
				alert("重置成功");
			});
    	}
    }
    
    $scope.disable = function(user) {
    	var disable = confirm("确认停用管理员【" + user.name + "】的账户？")
    	if(disable) {
    		$http.get("admin/sysuser/disable/" + user.id).success(function(data){
				$scope.refreshTable();
			});
    	}
    }
    
    $scope.enable = function(user) {
    	var enable = confirm("确认启用管理员【" + user.name + "】的账户？")
    	if(enable) {
    		$http.get("admin/sysuser/enable/" + user.id).success(function(data){
				$scope.refreshTable();
			});
    	}
    }
});

app.add.controller('userAddCtr', function ($scope, $http, ngDialog) {
	$scope.roles = [];
	$scope.user = {};
	$http.get("admin/sysuser/roles").success(function(data){
		$scope.roles = data;
	})
	
	$scope.closeDialog = function(){
		ngDialog.close();
	}
	
	$scope.saveUser = function(isValid) {
		if($scope.user.roles.length == 0) {
			alert("请勾选角色");
			return;
		}
		$http.post('admin/sysuser/addsave', $scope.user).success(function(data){		
			if(data.flag == 1) {
				ngDialog.close();
				$scope.refreshTable();
			} else {
				alert(data.result);
			}
			
		});
	}
});

app.add.controller('userEditCtr', function ($scope, $http, ngDialog) {
	$scope.roles = [];
	
	$http.get("admin/sysuser/roles").success(function(data){
		$scope.roles = data;
		$http.get("admin/sysuser/userrole/" + $scope.editUser.id).success(function(data){
			$scope.editUser.roles = data;
		})
	})
	
	$scope.saveUser = function(isValid) {
		if($scope.editUser.roles.length == 0) {
			alert("请勾选角色");
			return;
		}
		$http.post('admin/sysuser/editsave', $scope.editUser).success(function(data){		
			ngDialog.close();
			$scope.refreshTable();
		});
	}

	$scope.closeDialog = function(){
		ngDialog.close();
	}
});