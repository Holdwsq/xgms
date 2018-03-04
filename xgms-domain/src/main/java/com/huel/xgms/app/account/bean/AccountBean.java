package com.huel.xgms.app.account.bean;

/**
 * @description 用户注册Bean
 * @author wsq
 * @date 2018/3/1
 */
public class AccountBean {
    /**
     * 用户名
     */
    private String accountName;
    /**
     * 密码
     */
    private String pwd;
    /**
     * 确认密码
     */
    private String confirmPwd;
    /**
     * 该账户对应的用户ID
     */
    private String userId;

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

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
