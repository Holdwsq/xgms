package com.huel.xgms.index;


import com.huel.xgms.admin.system.bean.Function;

import java.util.List;

/**
 * @author wsq
 */
public class IndexBean {

	private String name;
	
	private List<Function> funcs;

	public IndexBean() {
	}

	public IndexBean(String name, List<Function> funcs) {
		this.name = name;
		this.funcs = funcs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Function> getFuncs() {
		return funcs;
	}

	public void setFuncs(List<Function> funcs) {
		this.funcs = funcs;
	}
	
}
