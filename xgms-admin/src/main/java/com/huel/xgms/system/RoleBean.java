package com.huel.xgms.system;

import java.util.List;

public class RoleBean {

	/**
	 * 主键
	 */
	private int id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 权限
	 */
	private List<Integer> funcs;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getFuncs() {
		return funcs;
	}

	public void setFuncs(List<Integer> funcs) {
		this.funcs = funcs;
	}
}
