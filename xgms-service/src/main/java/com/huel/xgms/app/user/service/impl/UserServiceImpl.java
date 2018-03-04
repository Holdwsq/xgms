package com.huel.xgms.app.user.service.impl;

import com.alibaba.druid.util.StringUtils;
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
        return null;
    }
}
