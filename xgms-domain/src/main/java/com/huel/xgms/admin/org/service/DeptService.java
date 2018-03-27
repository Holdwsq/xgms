package com.huel.xgms.admin.org.service;


import com.huel.xgms.admin.org.bean.Dept;

import java.util.List;

/**
 * 部门服务接口
 * @author wsq
 * @date 2018/3/27
 */
public interface DeptService {

	List<Dept> tree();
	
	boolean editSave(Dept dept);
	
	int getPersonSizeOfDept(int deptId);
	
	int getChildDeptSize(int deptId);
	
	boolean delete(int deptId);
	
	boolean add(Dept dept);
}
