package com.github.liuxboy.jdk.source.code.java.puzzlers.expressive.puzzlers.puzzle01;

public class Oddity {
    public static boolean isOdd(int i) {
        return i % 2 == 1;
    }

    public static void main(String[] args) {
        //判断奇数用 i % 2 != 0
        System.out.println(3 % 2 == 1);
        System.out.println(-3 % 2 == 1);

        System.out.println(3 % 2 != 0);
        System.out.println(-3 % 2 != 0);
    }
}
