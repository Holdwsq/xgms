package com.huel.xgms.admin.system.service;


import com.huel.xgms.admin.system.bean.Function;
import com.huel.xgms.admin.system.bean.User;
import com.huel.xgms.admin.system.bean.UserRole;
import com.huel.xgms.admin.system.dao.FunctionDao;
import com.huel.xgms.admin.system.dao.UserDao;
import com.huel.xgms.page.Pagination;
import org.jfaster.mango.plugin.page.Page;
import org.jfaster.mango.transaction.TransactionAction;
import org.jfaster.mango.transaction.TransactionStatus;
import org.jfaster.mango.transaction.TransactionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private FunctionDao funcDao;
	
	@Override
	public User queryByAccountAndPwd(String account, String password) {
		User user = userDao.queryByAccountAndPwd(account, password);
		if(user != null) {
			//查询当前用户有权限的菜单
			List<String> permissions = funcDao.queryPermissionByUserId(user.getId());
			user.setPermissions(permissions);
		}
		return user;
	}

	@Override
	public List<Function> queryFuncs(int userId) {
		//准备第一级菜单
		List<Function> roots = funcDao.queryFuncsByPid(0);
		Map<Integer, Function> rootMap = new HashMap<Integer, Function>();
		for(Function root : roots) {
			rootMap.put(root.getId(), root);
		}
		//查询用户的菜单
		List<Function> funcs = funcDao.queryFuncsByUserId(userId);
		
		Map<Integer, Function> funcMap = new HashMap<Integer, Function>();
		List<Function> result = new ArrayList<Function>();
		for(Function func : funcs) {
			if(func.getPid() == 0) {
				funcMap.put(func.getId(), func);
				result.add(func);
			} else {
				Function parent = funcMap.get(func.getPid());
				if(parent == null) {
					parent = rootMap.get(func.getPid());
					funcMap.put(func.getPid(), parent);
					result.add(parent);
				}
				List<Function> children = parent.getChild();
				if(children == null) {
					children = new ArrayList<Function>();
					parent.setChild(children);
				}
				children.add(func);
			}
		}
		return result;
	}

	@Override
	public boolean isPwdEquals(int userId, String pwd) {
		User user = this.userDao.getUser(userId);
		if(user == null) {
			return false;
		}
		if(user.getPwd().equals(pwd)) {
			return true;
		}
		return false;
	}

	@Override
	public void changePwd(int userId, String pwd) {
		this.userDao.changePwd(userId, pwd);
	}

	@Override
	public Pagination queryUsers(int pageNum, int pageSize) {
		Page page = Page.create(pageNum, pageSize);
		
		Pagination pagination = new Pagination();
		pagination.setList(this.userDao.query(page));
		pagination.setTotal(page.getTotal());
		return pagination;
	}
	
	@Override
	public boolean resetPwd(int userId, String pwd) {
		return this.userDao.updatePwd(userId, pwd);
	}
	
	@Override
	public boolean disable(int userId) {
		return this.userDao.updateStatus(userId, User.STATUS_DISABLE);
	}
	
	@Override
	public boolean enable(int userId) {
		return this.userDao.updateStatus(userId, User.STATUS_ENABLE);
	}
	
	@Override
	public boolean add(final User user) {
		int exists = this.userDao.accountExist(user.getAccount());
		if(exists > 0) {
			return false;
		}
		TransactionTemplate.execute(new TransactionAction() {
			@Override
			public void doInTransaction(TransactionStatus status) {
				int userId = userDao.addUser(user);
				List<UserRole> list = new ArrayList<UserRole>();
				for(Integer roleId : user.getRoles()) {
					UserRole ur = new UserRole(userId, roleId);
					list.add(ur);
				}
				userDao.saveUserRole(list);
			}
		});
		return true;
	}

	@Override
	public List<Integer> queryUserRoles(int userId) {
		return this.userDao.queryUserRoles(userId);
	}

	@Override
	public void edit(final User user) {
		TransactionTemplate.execute(new TransactionAction() {
			@Override
			public void doInTransaction(TransactionStatus status) {
				userDao.updateUser(user);
				userDao.clearUserRoles(user.getId());
				List<UserRole> list = new ArrayList<UserRole>();
				for(Integer roleId : user.getRoles()) {
					UserRole ur = new UserRole(user.getId(), roleId);
					list.add(ur);
				}
				userDao.saveUserRole(list);
			}
		});
	}
}
