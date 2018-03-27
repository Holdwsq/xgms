package com.huel.xgms.admin.system.service;
import com.huel.xgms.admin.system.bean.Function;
import com.huel.xgms.admin.system.bean.User;
import com.huel.xgms.page.Pagination;

import java.util.List;

public interface UserService {

	User queryByAccountAndPwd(String account, String password);
	
	List<Function> queryFuncs(int userId);
	
	boolean isPwdEquals(int userId, String pwd);
	
	void changePwd(int userId, String pwd);
	
	Pagination queryUsers(int pageNum, int PageSize);
	
	boolean resetPwd(int userId, String pwd);
	
	boolean disable(int userId);
	
	boolean enable(int userId);
	
	boolean add(User user); 
	
	List<Integer> queryUserRoles(int userId);

	void edit(User user);
}
