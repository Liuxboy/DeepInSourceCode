package com.github.liuxboy.httpclient.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Package: com.github.liuxboy.httpclient.util <br>
 * Author: liuchundong <br>
 * Date: 2016/12/8 <br>
 * Time: 20:18 <br>
 * Desc:
 */
public class MultiRequestHttpClient {
    final static Map<String, String> map = new HashMap<String, String>() {
        {
            put("name", "lcd");
        }
    };
    public static void main(String[] args) {
        final String getUrl = "http://127.0.0.1:8080/services/greeting/getGreeting?name=lcd";
        for (int i = 0; i < 3000; i++) {
            new Thread() {
                @Override
                public void run() {
                    String resJson = HttpPoolClient.getForObject(getUrl, null);
                }
            }.start();
        }

        final String postUrl = "http://127.0.0.1:8080/services/greeting/postGreeting";
        for (int i = 0; i < 3000; i++) {
            new Thread() {
                @Override
                public void run() {
                    String resJson = HttpPoolClient.postForJson(postUrl, map);
                }
            }.start();
        }
    }
}
