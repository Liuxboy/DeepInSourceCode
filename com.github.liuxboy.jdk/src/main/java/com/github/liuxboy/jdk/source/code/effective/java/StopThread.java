package com.github.liuxboy.jdk.source.code.effective.java;

import java.util.concurrent.TimeUnit;

/**
 * Created In www.jdpay.com
 *
 * @author wyliuchundong
 * @version 1.0.0
 * @date 2015/9/6 17:34
 * @comment StopThread
 */

public class StopThread {
    private static boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!stopRequested) {
                    i++;
                    System.out.println("运行次数：" + i);
                }
            }
        });
        Thread backgroundThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!stopRequested) {
                    i++;
                    System.out.println("运行次数：" + i);
                }
            }
        });
        backgroundThread1.start();
        backgroundThread2.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}
