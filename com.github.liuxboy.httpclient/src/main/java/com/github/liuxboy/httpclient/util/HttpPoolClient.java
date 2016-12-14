package com.github.liuxboy.httpclient.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

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
    private static int maxConnTotal = 200;      //最大连接数
    /**
     * 单个路由并发连接数
     */
    private static int maxConnPerRoute = 50;    //并发连接数

    private static RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(socketTimeout)
            .setConnectTimeout(connectTimeout)
            .setConnectionRequestTimeout(connectionRequestTimeout)
            .build();

    private static CloseableHttpClient httpClient = HttpClients.custom()
            .setMaxConnTotal(maxConnTotal)
            .setMaxConnPerRoute(maxConnPerRoute)
            .setDefaultRequestConfig(requestConfig)
            .setConnectionManager(new PoolingHttpClientConnectionManager())
            .build();

    public static String getForObject() {
        //TODO
        return null;
    }
}
