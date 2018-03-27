package com.huel.xgms.admin.org.service;

import com.huel.xgms.admin.org.bean.Dept;
import com.huel.xgms.admin.org.dao.DeptDao;
import com.huel.xgms.admin.org.dao.EmploeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.*;

@Repository
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptDao dao;
	
	@Autowired
	private EmploeeDao emplDao;
	
	private char[] CODE_BASE = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	
	@Override
	public List<Dept> tree() {
		List<Dept> result = new ArrayList<Dept>();
		
		List<Dept> deptList = dao.queryAllDepts(Dept.UPDATE_TYPE_NORMAL);
		Map<Integer, Dept> map = new HashMap<Integer, Dept>();
		
		for(Dept dept : deptList) {
			map.put(dept.getId(), dept);
			if(dept.getPid() == 0) {
				result.add(dept);
			}
		}
		
		Set<Integer> keySet = map.keySet();
		for(Integer id : keySet) {
			Dept dept = map.get(id);
			if(dept.getPid() == 0) {
				continue;
			}
			Dept parent = map.get(dept.getPid());
			if(parent == null) {
				continue;
			}
			List<Dept> children = parent.getChildren();
			if(children == null) {
				children = new ArrayList<Dept>();
				parent.setChildren(children);
			}
			children.add(dept);
		}
		
		return result;
	}

	@Override
	public boolean editSave(Dept dept) {
		dept.setUtime(System.currentTimeMillis());
		return this.dao.updateDept(dept);
	}

	@Override
	public int getPersonSizeOfDept(int deptId) {
		return this.emplDao.queryCountOfDept(deptId);
	}

	@Override
	public int getChildDeptSize(int deptId) {
		return this.dao.queryChildCount(deptId);
	}

	@Override
	public boolean delete(int deptId) {
		return this.dao.deleteDept(Dept.UPDATE_TYPE_DEL, System.currentTimeMillis(), deptId);
	}

	@Override
	public boolean add(Dept dept) {
		String code = null;
		
		List<Dept> pbCodes = this.dao.queryPcodeBcode(dept.getPid());
		String parentCode = "";
		List<String> brotherCodes = new ArrayList<String>();
		
		for(Dept codeDept : pbCodes) {
			if(codeDept.getId() == dept.getPid()) {
				parentCode = codeDept.getCode();
			} else {
				brotherCodes.add(codeDept.getCode());
			}
		}
		
		code = createCode(parentCode, brotherCodes);
		if(StringUtils.isEmpty(code)) {
			return false;
		}
		dept.setCode(code);
		dept.setUtime(System.currentTimeMillis());
		dept.setUtype(Dept.UPDATE_TYPE_NORMAL);
		this.dao.addDept(dept);
		return true;
	}
	
	private String createCode(String parentCode, List<String> brotherCodes) {
		if (brotherCodes.size() == 0) {
			return parentCode + CODE_BASE[0] + CODE_BASE[0];
		}
		if (brotherCodes.size() == CODE_BASE.length * CODE_BASE.length) {
			return null;
		}
		for (char code1 : CODE_BASE) {
			for (char code2 : CODE_BASE) {
				if(brotherCodes.contains(parentCode + code1 + code2)) {
					continue;
				} else {
					return parentCode + code1 + code2;
				}
			}
		}
		return null;
	}
}
