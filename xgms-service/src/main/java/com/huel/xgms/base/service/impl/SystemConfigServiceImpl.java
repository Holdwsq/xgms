package com.huel.xgms.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.huel.xgms.base.dao.ISystemConfigDao;
import com.huel.xgms.system.bean.SystemConfig;
import com.huel.xgms.system.service.ISystemConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 系统配置服务层实现
 * @author wsq
 * @date 2018/3/13
 */
@Service
public class SystemConfigServiceImpl implements ISystemConfigService {

    private static final Logger LOG = LoggerFactory.getLogger(SystemConfigServiceImpl.class);

    @Autowired
    private ISystemConfigDao systemConfigDao;

    @Override
    public String getValueByCode(String code) {
        if (StringUtils.isEmpty(code)){
            return null;
        }
        SystemConfig systemConfig = systemConfigDao.getByCode(code);
        if (LOG.isDebugEnabled()){
            LOG.debug("通过code 为：{}，得到系统配置：{}", code, JSON.toJSONString(systemConfig));
        }
        return systemConfig == null ? null : systemConfig.getValue();
    }

    @Override
    public String getValueByCode(String base, String code) {
        if (StringUtils.isEmpty(base)
                && StringUtils.isEmpty(code)){
            return null;
        }

        SystemConfig baseConfig= systemConfigDao.getByCode(base);
        SystemConfig codeConfig = systemConfigDao.getByCode(code);

        StringBuilder str = new StringBuilder();
        if(baseConfig != null && !StringUtils.isEmpty(baseConfig.getValue())){
            str.append(baseConfig.getValue());
        }
        if(codeConfig != null && !StringUtils.isEmpty(codeConfig.getValue())){
            str.append(codeConfig.getValue());
        }
        if (LOG.isDebugEnabled()){
            LOG.debug("通过base：{}，code：{}，得到value：{}",  base, code, str.toString());
        }
        return str.toString();
    }

    @Override
    public void update(SystemConfig systemConfig) {
        if (LOG.isDebugEnabled()){
            LOG.debug("更新系统配置：{}", JSON.toJSONString(systemConfig));
        }
        systemConfigDao.update(systemConfig);
    }

    @Override
    public void update(String id, String value) {

    }

    @Override
    public void save(SystemConfig systemConfig) {
        if (LOG.isDebugEnabled()){
            LOG.debug("新增系统配置：{}", JSON.toJSONString(systemConfig));
        }
        systemConfigDao.save(systemConfig);
    }

    @Override
    public void updateCodeValue(String code, String value) {
        systemConfigDao.updateCodeValue(code, value);
    }
}
