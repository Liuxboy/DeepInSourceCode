package com.github.liuxboy.jdk.source.code.java.puzzlers.loopy.puzzlers.puzzle31;

public class GhostOfLooper {
    public static void main(String[] args) {
        // Place your declaration for i here
        int i = 0;
        while (i != 0)
            i >>>= 1;
    }
}
