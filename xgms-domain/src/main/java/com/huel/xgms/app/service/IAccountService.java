package com.huel.xgms.app.service;

import com.huel.xgms.app.bean.AccountBean;
import com.huel.xgms.app.bean.User;

/**
 * @description 客服端用户登录服务层
 * @author wsq
 * @date 2018/1/7
 */
public interface IAccountService {
    /**
     * 用户登录
     * @param account
     * @param pwd
     * @return User
     */
    User login(String account, String pwd);

    /**
     * 用户注册服务
     * @param bean
     */
    void register(AccountBean bean);
}
