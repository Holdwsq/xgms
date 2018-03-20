package com.huel.xgms.api.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Maps;
import com.huel.xgms.app.user.bean.User;
import com.huel.xgms.base.BaseTest;
import com.huel.xgms.httpbean.ResponseBean;
import com.huel.xgms.util.HttpRequestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 账户控制层测试
 * @author wsq
 * @date 2018/1/6
 */
public class AccountControllerTest extends BaseTest{
    /**
     *  测试注册接口
     *  @author wsq
     */
    @Test
    public void testRegister(){
        String account = "你好、骚年";
        String pwd = "w123456789";

        String url = baseUrl + "/pub/account/register";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("accountName", account);
        paramMap.put("pwd", pwd);
        paramMap.put("confirmPwd", pwd);
        String resp = HttpRequestUtils.post(url, paramMap);
        Type type = new TypeReference<ResponseBean<User>>(){}.getType();
        ResponseBean<User> userResponseBean = JSON.parseObject(resp, type);
        User data = userResponseBean.getData();
        // getUser Interface
        String getUserUrl = baseUrl + "/pub/user/" + data.getId();
        String s = HttpRequestUtils.get(getUserUrl, null);
        ResponseBean<User> userBean = JSON.parseObject(s, type);
        Assert.assertNull(userBean.getData());
    }

    /**
     * 测试登录接口
     */
    @Test
    public void testLogin(){
        String accountName = "Tom";
        String pwd = "tom";

        String url = baseUrl + "/pub/account/login";

        Map<String, String> map = new HashMap<>();
        map.put("accountName", accountName);
        map.put("pwd", pwd);

        String respData = HttpRequestUtils.post(url, map);
        Assert.assertNotNull(respData);
    }
    @Test
    public void testModifyPwd(){
        String userId = "210b346cf29a4577aa51fb077b04674b";
        String pwd = "tom123";

        String url = baseUrl + "/pri/account/modifyPwd";
        Map<String, String> map = Maps.newHashMap();
        map.put("userId", userId);
        map.put("pwd", pwd);

        String post = HttpRequestUtils.post(url, map);
        Assert.assertNotNull(post);

    }
    public static void main(String... args){
        System.out.println(System.currentTimeMillis());
    }
}
