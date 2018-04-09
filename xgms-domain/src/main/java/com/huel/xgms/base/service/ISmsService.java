package com.huel.xgms.base.service;

/**
 * 短信发送服务接口
 * @author wsq
 * @date 2018/4/9
 */
public interface ISmsService {
    /**
     * 发送注册验证码
     * @param phone
     * @return
     */
    String sendRegisterCode(String phone);
}
