package com.huel.xgms.base.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 信息响应bean
 * @author wsq
 * @date 2018/4/10
 */
public class SmsRespBean implements Serializable{
    private static final long serialVersionUID = -2752498454644920053L;
    /**
     * 成功 响应成功，其他均为失败
     */
    public static final String SUCCESS = "00000";
    /**
     *
     */
    private String respCode;
    private String respDesc;
    private String failCount;
    private List<SmsFailBean> failList;
    private String smsId;

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

    public String getFailCount() {
        return failCount;
    }

    public void setFailCount(String failCount) {
        this.failCount = failCount;
    }

    public List<SmsFailBean> getFailList() {
        return failList;
    }

    public void setFailList(List<SmsFailBean> failList) {
        this.failList = failList;
    }

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }
}
