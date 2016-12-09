package com.github.liuxboy.httpclient.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Package: com.github.liuxboy.httpclient.util <br>
 * Author: liuchundong <br>
 * Date: 2016/12/8 <br>
 * Time: 20:18 <br>
 * Desc:
 */
public class MultiRequestHttpClient {

    public static void main(String[] args) {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();
        // URIs to perform GETs on
        String[] urisToGet = {
                "http://www.domain1.com/",
                "http://www.domain2.com/",
                "http://www.domain3.com/",
                "http://www.domain4.com/"
        };
        // create a thread for each URI
        GetThread[] getThreads = new GetThread[urisToGet.length];
        for (int i = 0; i < getThreads.length; i++) {
            HttpGet httpget = new HttpGet(urisToGet[i]);
            getThreads[i] = new GetThread(httpClient, httpget);
        }
        // start the threads
        for (int j = 0; j < getThreads.length; j++) {
            getThreads[j].start();
        }
        // join the threads
        for (int j = 0; j < getThreads.length; j++) {
            try {
                getThreads[j].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String[] urisToPost = {
                "http://www.domain1.com/",
                "http://www.domain2.com/",
                "http://www.domain3.com/",
                "http://www.domain4.com/"
        };
        PostThread[] postThreads = new PostThread[urisToGet.length];
        for (int i = 0; i < getThreads.length; i++) {
            HttpPost httpPost = new HttpPost(urisToGet[i]);
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());

            List<BasicNameValuePair> formParams = new ArrayList<BasicNameValuePair>();
            /*
            for (Map.Entry<String, String> entry : map.entrySet()) {
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }TODO post装参数
            */
            HttpEntity httpentity = new UrlEncodedFormEntity(formParams, Charset.defaultCharset());
            httpPost.setEntity(httpentity);
            postThreads[i] = new PostThread(httpClient, httpPost);
        }
        // start the threads
        for (int j = 0; j < getThreads.length; j++) {
            postThreads[j].start();
        }
        // join the threads
        for (int j = 0; j < getThreads.length; j++) {
            try {
                postThreads[j].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static class GetThread extends Thread {
        private final CloseableHttpClient httpClient;
        private final HttpContext context;
        private final HttpGet httpget;

        GetThread(CloseableHttpClient httpClient, HttpGet httpget) {
            this.httpClient = httpClient;
            this.context = HttpClientContext.create();
            this.httpget = httpget;
        }

        @Override
        public void run() {
            try {
                CloseableHttpResponse response = httpClient.execute(httpget, context);
                try {
                    HttpEntity entity = response.getEntity();

                } finally {
                    response.close();
                }
            } catch (ClientProtocolException ex) {
                // Handle protocol errors
            } catch (IOException ex) {
                // Handle I/O errors
            }
        }
    }

    static class PostThread extends Thread {
        private final CloseableHttpClient httpClient;
        private final HttpContext context;
        private final HttpPost httpPost;

        PostThread(CloseableHttpClient httpClient, HttpPost httpPost) {
            this.httpClient = httpClient;
            this.context = HttpClientContext.create();
            this.httpPost = httpPost;
        }

        @Override
        public void run() {
            try {
                CloseableHttpResponse response = httpClient.execute(httpPost, context);
                try {
                    HttpEntity entity = response.getEntity();

                } finally {
                    response.close();
                }
            } catch (ClientProtocolException ex) {
                // Handle protocol errors
            } catch (IOException ex) {
                // Handle I/O errors
            }
        }
    }
}
