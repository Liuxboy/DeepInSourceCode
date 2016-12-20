import com.alibaba.fastjson.JSON;
import com.github.liuxboy.httpclient.util.HttpPoolClientUtil;
import org.apache.http.entity.ContentType;

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
        for (int i = 0; i < 2000; i++) {
            new Thread() {
                @Override
                public void run() {
                    String resJson = HttpPoolClientUtil.getForObject(getUrl, null);
                }
            }.start();
        }

        final String postUrl = "http://127.0.0.1:8080/services/greeting/postGreeting";
        for (int i = 0; i < 2000; i++) {
            new Thread() {
                @Override
                public void run() {
                    String resJson = HttpPoolClientUtil.postForJson(postUrl, map);
                    String resObj1 = HttpPoolClientUtil.postForObject(postUrl, ContentType.APPLICATION_JSON, map);
                    String resObj2 = HttpPoolClientUtil.postForObject(postUrl, ContentType.APPLICATION_JSON, JSON.toJSONString(map));
                    String resObj3 = HttpPoolClientUtil.postForObject(postUrl, ContentType.APPLICATION_JSON.getMimeType(), map);
                }
            }.start();
        }
    }
}
