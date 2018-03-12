package com.huel.xgms.app.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.huel.xgms.app.user.bean.AuthBean;
import com.huel.xgms.app.user.dao.IAuthDao;
import com.huel.xgms.app.user.service.IAuthService;
import com.huel.xgms.util.UUIDMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 用户学生认证接口实现
 * @author wsq
 * @date 2018/3/12
 */
@Service
public class AuthServiceImpl implements IAuthService {
    private static final Logger LOG = LoggerFactory.getLogger(AuthServiceImpl.class);
    @Autowired
    private IAuthDao authDao;

    @Override
    public void auth(AuthBean bean) {
        // 用户提出学生认证申请，这里直接入库，等待后台管理员处理
        LOG.debug("用户学生认证申请，参数传递：{}", JSON.toJSONString(bean));
        if (bean == null){
            return;
        }
        bean.setAuthStatus(AuthBean.AUTH_STATUS_PENDING);
        bean.setCreateTime(System.currentTimeMillis());
        String id = UUIDMaker.generateUUID();
        bean.setId(id);
        bean.setDeleteFlag(AuthBean.DELETE_FLAG_NO);

        authDao.save(bean);
    }

    @Override
    public String getAuthStatus(String userId) {
        // 获取用户认证状态 状态为空 代表没有认证记录
        return authDao.getAuthStatus(userId);
    }
}
