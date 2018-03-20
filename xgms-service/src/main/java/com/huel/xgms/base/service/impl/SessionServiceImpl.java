package com.huel.xgms.base.service.impl;

import com.huel.xgms.base.service.ISessionService;
import org.springframework.stereotype.Service;

/**
 * session 服务实现
 * @author wsq
 * @date 2018/3/20
 */
@Service
public class SessionServiceImpl implements ISessionService{

    @Override
    public String getUserId(String sessionId) {
        /// todo 调用redis服务获取用户id
        return null;
    }

    @Override
    public String saveSession(String userId) {
        /// todo 保存Session 信息
        return null;
    }
}
