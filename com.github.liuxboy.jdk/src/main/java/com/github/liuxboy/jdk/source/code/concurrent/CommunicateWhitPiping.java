package com.github.liuxboy.jdk.source.code.concurrent;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Package: com.github.liuxboy.jdk.source.code.concurrent <br>
 * Author: liuchundong <br>
 * Date: 2017/6/23 <br>
 * Time: 11:30 <br>
 * Desc:
 */
public class CommunicateWhitPiping {
    public static void main(String[] args) {
        /**
         * 创建管道输出流
         */
        PipedOutputStream pos = new PipedOutputStream();
        /**
         * 创建管道输入流
         */
        PipedInputStream pis = new PipedInputStream();
        try {
            /**
             * 将管道输入流与输出流连接 此过程也可通过重载的构造函数来实现
             */
            pos.connect(pis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * 创建生产者线程
         */
        Producer p = new Producer(pos);
        /**
         * 创建消费者线程
         */
        Consumer c = new Consumer(pis);
        /**
         * 启动线程
         */
        p.start();
        c.start();
    }
}

/**
 * 生产者线程(与一个管道输入流相关联)
 */
class Producer extends Thread {
    private PipedOutputStream pos;

    public Producer(PipedOutputStream pos) {
        this.pos = pos;
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("写入数据：" + i);
                pos.write(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 消费者线程(与一个管道输入流相关联)
 */
class Consumer extends Thread {
    private PipedInputStream pis;

    public Consumer(PipedInputStream pis) {
        this.pis = pis;
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("读出数据：" + pis.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
