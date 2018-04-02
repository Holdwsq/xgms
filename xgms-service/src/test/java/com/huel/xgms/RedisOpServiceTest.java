package com.huel.xgms;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.huel.xgms.base.service.IRedisOpService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.Set;

/**
 * @author admin
 * @date 2018/3/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext-*.xml"})
public class RedisOpServiceTest {
    @Autowired
    private IRedisOpService redisOpService;

    @Test
    public void testGet(){
        String key = "test";
        String lalal = "lalal";
        Map<String, Object> map = Maps.newHashMap();
        map.put("wang", "wang");
        map.put("class", 7);
        map.put("sex", "男");
        redisOpService.set(key, JSON.toJSONString(map), 200L);
        String s = redisOpService.get(key);
        Map x = JSON.parseObject(s, Map.class);
        Set set = x.keySet();
        for (Object o : set) {
            System.out.println("key : " + o + ", value: " + x.get(o));
        }
        System.out.println(x);
        Assert.assertEquals("lalal", s);
    }
}
