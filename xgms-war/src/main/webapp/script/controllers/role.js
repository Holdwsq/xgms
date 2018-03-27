app.add.controller('roleCtr', function($scope, $http, ngDialog) {
	$scope.roles = [];//role列表
	$scope.role = {};//等待编辑的role
	
	$scope.loadRoles = function() {
		$http.get("admin/sysrole/list").success(function(data){		
			$scope.roles = data;
		});
	}
	
	$scope.loadRoles();
	
    $scope.openAdd = function() {
    	$scope.role = {};//每次打开，都要清空
    	ngDialog.open({
			template : 'roleDialog',
            controller: 'roleAddCtr',
            className: 'ngdialog-theme-default',
            scope:$scope
        });
    }
    
    $scope.editRole = function(role){
    	$scope.role = angular.copy(role);
    	ngDialog.open({
			template : 'roleDialog',
            controller: 'roleEditCtr',
            className: 'ngdialog-theme-default',
            scope:$scope
        });
    }
    
    $scope.delRole = function(role){
    	var del = confirm("删除角色将导致人员失去该角色的权限，确认删除？")
    	if(del) {
    		$http.get("admin/sysrole/del/" + role.id).success(function(data){
				$scope.loadRoles();
			})
    	}
    }
    
});

app.add.controller('roleAddCtr', function ($scope, $http, ngDialog) {
	$scope.funcs = [];
	
	$http.get("admin/sysrole/funcs").success(function(data){
		$scope.funcs = data;
	})
	
	$scope.closeRoleDialog = function() {
		ngDialog.close();
	}
	
	$scope.saveRole = function(isValid) {
		if ($scope.role.name === "") {//TODO 表单验证需要继续研究
    		alert("角色名称不能为空");
	        return;
        }
        var selectedFuncs = new Array();
        for(var i = 0; i < $scope.funcs.length; i++) {
        	var pf = $scope.funcs[i];
        	if(pf.isSelected) {
        		selectedFuncs.push(pf.id);
        	}
        	if(pf.child) {
        		for(var j = 0; j < pf.child.length; j++) {
        			var cf = pf.child[j];
        			if(cf.isSelected) {
        				selectedFuncs.push(cf.id);
        			}
        		}
        	}
        }
        if(selectedFuncs.length == 0) {
        	alert("请勾选权限");
        	return;
        }
        $scope.role.funcs = selectedFuncs;
        
        $http({
			url:'admin/sysrole/addsave',
			method:'post',
			data:$scope.role
		}).success(function(data){		
			ngDialog.close();
			$scope.loadRoles();
		});
	}
});

app.add.controller('roleEditCtr', function ($scope, $http, ngDialog) {
	$scope.funcs = [];
	
	$http.get("admin/sysrole/funcs").success(function(data){
		$scope.funcs = data;
		
		//此处需要获取用户的权限列表，设置到树中
		$http.get("admin/sysrole/rolefunc/" + $scope.role.id).success(function(data){
			for(var i = 0; i < $scope.funcs.length; i++) {
				var pf = $scope.funcs[i];
				if(data.indexOf(pf.id) != -1) {
					pf.isSelected = true;
				}
				if(pf.child) {
	        		for(var j = 0; j < pf.child.length; j++) {
	        			var cf = pf.child[j];
	        			if(data.indexOf(cf.id) != -1) {
							cf.isSelected = true;
						}
	        		}
				}
			}
		});
	});
	
	$scope.closeRoleDialog = function() {
		ngDialog.close();
	}
	
	$scope.saveRole = function(isValid) {
		if ($scope.role.name === "") {//TODO 表单验证需要继续研究
    		alert("角色名称不能为空");
	        return;
        }
        var selectedFuncs = new Array();
        for(var i = 0; i < $scope.funcs.length; i++) {
        	var pf = $scope.funcs[i];
        	if(pf.isSelected) {
        		selectedFuncs.push(pf.id);
        	}
        	if(pf.child) {
        		for(var j = 0; j < pf.child.length; j++) {
        			var cf = pf.child[j];
        			if(cf.isSelected) {
        				selectedFuncs.push(cf.id);
        			}
        		}
        	}
        }
        if(selectedFuncs.length == 0) {
        	alert("请勾选权限");
        	return;
        }
        $scope.role.funcs = selectedFuncs;
        
        $http({
			url:'admin/sysrole/editsave',
			method:'post',
			data:$scope.role
		}).success(function(data){		
			ngDialog.close();
			$scope.loadRoles();
		});
	}
});