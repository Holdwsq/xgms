/**
 * 页面的根节点处理器
 */
app.controller('indexCtr', function ($rootScope, $scope, $state, $http, ngDialog) {
	var token = window.sessionStorage.getItem("token");
	if(token) {
		$http.defaults.headers.common.Authorization = 'Bearer ' + token;
	} else {
		window.location.href="login.html";
		return;
	}
    
	$scope.menus = [];
    $scope.childMenus = [];
    
    //获取登陆后的数据
    $http({
    	url:'admin/anony/index',
    	method:'get'
    }).success(function(data){
    	if(data.flag == 1) {
			$scope.username = data.result.name;	
    		$scope.menus = data.result.funcs;    		
    	}
    });
    
    //点击一级菜单
    $scope.clickMenu = function(item) {
    	//搞定本身的状态
    	for(var i = 0; i < $scope.menus.length; i++) {
    		if($scope.menus[i].id == item.id) {
    			$scope.menus[i].isActive = true;    			
    		} else {
    			$scope.menus[i].isActive = false;
    		}
    	}
    	
    	//清除前一个子菜单数组的状态
		for(var i = 0; i < $scope.childMenus.length; i++) {
			$scope.childMenus[i].isActive = false;
		}
		
    	//搞定前一个菜单的子菜单的状态
    	if(item.child) {
    		//将本身的子菜单添加进去
    		$scope.childMenus = item.child;
    		//第一个子菜单选中
    		$scope.childMenus[0].isActive = true;
    		$state.go($scope.childMenus[0].url);
    	} else {
    		$scope.childMenus = [];
    		$state.go(item.url);
    	}
    };
    
    //点击二级菜单
    $scope.clickChildMenu = function(item) {
    	for(var i = 0; i < $scope.childMenus.length; i++) {
    		if($scope.childMenus[i].id == item.id) {
    			$scope.childMenus[i].isActive = true;    			
    		} else {
    			$scope.childMenus[i].isActive = false;
    		}
    	}
    	$state.go(item.url);
    }
    
    //退出系统
    $scope.logout = function() {
    	$http.defaults.headers.common.Authorization = '';
		window.sessionStorage.clear();
    	window.location.href="login.html";
    }
    
    //打开修改密码对话框
    $scope.openPwdDialog = function() {
    	ngDialog.open({
            template: 'pwdDialog',
            controller: 'pwdCtr',
            className: 'ngdialog-theme-default'
        });
    }
    
    //对全局的http请求状态进行监听，401,403,404,500分别处理
    $rootScope.$on('responseError401', function(event){
    	$scope.logout();
	});
	$rootScope.$on('responseError403', function(event){
		if(confirm('您当前账号无操作此功能的权限，重新登录？')) {
			$scope.logout();
		}
	});
	$rootScope.$on('responseError404', function(event){
		$state.go("404");
	});
	$rootScope.$on('responseError500', function(event){
		alert('系统出现错误，请稍后再试。');
	});
});

app.controller('pwdCtr', function ($scope, $http, ngDialog) {
    //关闭修改密码对话框
    $scope.closePwdDialog = function(){
    	ngDialog.close();
    }
    
    $scope.pwd = {};
    //修改密码
    $scope.changePwd = function(isValid) {
    	if (!isValid) {
    		alert("表单验证失败，请填写正确格式。");
	        return;
        }
    	if($scope.pwd.oldPwd == $scope.pwd.newPwd) {
    		alert("新密码不能与原密码相同");
    		return;
    	}
    	if($scope.pwd.newPwd != $scope.pwd.confirmPwd) {
    		alert("确认密码必须与新密码相同");
    		return;
    	}
    	$http.post('admin/anony/changePwd', $scope.pwd).success(function(data){
	    	if(data.flag == 1) {
	    		$scope.pwd = {};
	    		//修改成功后，关闭弹出框 
				ngDialog.close();
				alert("修改成功");
	    	} else {
	    		alert(data.result);
	    	}
	    });
    }
});