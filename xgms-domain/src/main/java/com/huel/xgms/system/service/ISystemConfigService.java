package com.huel.xgms.system.service;

import com.huel.xgms.system.bean.SystemConfig;

/**
 * 本地系统配置服务接口
 * @author wsq
 * @date 2018/3/13
 */
public interface ISystemConfigService {
    /**
     * 通过code 获取配置值
     * @param code
     * @return
     */
    String getValueByCode(String code);

    /**
     * 获取base 和 code 拼接配置值
     * @param base
     * @param code
     * @return
     */
    String getValueByCode(String base, String code);

    /**
     * 更新系统配置
     * @param systemConfig
     */
    void update(SystemConfig systemConfig);

    /**
     * 更新系统配置
     * @param id
     * @param value
     */
    void update(String id,String value);

    /**
     * 保存系统配置
     * @param systemConfig
     */
    void save(SystemConfig systemConfig);

    /**
     * 更新系统配置
     * @param code
     * @param value
     */
    void updateCodeValue(String code, String value);
}
