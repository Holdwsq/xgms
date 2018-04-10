package com.huel.xgms.base.bean;

import java.io.Serializable;

/**
 * 手机验证码记录bean
 * @author wsq
 * @date 2018/4/10
 */
public class PinRecordBean implements Serializable{

    private static final long serialVersionUID = -8843131003729548867L;
    /**
     * 记录id
     */
    private int id;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 验证码
     */
    private String code;
    /**
     * 发送时间
     */
    private long sendTime;
    /**
     * 过期时间
     */
    private long expiryTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }
}
