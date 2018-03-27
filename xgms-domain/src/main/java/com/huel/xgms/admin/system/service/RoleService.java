package com.huel.xgms.admin.system.service;


import com.huel.xgms.admin.system.bean.Role;

import java.util.List;

public interface RoleService {
	
	Role get(int id);

	void add(Role role, List<Integer> funcs);
	
	void update(Role role, List<Integer> funcs);
	
	void del(int id);
	
	void update(Role role);
	
	List<Role> list();
	
	List<Integer> getRoleFunc(int id);
}
