package com.github.liuxboy.httpclient.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.Charsets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.HttpResponse;

import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MIME;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Package: com.github.liuxboy.httpclient.util <br>
 * Author: liuchundong <br>
 * Date: 2016-12-19 <br>
 * Time: 14:03:19 <br>
 * Desc: HttpClient池化请求帮助类，使用Charsets#UTF_8
 *
 * @see Charsets#UTF_8
 */
public class HttpPoolClientUtil {

    private static Log log = LogFactory.getLog(HttpPoolClientUtil.class);
    /**
     * 以下3个超时相关的参数如果未配置，默认为0，意味着无限大，就是一直阻塞等待!
     * @see org.apache.http.client.config.RequestConfig#socketTimeout
     * @see org.apache.http.client.config.RequestConfig#connectTimeout
     * @see org.apache.http.client.config.RequestConfig#connectionRequestTimeout
     */
    /**
     * 服务器返回数据(response)的时间，超过该时间抛出read timeout
     */
    private static int socketTimeout = 3000;    //单位：ms
    /**
     * 连接上服务器(握手成功)的时间，超出该时间抛出connect timeout
     */
    private static int connectTimeout = 3000;   //单位：ms
    /**
     * 从连接池中获取连接的超时时间，超过该时间未拿到可用连接，会抛出
     * org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool
     */
    private static int connectionRequestTimeout = 3000;    //单位：ms

    /**
     * 以下2个参数是关于请求池设置的
     * 注意：此两个参数至关重要，务必设值
     * @see PoolingHttpClientConnectionManager here
     * this.pool = new CPool(new InternalConnectionFactory(this.configData, connFactory), 2, 20, timeToLive, tunit);
     * 通过默认方式新建的话，默认最大连接数为上面CPool构造参数中的20，单个路由最大连接数为2，
     */

    /**
     * 最大连接数，整个连接池支撑的最大连接数，
     * !!具体设值需要根据实际系统部署结构进行最优调节!!
     */
    private static int maxConnTotal = 300;          //最大连接数
    /**
     * 每个路由(route)最大连接数，route可以理解为运行环境机器->目标机器的一条线路，
     * 用本工具请求www.baidu.com的和www.google.com的两个地址，那么他就会产生两个route。
     * !!具体设值需要根据实际部署结构进行最优调节!!
     */
    private static int maxConnPerRoute = 100;        //单个路由并发连接数

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
     * @param url 请求地址
     * @param obj 参数对象
     * @return may be {@code null}
     */
    public static String postForJson(String url, Object obj) {
        String content = JSON.toJSONString(obj);
        return postForObject(url, ContentType.APPLICATION_JSON, content);
    }

    /**
     * @param url      请求地址
     * @param mimeType example: {@link ContentType#APPLICATION_JSON#getMimeType()}
     * @param paraMap  参数对象
     * @return may be {@code null}
     */
    public static String postForObject(String url, String mimeType, Map<String, String> paraMap) {
        //默认APPLICATION_FORM_URLENCODED
        ContentType contentType = ContentType.create(
                ContentType.APPLICATION_FORM_URLENCODED.getMimeType()
                , Charsets.UTF_8);
        if (mimeType != null) {
            contentType = ContentType.create(mimeType, Charsets.UTF_8);
        }
        return postForObject(url, contentType, paraMap);
    }

    /**
     * @param url         请求地址
     * @param contentType example: {@link ContentType#APPLICATION_JSON}
     * @param paraMap     请求参数Map
     * @return may be {@code null}
     */
    public static String postForObject(String url, ContentType contentType, Map<String, String> paraMap) {
        if (MapUtils.isEmpty(paraMap)) {
            log.warn("HttpClient->postForObject map is empty");
            return null;
        }
        if (null == contentType) {
            contentType = ContentType.create(
                    ContentType.APPLICATION_FORM_URLENCODED.getMimeType()
                    , Charsets.UTF_8);
        }
        String retStr = null;
        try {
            //组装Entity
            List<NameValuePair> formParams = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> entry : paraMap.entrySet()) {
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            HttpEntity httpEntity = EntityBuilder.create()
                    .setContentType(contentType)
                    .setParameters(formParams).build();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(httpEntity);

            //返回处理器，处理异常，关闭流，管理连接等
            ResponseHandler<String> responseHandler = new JsonResponseHandler();
            //执行请求，并拿到结果
            retStr = httpClient.execute(httpPost, responseHandler);
        } catch (Exception e) {
            log.error("HttpPoolClientUtil.postForObject has error:", e);
        }
        return retStr;
    }

    /**
     * @param url         请求地址
     * @param contentType example: {@link ContentType#APPLICATION_JSON}
     * @param content     请求报文
     * @return may be {@code null}
     */
    public static String postForObject(String url, ContentType contentType, String content) {
        String retStr = null;
        try {
            //组装StringEntity
            HttpEntity httpEntity = new StringEntity(content, Charsets.UTF_8);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(httpEntity);
            if (null == contentType) {
                contentType = ContentType.create(
                        ContentType.APPLICATION_FORM_URLENCODED.getMimeType()
                        , Charsets.UTF_8);
            }
            httpPost.setHeader(MIME.CONTENT_TYPE, contentType.toString());
            //返回处理器，处理异常，关闭流，管理连接等
            ResponseHandler<String> responseHandler = new JsonResponseHandler();
            //执行请求，并拿到结果
            retStr = httpClient.execute(httpPost, responseHandler);
        } catch (Exception e) {
            log.error("HttpPoolClientUtil.postForObject has error:", e);
        }
        return retStr;
    }

    /**
     * @param url 请求地址
     * @param map 请求参数map
     * @return may be {@code null}
     */
    public static String getForObject(String url, final Map<String, String> map) {
        List<NameValuePair> paramList = null;
        if (map != null) {
            paramList = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        String retStr = null;
        try {
            //组装uri
            URI uri = CollectionUtils.isNotEmpty(paramList)
                    ? new URIBuilder(url).setParameters(paramList).build()
                    : new URIBuilder(url).build();
            HttpGet httpGet = new HttpGet(uri);
            //返回处理器，处理异常，关闭流，管理连接等
            ResponseHandler<String> responseHandler = new JsonResponseHandler();
            //执行请求，并拿到结果
            retStr = httpClient.execute(httpGet, responseHandler);
        } catch (Exception e) {
            log.error("HttpPoolClientUtil.getForObject has error:", e);
        }
        return retStr;
    }
}

/**
 * 返回处理器
 * 已处理异常与关闭流
 */
class JsonResponseHandler implements ResponseHandler<String> {
    @Override
    public String handleResponse(HttpResponse response) throws IOException {
        StatusLine statusLine = response.getStatusLine();
        HttpEntity entity = response.getEntity();
        String responseStr = null;
        if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
            throw new HttpResponseException(
                    statusLine.getStatusCode(),
                    statusLine.getReasonPhrase());
        }
        if (entity != null) {
            //字符串长度超过不超过Integer.MAX_VALUE;
            responseStr = EntityUtils.toString(entity, Charsets.UTF_8);
        }
        return responseStr;
    }
}
