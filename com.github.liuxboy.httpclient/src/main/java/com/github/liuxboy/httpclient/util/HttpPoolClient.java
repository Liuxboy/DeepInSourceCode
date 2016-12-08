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
    private static int socketTimeout = 2000;    //socket建立超时时长
    private static int connectTimeout = 2000;   //connect建立超时时长
    private static int connectionRequestTimeout = 10000;    //连接请求超时时长
    private static int maxConnTotal = 200;      //最大连接数
    private static int maxConnPerRoute = 50;    //并发支持数

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
