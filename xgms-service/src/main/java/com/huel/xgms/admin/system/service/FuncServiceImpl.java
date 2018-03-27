package com.huel.xgms.admin.system.service;


import com.huel.xgms.admin.system.bean.Function;
import com.huel.xgms.admin.system.dao.FunctionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FuncServiceImpl implements FuncService{

	@Autowired
	private FunctionDao dao;
	
	@Override
	public List<Function> queryAll() {
		List<Function> funcs = dao.queryAll();
		Map<Integer, Function> funcMap = new HashMap<>();
		List<Function> result = new ArrayList<>();
		for(Function func : funcs) {
			Function parent = funcMap.get(func.getPid());
			if(parent == null) {
				funcMap.put(func.getId(), func);
				result.add(func);
			} else {
				List<Function> children = parent.getChild();
				if(children == null) {
					children = new ArrayList<>();
					parent.setChild(children);
				}
				children.add(func);
			}
		}
		return result;
	}

}
