package com.github.liuxboy.jdk.source.code.java.puzzlers.expressive.puzzlers.puzzle07;

public class CleverSwap {
    public static void main(String[] args) {
        int x = 1984;
        int y = 2001;
        x ^= y ^= x ^= y;
        System.out.println("x = " + x + "; y = " + y);
    }
}
