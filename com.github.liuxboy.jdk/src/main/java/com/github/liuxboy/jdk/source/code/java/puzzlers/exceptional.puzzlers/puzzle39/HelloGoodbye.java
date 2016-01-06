package com.github.liuxboy.jdk.source.code.java.puzzlers.exceptional.puzzlers.puzzle39;

public class HelloGoodbye {
    public static void main(String[] args) {
        try {
            System.out.println("Hello world");
            System.exit(0);
        } finally {
            System.out.println("Goodbye world");
        }
    } 
}
