package com.github.liuxboy.jdk.source.code.concurrent.newthread;

/**
 * Created In www.jdpay.com
 *
 * @author wyliuchundong
 * @version 1.0.0
 * @date 2015/9/3 19:34
 * @comment ThreadTest
 */
public class ThreadTest extends Thread {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            new Thread("" + i) {
                public void run() {
                    System.out.println("Thread: " + getName() + " running");
                }
            }.start();
        }
    }
}
