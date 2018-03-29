package com.huel.xgms.app.user.service;

import com.huel.xgms.app.user.bean.AuthBean;
import com.huel.xgms.app.user.bean.User;
import com.huel.xgms.base.bean.PagingQueryBean;
import com.huel.xgms.page.Pagination;

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

    /**
     * 分页获取应用用户
     * @param queryBean 查询bean
     * @return Pagination
     */
    Pagination listByPage(PagingQueryBean queryBean);

    /**
     * 重置用户密码
     * @param userId 用户id
     * @param defaultPwd 默认用户密码
     */
    void resetPwd(String userId, String defaultPwd);

    /**
     * 禁用app用户
     * @param userId
     */
    void disable(String userId);

    /**
     * 启用app用户
     * @param userId
     */
    void enable(String userId);

    /**
     * 通过后台新增App用户
     * @param user 用户信息
     */
    void addUserByAdmin(User user);

    /**
     * 管理员修改用户
     * @param user
     */
    void editUser(User user);
}
