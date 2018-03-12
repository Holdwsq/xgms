package com.huel.xgms.app.user.bean;

import java.io.Serializable;

/**
 * 用户认证bean
 * @author wsq
 * @date 2018/3/12
 */
public class AuthBean implements Serializable{
    private static final long serialVersionUID = 9078138966429939051L;

    public static final String AUTH_STATUS_PENDING = "0";
    public static final String AUTH_STATUS_SUCCESS = "1";
    public static final String AUTH_STATUS_FAIL = "2";

    public static final String DELETE_FLAG_NO = "0";
    public static final String DELETE_FLAG_YES = "1";

    /**
     * 主键id
     */
    private String id;
    /**
     * 认证人id
     */
    private String userId;
    /**
     * 认证人名称
     */
    private String userName;
    /**
     * 学校名称
     */
    private String schoolName;
    /**
     * 学号
     */
    private String studentNo;
    /**
     * 学历
     */
    private String education;
    /**
     * 入学年份
     */
    private String entranceYear;
    /**
     * 管理者 操作人 id
     */
    private String operatorId;
    /**
     * 操作时间
     */
    private Long operateTime;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 删除 标识
     * 未删除 = 0，{@linkplain DELETE_FLAG_NO}
     * 已删除 = 1，{@linkplain DELETE_FLAG_YES}
     */
    private String deleteFlag;
    /**
     * 认证 状态，默认为 待处理
     * 待处理 = '0'， {@linkplain AUTH_STATUS_PENDING}
     * 认证成功 = '1', {@linkplain AUTH_STATUS_SUCCESS}
     * 认证失败 = '2', {@linkplain AUTH_STATUS_FAIL}
     */
    private String authStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEntranceYear() {
        return entranceYear;
    }

    public void setEntranceYear(String entranceYear) {
        this.entranceYear = entranceYear;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public Long getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Long operateTime) {
        this.operateTime = operateTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus;
    }
}
