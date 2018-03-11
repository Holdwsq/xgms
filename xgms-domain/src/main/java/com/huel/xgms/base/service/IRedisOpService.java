package com.huel.xgms.base.service;

import java.util.Map;

/**
 * 针对Redis 操作服务的一些接口, 统一将值保存为String 类型
 * @author wsq
 * @date 2018/3/11
 */
public interface IRedisOpService {
    /**
     * 通过key获取List中对应的元素
     * @param key
     * @return value
     */
    String get(String key);

    /**
     * 保存List 元素
     * @param key
     * @param obj
     */
    void set(String key, Object obj);

    /**
     * 保存值到 Hash 表
     * @param key
     * @param hKey
     * @param hObj
     */
    void putInHash(String key, String hKey, Object hObj);

    /**
     * check hash表中是否有该 键值
     * @param key
     * @param hKey
     * @return
     */
    boolean hasKeyInHash(String key, String hKey);

    /**
     * 从 hash 表中获取 键 对应的 值
     * @param key
     * @param hKey
     * @return
     */
    String getValueFromHash(String key, String hKey);

    /**
     * 获取 键值对
     * @param key
     * @return
     */
    Map<String, String> getEntriesFromHash(String key);

    /**
     * 删除 hash 表中的 键位
     * @param key
     * @param hashKeys
     * @return
     */
    Long deleteKeysFromHash(String key, String... hashKeys);

}
