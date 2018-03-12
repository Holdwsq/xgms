package com.huel.xgms.app.user.service;

import com.huel.xgms.app.user.bean.AuthBean;

/**
 * 用户学生认证服务接口
 * @author wsq
 * @date 2018/3/12
 */
public interface IAuthService {
    /**
     * 用户的学生认证
     * @param bean
     */
    void auth(AuthBean bean);

    /**
     * 通过用户id获取，认证状态
     * @param userId
     * @return
     */
    String getAuthStatus(String userId);

}
