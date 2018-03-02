package com.huel.xgms.app.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 * @description
 * @date 2018/1/7
 */
public class User implements Serializable{
    private static final long serialVersionUID = -4804146827693434948L;
    /**
     * 用户id
     */
    private String id;
    /**
     * 客户端登录账号
     */
    private String account;
    /**
     * 客服端登录密码
     */
    private String pwd;
    /**
     * 用户昵称
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
     * 删除标识 0-未删除 1-已删除
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
    /**
     * 年龄
     */
    private int age;
    private String birDate;
    /**
     * 手机号
     */
    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
