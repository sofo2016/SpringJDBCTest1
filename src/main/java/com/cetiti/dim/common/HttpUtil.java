package com.cetiti.dim.common;


import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import java.io.IOException;


public class HttpUtil {

    private static Logger logger = Logger.getLogger(HttpUtil.class);

    public static void post(String content, String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        StringEntity se = new StringEntity(content, "UTF-8");
        se.setContentEncoding("UTF-8");
        se.setContentType("application/json");

        httpPost.setEntity(se);
        try {
            httpClient.execute(httpPost);
        } catch (IOException e) {
            logger.error("execute HttpUtil failed: ", e);
        }
        // 关闭连接,释放资源
        httpClient.close();
    }
    public static void postlog(String content, String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        try {
            httpClient.execute(httpPost);
        } catch (IOException e) {
        }
        // 关闭连接,释放资源
        httpClient.close();
    }
    
    public static void postLine(String content, String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        StringEntity se = new StringEntity(content, "UTF-8");
        se.setContentEncoding("UTF-8");

        httpPost.setEntity(se);
        try {
            httpClient.execute(httpPost);
        } catch (IOException e) {
        }
        // 关闭连接,释放资源
        httpClient.close();
    }
}
