package com.huel.xgms.base.service;

/**
 * 会话服务
 * @author wsq
 * @date 2018/3/20
 */
public interface ISessionService {
    /**
     * 通过sessionId 获取用户id
     * @param sessionId
     * @return
     */
    String getUserId(String sessionId);

    /**
     * 保存session 信息
     * @param userId 用户id
     * @return 返回保存后的sessionId
     */
    String saveSession(String userId);
}
