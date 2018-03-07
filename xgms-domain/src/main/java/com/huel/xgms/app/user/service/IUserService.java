package com.huel.xgms.app.user.service;

import com.huel.xgms.app.user.bean.User;

/**
 * Created by admin
 *
 * @date 2018/3/4
 */
public interface IUserService {
    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    User getUserInfo(String userId);

    /**
     * 更新用户信息
     * @param user
     */
    void update(User user);
}
