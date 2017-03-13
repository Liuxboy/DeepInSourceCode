import com.alibaba.fastjson.JSON;
import com.github.liuxboy.httpclient.util.HttpPoolClientUtil;
import org.apache.http.entity.ContentType;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Package: com.github.liuxboy.httpclient.util <br>
 * Author: liuchundong <br>
 * Date: 2016/12/8 <br>
 * Time: 20:18 <br>
 * Desc:
 */
public class MultiRequestHttpClient {
    private static Map testMap = new HashMap<String, String>() {
        {
            put("name", "lcd");
        }
    };

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2000,
                4000, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        for (int i = 0; i < 2000; i++) {
            threadPoolExecutor.execute(
                    new Runnable() {
                        public void run() {
                            HttpPoolClientUtil.getForObject("http://127.0.0.1:8080/services/greeting/getGreeting?name=lcd", null);
                            HttpPoolClientUtil.postForJson("http://127.0.0.1:8080/services/greeting/postGreeting", testMap);
                            HttpPoolClientUtil.postForObject("http://127.0.0.1:8080/services/greeting/postGreeting", ContentType.APPLICATION_JSON, JSON.toJSONString(testMap));
                        }
                    });
        }
    }
}
