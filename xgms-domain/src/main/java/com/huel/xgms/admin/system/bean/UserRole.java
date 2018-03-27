package com.huel.xgms.admin.system.bean;

public class UserRole {

	private int id;
	
	private int userId;
	
	private int roleId;

	public UserRole() {
	}

	public UserRole(int userId, int roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
}
