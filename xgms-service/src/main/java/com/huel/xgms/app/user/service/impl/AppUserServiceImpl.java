package com.huel.xgms.app.user.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.huel.xgms.app.user.bean.User;
import com.huel.xgms.app.user.dao.IUserDao;
import com.huel.xgms.app.user.service.IUserService;
import com.huel.xgms.base.bean.PagingQueryBean;
import com.huel.xgms.page.Pagination;
import org.jfaster.mango.plugin.page.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * 用户接口服务
 * @author wsq
 * @date 2018/3/4
 */
@Service
public class AppUserServiceImpl implements IUserService {
    private static final Logger LOG = LoggerFactory.getLogger(AppUserServiceImpl.class);

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

    @Override
    public List<User> list(Set<String> userIds) {
        List<User> userList = Lists.newLinkedList();
        if (CollectionUtils.isEmpty(userIds)){
            return userList;
        }
        // 获取用户列表
        userList = userDao.list(userIds);
        LOG.info("通过用户IDs：{}，得到用户信息：{}", JSON.toJSONString(userIds), JSON.toJSONString(userList));
        return userList;
    }

    @Override
    public Pagination listByPage(PagingQueryBean queryBean) {
        Pagination pagination = new Pagination();
        pagination.setList(new ArrayList());
        // 如果查询类为空，直接返回
        if (queryBean == null){
            return pagination;
        }
        Page page = new Page();
        page.setPageNum(queryBean.getPageNo());
        page.setPageSize(queryBean.getPageSize());
        // 分页获取用户
        List<User> list = userDao.listByPage(queryBean, page);

        pagination.setTotal(page.getTotal());
        pagination.setList(list);
        return pagination;
    }
}
