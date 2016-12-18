package com.github.liuxboy.httpclient.examples;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Package: com.github.liuxboy.httpclient <br>
 * Author: liuchundong <br>
 * Date: 2016/12/7 <br>
 * Time: 15:07 <br>
 * Desc: 简单Post请求
 */
public class SamplePost {
    public static void main(String[] args) {
        try {
            String url = "http://www.baidu.com";
            // 使用默认配置创建httpclient的实例
            CloseableHttpClient client = HttpClients.createDefault();

            HttpPost post = new HttpPost(url);
            CloseableHttpResponse response = client.execute(post);
            // 服务器返回码
            int status_code = response.getStatusLine().getStatusCode();
            System.out.println("status_code = " + status_code);
            // 服务器返回内容
            String respStr = null;
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                entity.getContent();
                respStr = EntityUtils.toString(entity, "UTF-8");
            }
            System.out.println("respStr = " + respStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
