package com.huel.xgms.admin.system.bean;

import java.util.List;

/**
 * 系统管理员
 * @author zxq
 *
 */
public class User {

	public static final int STATUS_ENABLE = 1;
	
	public static final int STATUS_DISABLE = 2;
	
	public static final int TYPE_SUPER = 1;
	
	public static final int TYPE_NORMAL = 2;
	
	/**
	 * 主键
	 */
	private int id;
	
	/**
	 * 账号
	 */
	private String account;
	
	/**
	 * 密码
	 */
	private String pwd;
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 创建时间
	 */
	private long createTime;
	
	/**
	 * 状态：启用=1，{@linkplain #STATUS_ENABLE}，禁用=2，{@linkplain #STATUS_DISABLE}
	 */
	private int status;
	
	/**
	 * 管理员类型，超级管理员，普通管理员
	 * {@linkplain #TYPE_SUPER}, {@linkplain #TYPE_NORMAL}}
	 */
	private int type;
	
	/**
	 * 权限列表
	 */
	private List<String> permissions;
	
	private List<Integer> roles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	public List<Integer> getRoles() {
		return roles;
	}

	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}
	
	
}
