package com.huel.xgms.admin.system.bean;

import java.util.List;

public class Function {

	/**
	 * 主键
	 */
	private int id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 图标
	 */
	private String icon;
	
	/**
	 * 父ID
	 */
	private int pid;
	
	/**
	 * 排序
	 */
	private int order;
	
	/**
	 * 链接，为angularjs使用，例如“demo”
	 */
	private String url;
	
	/**
	 * 权限，后台验证请求使用，如“/user/”
	 */
	private String permission;
	
	/**
	 * 子菜单
	 */
	private List<Function> child;

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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public List<Function> getChild() {
		return child;
	}

	public void setChild(List<Function> child) {
		this.child = child;
	}
	
}
