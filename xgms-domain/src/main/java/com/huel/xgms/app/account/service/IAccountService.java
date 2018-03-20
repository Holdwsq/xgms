package com.huel.xgms.app.account.service;

import com.huel.xgms.app.account.bean.AccountBean;
import com.huel.xgms.app.user.bean.User;

/**
 * @description 客服端用户登录服务层
 * @author wsq
 * @date 2018/1/7
 */
public interface IAccountService {
    /**
     * 用户登录
     * @param accountName
     * @param pwd
     * @return User
     */
    User login(String accountName, String pwd);

    /**
     * 用户注册服务
     * @param bean
     */
    User register(AccountBean bean);

    /**
     * 检测用户注册的账户名是否合法
     * @param accountName 用户注册账户名
     * @param userId 第二个参数为空
     * @return boolean
     */
    boolean validateAccountName(String accountName, String userId);

    /**
     * 修改密码
     * @param userId
     * @param pwd
     */
    void modifyPwd(String userId, String pwd);
}
