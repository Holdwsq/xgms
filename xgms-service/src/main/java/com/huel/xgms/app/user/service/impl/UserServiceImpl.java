package com.huel.xgms.app.user.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.huel.xgms.app.user.bean.User;
import com.huel.xgms.app.user.dao.IUserDao;
import com.huel.xgms.app.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 用户接口服务
 * @author wsq
 * @date 2018/3/4
 */
@Service
public class UserServiceImpl implements IUserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private IUserDao userDao;

    @Override
    public User getUserInfo(String userId) {
        User user = null;
        if (StringUtils.isEmpty(userId)){
            return user;
        }
        user = userDao.getUserInfo(userId);
        LOG.info("userID：{} 对应的人员信息：{}", userId, JSON.toJSONString(user));
        return user;
    }

    @Override
    public void update(User user) {
        LOG.debug("更新用户信息，参数传递：{}", JSON.toJSONString(user));
        if (user == null){
            return ;
        }
        // 搜索该用户的是否存在
        User userInfo = getUserInfo(user.getId());
        if (userInfo == null){
            throw new RuntimeException("更新用户已被删除");
        }

        long time = System.currentTimeMillis();

        user.setUpdateTime(time);
        userDao.updateUserInfo(user);
    }
}
