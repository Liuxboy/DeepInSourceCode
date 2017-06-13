package com.github.liuxboy.jdk.source.code.java.puzzlers.loopy.puzzlers.puzzle34;

public class Count {
    public static void main(String[] args) {
        final int START = 2000000000;
        int count = 0;
        for (float f = START; f < START + 50; f++)
            count++;
        System.out.println(count);
    }
}
