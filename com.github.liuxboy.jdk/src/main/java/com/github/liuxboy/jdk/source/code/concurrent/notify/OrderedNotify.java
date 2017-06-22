package com.github.liuxboy.jdk.source.code.concurrent.notify;

/**
 * Package: com.github.liuxboy.jdk.source.code.concurrent.notify <br>
 * Author: liuchundong <br>
 * Date: 2017/6/21 <br>
 * Time: 11:25 <br>
 * Desc: 程序设置三个线程，线程A,B,C，三个线程分别打印a,b,c，怎样能实现打印出来的结果是
 * abcabcabc........
 */
public class OrderedNotify {

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
