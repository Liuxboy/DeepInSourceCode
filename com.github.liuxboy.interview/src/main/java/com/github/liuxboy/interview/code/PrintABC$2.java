package com.github.liuxboy.interview.code;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Package: com.github.liuxboy.jdk.source.code.concurrent.notify <br>
 * Author: liuchundong <br>
 * Date: 2017/6/21 <br>
 * Time: 11:25 <br>
 * Desc: 程序设置三个线程，线程A,B,C，三个线程分别打印a,b,c，怎样能实现打印出来的结果是
 * abcabcabc........
 */
public class PrintABC$2 {

    private static volatile int printFlag = 0;

    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    while (printFlag != 0) {
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + "打印A");
                    printFlag = 1;
                    condition.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    while (printFlag != 1) {
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + "打印B");
                    printFlag = 2;
                    condition.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    while (printFlag != 2) {
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + "打印C");
                    printFlag = 0;
                    condition.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }
}

