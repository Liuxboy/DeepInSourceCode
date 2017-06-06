package com.github.liuxboy.jdk.source.code.concurrent.executor;

import java.util.concurrent.*;

/**
 * Package: com.github.liuxboy.jdk.source.code.concurrent.executor <br>
 * Author: liuchundong <br>
 * Date: 2017/3/13 <br>
 * Time: 14:21 <br>
 * Desc:
 */
public class ExecutorServiceTest {
    private static Executor executor;
    private static ExecutorService executorService;
    private static AbstractExecutorService abstractExecutorService;
    private static ThreadPoolExecutor threadPoolExecutor;
    private static Executors executors;
    public static void main(String[] args) {
        executorService = Executors.newSingleThreadExecutor();
        executorService = Executors.newCachedThreadPool();
        executorService = Executors.newFixedThreadPool(10);
        executorService = Executors.newScheduledThreadPool(10);
    }

}
