package com.huel.xgms.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.huel.xgms.base.service.IRedisOpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * redis 常用操作服务实现
 * @author wsq
 * @date 2018/3/11
 */
@Service
public class RedisOpServiceImpl implements IRedisOpService {

    private static Logger LOG = LoggerFactory.getLogger(RedisOpServiceImpl.class);
    private RedisTemplate redisTemplate;
    private ValueOperations valueOperations;
    private HashOperations hashOperations;

    public RedisOpServiceImpl(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public String get(String key) {
        Object o = valueOperations.get(key);
        LOG.debug("redisop 获取 key：{} 对应 value ：{}", key, o);
        return (String) o;
    }

    @Override
    public void set(String key, Object obj) {
        String data = JSON.toJSONString(obj);
        valueOperations.set(key, data);
    }

    @Override
    public void putInHash(String key, String hKey, Object hObj) {

    }

    @Override
    public boolean hasKeyInHash(String key, String hKey) {
        return false;
    }

    @Override
    public String getValueFromHash(String key, String hKey) {
        return null;
    }

    @Override
    public Map<String, String> getEntriesFromHash(String key) {
        return null;
    }

    @Override
    public Long deleteKeysFromHash(String key, String... hashKeys) {
        return null;
    }
}
