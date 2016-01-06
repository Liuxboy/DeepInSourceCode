package com.github.liuxboy.jdk.source.code.concurrent.creat.newthread;

/**
 * Created In www.jdpay.com
 *
 * @author wyliuchundong
 * @version 1.0.0
 * @date 2015/7/puzzle13 puzzle20:40
 * @comment RunnableTest
 */
/**
 * Thread sleep和wait区别
 * @author DreamSea
 * 2012-1-puzzle15
 */
public class RunnableTest implements Runnable {
    int number = 10;

    public void firstMethod() throws Exception {
        synchronized (this) {
            number += 100;
            System.out.println(number);
        }
    }

    public void secondMethod() throws Exception {
        synchronized (this) {
            /**
             * (休息2S,阻塞线程)
             * 以验证当前线程对象的机锁被占用时,
             * 是否被可以访问其他同步代码块
             */
            Thread.sleep(2000);
            this.wait(2000);
            number *= 200;
        }
    }

    @Override
    public void run() {
        try {
            firstMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        RunnableTest runnableTest = new RunnableTest();
        Thread thread = new Thread(runnableTest);
        thread.start();
        runnableTest.secondMethod();
        System.out.println("number=" + runnableTest.number);
    }
}
