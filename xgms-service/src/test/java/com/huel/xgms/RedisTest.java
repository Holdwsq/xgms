package com.huel.xgms;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @redis 连接测试
 * @author wsq
 * @date 2018/3/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext-*.xml"})
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void testRedisConnect(){
        String s = "1233";
        redisTemplate.opsForValue().set("方法", s);
    }
    @Test
    public void testRedisInString(){
        String data = "今天是2018年3月11号，周末";
        Assert.assertNotNull(redisTemplate);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Assert.assertNotNull("valueOps不能为空", valueOperations);
        valueOperations.set("data", data);
        String lastData = (String) valueOperations.get("data");
        Assert.assertEquals(data, lastData);

        // test 插入基本类型数据
        valueOperations.set("int", 123);
        valueOperations.set("boolean", true);
        valueOperations.set("chat", 'c');
        valueOperations.set("float", 12.12);
        System.out.println("int:" + valueOperations.get("int"));
        System.out.println("boolean:" + valueOperations.get("boolean"));
    }

    /**
     *  测试 redis 插入list
     */
    @Test
    public void testRedisInList(){
        // listOps 配置
        ListOperations listOperations = redisTemplate.opsForList();
        redisTemplate.delete("myList");
        String listName = "myList";
        listOperations.leftPush(listName, "A");
        listOperations.leftPush(listName, "B");
        listOperations.rightPush(listName, "C");

        List list = listOperations.range(listName, 0, -1);
        int i = 0;
        for (Object o : list) {
            System.out.println("第" + ++i + "个元素：" + o);
        }
        Assert.assertEquals(3, list.size());
        List<String> sessionList = new ArrayList<String>();
        sessionList.add("session1");
        sessionList.add("session2");
        sessionList.add("session3");
        listOperations.rightPushAll(listName, sessionList);

        List range = listOperations.range(listName, 0, -1);
        int j = 0;
        for (Object o : range) {
            System.out.println("第" + ++i + "个元素：" + o);
        }
    }

    /**
     * 测试写入Set集合数据
     */
    @Test
    public void testRedisInSet(){
        redisTemplate.delete("mySet");

        SetOperations setOperations = redisTemplate.opsForSet();
        String setName = "mySet";

        setOperations.add(setName, "A");
        setOperations.add(setName, "A");

        Set members = setOperations.members(setName);
        Assert.assertEquals(1, members.size());

        // 多个值操作
        setOperations.add(setName, "B", "C", "D");
        Boolean b = setOperations.isMember(setName, "B");
        Assert.assertTrue(b);
        // set
        Object pop = setOperations.pop(setName);
        System.out.println("pop 的元素为：" + pop);
    }
    @Test
    public void testRedisInHash(){
        String hashName = "myHash";

        redisTemplate.delete(hashName);
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put(hashName, "BJ", "北京");
        hashOperations.put(hashName, "SH", "上海");
        hashOperations.put(hashName, "HB", "鹤壁");
        Map entries = hashOperations.entries(hashName);
        Set set = entries.keySet();
        for (Object o : set) {
            System.out.println("key : " + o + ", value : " + entries.get(o));
        }
    }
}
