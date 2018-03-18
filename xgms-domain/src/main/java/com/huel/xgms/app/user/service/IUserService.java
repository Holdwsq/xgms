package com.huel.xgms.app.user.service;

import com.huel.xgms.app.user.bean.AuthBean;
import com.huel.xgms.app.user.bean.User;

import java.util.List;
import java.util.Set;

/**
 * Created by admin
 *
 * @date 2018/3/4
 */
public interface IUserService {
    /**
     * 获取用户信息
     * @param userId 用户id
     * @return User
     */
    User getUserInfo(String userId);

    /**
     * 更新用户信息
     * @param user 用户信息
     */
    void update(User user);

    /**
     * 获取用户列表
     * @param userIds 用户id集合
     * @return List<User>
     */
    List<User> list(Set<String> userIds);
}
