package com.huel.xgms.base.bean;

import java.io.Serializable;

/**
 * sms失败响应实体
 * @author wsq
 * @date 2018/4/10
 */
public class SmsFailBean implements Serializable{
    private static final long serialVersionUID = -8901723150023140946L;
    /**
     * 失败手机号
     */
    private String phone;
    /**
     * 错误码
     */
    private String respCode;
    /**
     * 错误描述
     */
    private String respDesc;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }
}
