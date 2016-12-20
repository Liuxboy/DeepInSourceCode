package com.github.liuxboy.httpclient.examples;

import org.apache.commons.codec.Charsets;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


/**
 * Package: com.github.liuxboy.httpclient <br>
 * Author: liuchundong <br>
 * Date: 2016/12/7 <br>
 * Time: 15:07 <br>
 * Desc: 简单Get请求
 */
public class SampleGet {

    public static void main(String[] args) {
        String url = "http://127.0.0.1:8080/getGreeting?name=zhangsan";
        // 使用默认配置创建httpclient的实例
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        try {
            // 执行请求
            CloseableHttpResponse response = client.execute(get);
            // 返回码
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("statusCode = " + statusCode);
            // 返回实体
            String respStr = null;
            HttpEntity entity = null;
            if (HttpStatus.SC_OK == statusCode) {
                entity = response.getEntity();
                Header header = entity != null ? entity.getContentType() : null;
                for (HeaderElement headerElement : header.getElements()) {
                    if (ContentType.APPLICATION_JSON.getMimeType().equals(headerElement.getName())){

                    }
                }
                //如果约定请求返回为json字符中，那resp本身就是json格式的，无需再轩换
                respStr = EntityUtils.toString(entity, Charsets.UTF_8);
            }
            System.out.println("respStr = " + respStr);
            // 释放资源(其实关掉entity.content的流)
            EntityUtils.consume(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
