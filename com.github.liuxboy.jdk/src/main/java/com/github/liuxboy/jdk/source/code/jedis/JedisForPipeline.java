package com.github.liuxboy.jdk.source.code.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Package: com.github.liuxboy.jdk.source.code.jedis <br>
 * Author: liuchundong <br>
 * Date:  2019-9-26<br>
 * Time: 13:50 <br>
 * Desc:
 */
public class JedisForPipeline {

    private static void testNormalOperation(Jedis jedis) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            jedis.set(String.valueOf(i), String.valueOf(i));
        }
        long end = System.currentTimeMillis();
        System.out.println("the jedis normal operation cost total time is:" + (end - start) + "ms");
    }

    public static void testPipelineOperation(Jedis jedis) throws InterruptedException {

        Pipeline pipe = jedis.pipelined(); // 先创建一个pipeline的链接对象
        long start_pipe = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            pipe.set(String.valueOf(i), String.valueOf(i));
        }
        pipe.sync(); // 获取所有的response
        long end_pipe = System.currentTimeMillis();
        System.out.println("the pipe total time is:" + (end_pipe - start_pipe));

        BlockingQueue<String> logQueue = new LinkedBlockingQueue<>();
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            logQueue.put("i=" + i);
        }
        long stop = System.currentTimeMillis();
        System.out.println("the BlockingQueue total time is:" + (stop - begin));
    }

    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        jedis.auth("123");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());

        testNormalOperation(jedis);

        try {
            testPipelineOperation(jedis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
