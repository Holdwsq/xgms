package com.huel.xgms.api.login;

import com.alibaba.fastjson.JSON;
import com.huel.xgms.app.bean.User;
import com.huel.xgms.base.BaseTest;
import com.huel.xgms.httpbean.ResponseBean;
import com.huel.xgms.util.HttpRequestUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @description
 * @date 2018/1/6
 */
public class UserLoginTest extends BaseTest{

    @Test
    public void testRegister() throws IOException {
        String registerUrl = baseUrl + "/pub/register";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建一个User对象
        User user = new User();
        user.setUserName("name");
        user.setAge(18);
        user.setBirDate("2014");
        user.setPhone("15670099659");
        String s = JSON.toJSONString(user);

        HttpPost post = new HttpPost(registerUrl);
        StringEntity entity = new StringEntity(s, "UTF-8"); // 解决中文乱码
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        post.setEntity(entity);

        CloseableHttpResponse httpResponse = httpClient.execute(post);
        String resStr = EntityUtils.toString(httpResponse.getEntity());

        ResponseBean responseBean = JSON.parseObject(resStr, ResponseBean.class);
        Assert.assertEquals(ResponseBean.STATUS_SUCCESS, responseBean.getStatus());
    }
    @Test
    public void testLogin(){
        String account = "wang";
        String pwd = "123456";

        String url = baseUrl + "/pub/account";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("account", account);
        paramMap.put("pwd", pwd);

        String resp = HttpRequestUtils.post(url, paramMap);
        Assert.assertNotNull(resp);
    }
}
