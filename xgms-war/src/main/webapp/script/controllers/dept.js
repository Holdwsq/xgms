app.add.controller('deptCtr', function($scope, $http) {

	$scope.selectedDept = undefined;
	$scope.editDept = undefined;
	
	//清除树的选中状态
	$scope.clearSelected = function() {
        $scope.selectedDept = undefined;
    }
	//加载部门树
	$scope.loadDepts = function() {
		$http.get("admin/orgdept/tree").success(function(data){
			$scope.depts = [];
			$scope.depts = data;
		});		
	}
	
	$scope.loadDepts();
	
	//选中树节点事件
	$scope.showSelected = function(sel) {
		$scope.editDept = angular.copy(sel);
        $scope.cancelAdd();
    };
	
	//添加一级部门
    $scope.add = function() {
 		$scope.clearSelected();
 		$scope.addStatus = true;
 	}
 	
 	//添加子部门
 	$scope.addChild = function() {
 		$scope.addStatus = true;
 	}
 	
 	//编辑保存
 	$scope.saveDept = function(valid) {
 		if(!valid) {
 			alert("请认真填写表单信息");
 			return;
 		}
 		
 		$http.post('admin/orgdept/editsave', $scope.editDept).success(function(data){
 			$scope.editDept = undefined;
 			$scope.selectedDept = undefined;
 			$scope.loadDepts();
			alert("保存成功");
		});
 	}
 	
 	$scope.del = function(){
 		if($scope.selectedDept.children && $scope.selectedDept.children.length > 0) {
 			alert("部门存在子部门，请先删除子部门");
 		} else {
			var del = confirm("确认删除部门【" + $scope.selectedDept.name + "】？");
			if(del) {
				$http.get("admin/orgdept/del/" + $scope.selectedDept.id).success(function(data){
					if(data.flag == 1) {
						$scope.editDept = undefined;
 						$scope.selectedDept = undefined;
						$scope.loadDepts();
					} else {
						alert(data.result);
					}
				});
			}		 			
 		}
 	}
 	
 	//取消添加
 	$scope.cancelAdd = function(){
 		$scope.addStatus = false;
 		$scope.addDept = undefined;
 	}
 	
 	//添加保存
 	$scope.addSave = function(valid) {
 		if(!valid) {
 			alert("请认真填写表单信息");
 			return;
 		}
 		if($scope.selectedDept != undefined) {
 			$scope.addDept.pid = $scope.selectedDept.id;
 		}
 		
 		$http.post('admin/orgdept/addsave', $scope.addDept).success(function(data){
 			$scope.addDept = undefined;
 			$scope.loadDepts();
			alert("保存成功");
		});
 	}
});