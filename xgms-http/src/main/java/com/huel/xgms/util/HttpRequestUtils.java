package com.huel.xgms.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author wsq
 * @description http请求工具类
 * @date 2018/3/1
 */
public class HttpRequestUtils {
    private static Logger LOG = LoggerFactory.getLogger(HttpRequestUtils.class);
    /**
     * @description
     * @param url
     * @param param
     * @return 请求返回数据
     */
    public static String postJson(String url, String param){
        LOG.debug("HttpRequestUtils postJson，请求参数 url：{},param：{}", url, param);
        String respData = null;
        CloseableHttpClient httpClient;
        HttpPost httpPost;
        try {
            httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            // 传递参数
            StringEntity reqEntiry = new StringEntity(param, "UTF-8");
            httpPost.setEntity(reqEntiry);

            CloseableHttpResponse resp = httpClient.execute(httpPost);
            StatusLine statusLine = resp.getStatusLine();
            if (statusLine.getStatusCode() == HttpStatus.SC_OK){
                // 请求成功
                HttpEntity entity = resp.getEntity();
                respData = EntityUtils.toString(entity, "UTF-8");
            }else{
                // 请求失败处理
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respData;
    }

    /**
     * @description post url key-value 请求
     * @param url
     * @param paramMap
     * @return 响应结果
     */
    public static String post(String url, Map<String, String> paramMap){
        LOG.debug("HttpRequestUtils post, 请求参数 url：{}，paramList：{}", url, JSON.toJSONString(paramMap));

        String respData = null;
        CloseableHttpClient httpClient;
        HttpPost httpPost;
        try {
            httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            // 请求参数 转为NameValuePair List
            List<NameValuePair> list = new LinkedList<NameValuePair>();
            for (String s : paramMap.keySet()) {
                list.add(new BasicNameValuePair(s, paramMap.get(s)));
            }
            // url key-value 传参
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list);
            httpPost.setEntity(entity);
            // 解析响应结果
            CloseableHttpResponse resp =  httpClient.execute(httpPost);
            StatusLine statusLine = resp.getStatusLine();
            if (statusLine != null && statusLine.getStatusCode() == HttpStatus.SC_OK){
                // 请求成功
                respData = EntityUtils.toString(resp.getEntity(), "UTF-8");
            }else{
                // 请求失败
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respData;
    }

    /**
     * @description get 请求
     * @param url
     * @param param
     * @return 响应结果
     */
    public static String get(String url, String param){
        String respData = null;
        CloseableHttpClient httpClient;
        HttpGet httpGet;
        try{
            httpClient = HttpClients.createDefault();
            // 拼接为 Get 请求格式
            url = url + "?" + param;
            httpGet = new HttpGet(url);

            CloseableHttpResponse resp = httpClient.execute(httpGet);
            StatusLine statusLine = resp.getStatusLine();

            if (statusLine != null && statusLine.getStatusCode() == HttpStatus.SC_OK){
                // 请求成功
                respData = EntityUtils.toString(resp.getEntity(), "UTF-8");
            }else{
                // 请求失败，处理
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respData;
    }
}
