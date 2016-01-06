package com.github.liuxboy.jdk.source.code.java.puzzlers.exceptional.puzzlers.puzzle37;

public class Arcane2 {
    public static void main(String[] args) {
        try {
            // If you have nothing nice to say, say nothing
        } catch(Exception e) {
            System.out.println("This can't happen");
        }
    }
}
