package com.huel.xgms.app.user.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息Bean
 * @author wsq
 * @date 2018/1/7
 */
public class User implements Serializable{

    private static final long serialVersionUID = -4804146827693434948L;

    public static final String FLAG_DELETE_NO = "0";
    public static final String FLAG_DELETE_YES = "1";
    /**
     * 用户id
     */
    private String id;
    /**
     * 客户端登录账号
     */
    private String accountName;
    /**
     * 客服端登录密码
     */
    private String pwd;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户爱好
     */
    private String hobby;
    /**
     * 缩略图 id
     */
    private String previewId;
    /**
     * 原图 id
     */
    private String fileId;
    /**
     * 性别 1-男 2-女
     */
    private String sex;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 简介
     */
    private String brief;
    /**
     * 学校名称
     */
    private String school;
    /**
     * 删除标识
     * 未删除 = 0， {@linkplain FLAG_DELETE_NO}
     * 已删除 = 1，{@linkplain FLAG_DELETE_YES}
     */
    private String deleteFlag;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 更新时间
     */
    private Long updateTime;
    /**
     * 是否认证 0-未认证 1-已认证
     */
    private String auth;

    private String birDate;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 用户年龄
     */
    private Integer age;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getPreviewId() {
        return previewId;
    }

    public void setPreviewId(String previewId) {
        this.previewId = previewId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirDate() {
        return birDate;
    }

    public void setBirDate(String birDate) {
        this.birDate = birDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
