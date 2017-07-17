package com.github.liuxboy.interview.code;

/**
 * Created In www.jdpay.com
 *
 * @author wyliuchundong
 * @version 1.0.0
 * @date 2015/7/29 8:27
 * @comment StaticTest
 */
public class StaticTest {
    private static int x = 100;
    // static field是属于类变量，是全局的，只加载一次，而不是实例变量，所以无论多少个实例对其操作，
    // 都是针对同一变量，所以下面的结果是加了三次，减了一次，都是对StaticTest.x进行操作的
    public static void main(String args[]) {
        StaticTest hs1 = new StaticTest();
        hs1.x++;
        StaticTest hs2 = new StaticTest();
        hs2.x++;
        hs1 = new StaticTest();
        hs1.x++;
        StaticTest.x--;
        System.out.println("x=" + x);
    }
}
