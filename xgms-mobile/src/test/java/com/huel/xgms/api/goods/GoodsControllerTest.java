package com.huel.xgms.api.goods;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.huel.xgms.app.user.bean.User;
import com.huel.xgms.base.service.impl.QiNiuFileServiceImpl;
import com.huel.xgms.httpbean.ResponseBean;
import com.huel.xgms.util.HttpRequestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;

/**
 * 商品控制层测试
 * @author wsq
 * @date 2018/3/21
 */
public class GoodsControllerTest {
    public static final String BASEURL = "http://127.0.0.1:8080/xgms/api";

    /**
     * 测试发布商品
     * {@link com.huel.xgms.goods.GoodsController}
     */
    @Test
    public void testPublishGoods() throws FileNotFoundException {
        String publishUri = "/pri/goods";
        // 商品发布请求路径
        String publishUrl = BASEURL + publishUri;
        ClassLoader classLoader = this.getClass().getClassLoader();
//        InputStream resourceAsStream = classLoader.getResourceAsStream("Lighthouse.jpg");
        String fileUrl = "D:\\image\\java_momery.png";
        InputStream in = new FileInputStream(fileUrl);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        StringEntity entity;
        HttpPost post = new HttpPost(publishUrl);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        File file = new File(fileUrl);
        builder.addBinaryBody("files", file);
        /*builder.addBinaryBody("files", resourceAsStream);
        builder.addBinaryBody("files", resourceAsStream);*/
        builder.addTextBody("title", "中山大学", ContentType.TEXT_PLAIN.withCharset("UTF-8"));
        builder.addTextBody("type", "text", ContentType.TEXT_PLAIN.withCharset("UTF-8"));
        HttpEntity build = builder.build();
        post.setEntity(build);
        try {
            CloseableHttpResponse response = httpClient.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == HttpServletResponse.SC_OK){
                String s = EntityUtils.toString(response.getEntity(), "UTF-8");
                ResponseBean responseBean = JSON.parseObject(s, ResponseBean.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
