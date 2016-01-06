package com.github.liuxboy.jdk.source.code.java.puzzlers.exceptional.puzzlers.puzzle44;

public class Strange1 {
    public static void main(String[] args) {
        try {
            Missing m = new Missing();
        } catch (java.lang.NoClassDefFoundError ex) {
            System.out.println("Got it!");
        }
    }
}
