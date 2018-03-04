package com.huel.xgms.api.login;

import com.google.common.collect.Maps;
import com.huel.xgms.base.BaseTest;
import com.huel.xgms.util.HttpRequestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @description
 * @date 2018/1/6
 */
public class AccountControllerTest extends BaseTest{
    /**
     *  测试注册接口
     *  @author wsq
     */
    @Test
    public void testRegister(){
        String account = "Tom";
        String pwd = "123456";

        String url = baseUrl + "/pub/account/register";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("accountName", account);
        paramMap.put("pwd", pwd);
        paramMap.put("confirmPwd", pwd);
        String resp = HttpRequestUtils.post(url, paramMap);
        Assert.assertNotNull(resp);
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
}
