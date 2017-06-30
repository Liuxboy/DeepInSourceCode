package com.github.liuxboy.interview.code;

/**
 * Package: com.github.liuxboy.interview.code <br>
 * Author: liuchundong <br>
 * Date: 2017/6/22 <br>
 * Time: 13:06 <br>
 * Desc: 程序设置三个线程，线程A,B,C，三个线程分别打印a,b,c，怎样能实现打印出来的结果是
 * abcabcabc........
 */
public class PrintABC {
    private static Boolean isThreadA = true;
    private static Boolean isThreadB = false;
    private static Boolean isThreadC = false;

    public static void main(String[] args) {
        final PrintABC abc = new PrintABC();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (abc) {
                    while (!isThreadA) {
                        try {
                            abc.wait();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + "A");
                    isThreadA = false;
                    isThreadB = true;
                    isThreadC = false;
                    abc.notifyAll();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (abc) {
                    while (!isThreadB) {
                        try {
                            abc.wait();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + "B");
                    isThreadA = false;
                    isThreadB = false;
                    isThreadC = true;
                    abc.notifyAll();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (abc) {
                    while (!isThreadC) {
                        try {
                            abc.wait();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + "C");
                    isThreadA = true;
                    isThreadB = false;
                    isThreadC = false;
                    abc.notifyAll();
                }
            }
        }).start();
    }
}
