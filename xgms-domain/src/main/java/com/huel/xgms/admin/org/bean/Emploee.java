package com.huel.xgms.admin.org.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 人员实体bean
 * @author wsq
 */
public class Emploee {
	
	public static final int UPDATE_TYPE_NORMAL = 1;
	
	public static final int UPDATE_TYPE_DEL = 2;

	private int id;
	
	private String name;
	
	private String py;
	
	private String emplid;
	
	@JSONField(serialize = false)
	private String pwd;
	
	private String tel;
	
	private String mobile;
	
	private String email;
	
	private int deptid;
	
	private String deptName;
	
	private String job;
	
	private int sort;
	
	/**
	 * {@linkplain #UPDATE_TYPE_NORMAL}, {@linkplain #UPDATE_TYPE_DEL}
	 */
	private int utype;
	
	private long utime;
	
	private String avater;
	
	private long entryTime;
	
	private long quitTime;

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

	public String getPy() {
		return py;
	}

	public void setPy(String py) {
		this.py = py;
	}

	public String getEmplid() {
		return emplid;
	}

	public void setEmplid(String emplid) {
		this.emplid = emplid;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDeptid() {
		return deptid;
	}

	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
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

	public String getAvater() {
		return avater;
	}

	public void setAvater(String avater) {
		this.avater = avater;
	}

	public long getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(long entryTime) {
		this.entryTime = entryTime;
	}

	public long getQuitTime() {
		return quitTime;
	}

	public void setQuitTime(long quitTime) {
		this.quitTime = quitTime;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public long getUtime() {
		return utime;
	}

	public void setUtime(long utime) {
		this.utime = utime;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
}
