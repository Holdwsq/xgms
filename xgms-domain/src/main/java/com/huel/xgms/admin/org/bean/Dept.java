package com.huel.xgms.admin.org.bean;

import java.util.List;

/**
 * 部门实体
 * @author wsq
 * @date 2018/3/27
 */
public class Dept {

	public static final int UPDATE_TYPE_NORMAL = 1;
	
	public static final int UPDATE_TYPE_DEL = 2;
	
	private int id;
	
	private String name;
	
	private String code;
	
	private int pid;
	
	private int sort;
	
	private int utype;
	
	private long utime;
	
	private List<Dept> children;
	
	private List<Emploee> emploee;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getUtype() {
		return utype;
	}

	public void setUtype(int utype) {
		this.utype = utype;
	}

	public List<Emploee> getEmploee() {
		return emploee;
	}

	public void setEmploee(List<Emploee> emploee) {
		this.emploee = emploee;
	}

	public long getUtime() {
		return utime;
	}

	public void setUtime(long utime) {
		this.utime = utime;
	}

	public List<Dept> getChildren() {
		return children;
	}

	public void setChildren(List<Dept> children) {
		this.children = children;
	}
}
