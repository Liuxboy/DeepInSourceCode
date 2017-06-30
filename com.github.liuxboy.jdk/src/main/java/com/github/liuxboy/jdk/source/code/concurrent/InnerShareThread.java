package com.github.liuxboy.jdk.source.code.concurrent;

/**
 * Package: com.github.liuxboy.jdk.source.code.concurrent <br>
 * Author: liuchundong <br>
 * Date: 2017/6/23 <br>
 * Time: 10:49 <br>
 * Desc:
 */
public class InnerShareThread {
    public static void main(String[] args) {
        Mythread mythread = new Mythread();
        mythread.getThread().start();
        mythread.getThread().start();
        mythread.getThread().start();
        mythread.getThread().start();
    }
}

class Mythread {
    int index = 0;
    //静态内部类共享index
    private class InnerThread extends Thread {
        public synchronized void run() {
            while (true) {
                System.out.println(Thread.currentThread().getName()
                        + "is running and index is " + index++);
            }
        }
    }

    public Thread getThread() {
        return new InnerThread();
    }
}