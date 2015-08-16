package com.github.liuxboy.jdk.source.code.java.puzzlers.expressive.puzzlers;

/**
 * Created In www.jdpay.com
 *
 * @author wyliuchundong
 * @version 1.0.0
 * @date 2015/8/14 14:47
 * @comment Puzzler01_Oddity
 */
public class Puzzler01_Oddity {
    public static void main(String[] args) {
        //判断奇数用 i % 2 != 0
        System.out.println(3 % 2 == 1);
        System.out.println(-3 % 2 == 1);

        System.out.println(3 % 2 != 0);
        System.out.println(-3 % 2 != 0);
    }
}
