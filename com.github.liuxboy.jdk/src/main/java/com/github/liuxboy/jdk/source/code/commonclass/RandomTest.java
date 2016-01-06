package com.github.liuxboy.jdk.source.code.commonclass;

import java.util.Random;

/**
 * Created In www.jdpay.com
 *
 * @author wyliuchundong
 * @version 1.0.0
 * @date 2015/8/31 16:33
 * @comment RandomTest
 */
public class RandomTest {
    public static void main(String[] args) {
        //Math.random()产生一个[0.0,1.0)之间的数
        int a = (int)Math.random();
        int b = (int)(0 + Math.random() * (2 - 0 +1));
        System.out.println(a);
        System.out.println(b);

        Random random = new Random();
        //产生1到32位之间的任意整数
        int c = random.nextInt();
        System.out.println(c);
        Random random1 = new Random(10);
        int d = random1.nextInt();
        System.out.println(d);

    }
}
