package com.github.liuxboy.jdk.source.code.understanding.jvm;

import java.io.IOException;
import java.io.InputStream;

/**
 * Package: com.github.liuxboy.jdk.source.code.understanding.jvm <br>
 * Author: liuchundong <br>
 * Date: 2017/7/14 <br>
 * Time: 11:21 <br>
 * Desc: jstack监控以下场景
 */
public class ThreadDump {
    public static void main(String[] args) {
        //监控死循环
        for (;;) {
            int[] ints = new int[409600];
            System.out.println("Di-Da");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //监控阻塞
        //ExecutorService service = Executors.newFixedThreadPool(1);
        //service.execute(new TestTask());

        //监控死锁@see concurrent.book的chapter01

        //监控IO等待
        /*WaitIO waitIO = new WaitIO();
        try {
            waitIO.waitIO();
            System.out.println("over");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}

class TestTask implements Runnable {
    @Override
    public void run() {
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("over");
    }
}

class WaitIO {
    public void waitIO() throws IOException {
        InputStream inputStream = System.in;
        int i = inputStream.read();
        System.out.println("exit!" + i);
    }
}
