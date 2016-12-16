package com.github.liuxboy.httpclient.util;

import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Package: com.github.liuxboy.httpclient.util <br>
 * Author: liuchundong <br>
 * Date: 2016/12/8 <br>
 * Time: 15:16 <br>
 * Desc: HttpClient池化请求帮助类
 */
public class HttpPoolClient {
    /**
     * 以下3个超时相关的参数如果未配置，默认为-1，意味着无限大，就是一直阻塞等待!
     * @see org.apache.http.client.config.RequestConfig#socketTimeout
     * @see org.apache.http.client.config.RequestConfig#connectTimeout
     * @see org.apache.http.client.config.RequestConfig#connectionRequestTimeout
     */
    /**
     * 服务器返回数据(response)的时间，超过该时间抛出read timeout
     */
    private static int socketTimeout = 2000;    //单位：ms
    /**
     * 连接上服务器(握手成功)的时间，超出该时间抛出connect timeout
     */
    private static int connectTimeout = 2000;   //单位：ms
    /**
     * 从连接池中获取连接的超时时间，超过该时间未拿到可用连接，会抛出
     * org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool
     */
    private static int connectionRequestTimeout = 10000;    //单位：ms

    /**
     * 以下2个参数是关于请求池设置的
     * 比如整个池子200个连接，通过该连接池同时请求两个地址
     * 最大连接数，整个池子的连接数
     */
    private static int maxConnTotal = 300;          //最大连接数
    /**
     * 单个路由并发连接数
     */
    private static int maxConnPerRoute = 100;        //并发连接数

    private static RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(socketTimeout)
            .setConnectTimeout(connectTimeout)
            .setConnectionRequestTimeout(connectionRequestTimeout)
            .build();

    private static CloseableHttpClient httpClient = HttpClients.custom()
            .setMaxConnTotal(maxConnTotal)
            .setMaxConnPerRoute(maxConnPerRoute)
            .setDefaultRequestConfig(requestConfig)
            .build();

    /**
     *
     * @param url
     * @param map   参数map
     * @return JSON格式字符串返回
     */
    public static String getForObject(String url, Map<String, String> map) {
        StringBuffer sb = new StringBuffer(url);
        List<NameValuePair>  paramList = new ArrayList<NameValuePair>();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        String retStr = "";
        try {
            //组装uri
            URI uri = new URIBuilder(url).setParameters(paramList).build();
            HttpGet httpGet = new HttpGet(uri);
            //执行请求
            HttpResponse response = httpClient.execute(httpGet);
            int code = response.getStatusLine().getStatusCode();
            //判断请求是否成功
            if (code == HttpStatus.SC_OK) {
                //获取返回的实体
                HttpEntity entity = response.getEntity();
                //获取返回的内容
                retStr = EntityUtils.toString(entity, Charsets.UTF_8);

            } else {
                //logger.error("HttpPoolClient#getForObject.code=", code);
                throw new RuntimeException(String.valueOf(code));
            }
        } catch (MalformedURLException me) {
            //TODO logger.error();
        } catch (URISyntaxException ue) {
            //TODO logger.error();
        } catch (IOException ioe) {
            //TODO logger.error();
        } catch (Exception e) {
            //TODO logger.error();
        }
        return retStr;
    }
}
