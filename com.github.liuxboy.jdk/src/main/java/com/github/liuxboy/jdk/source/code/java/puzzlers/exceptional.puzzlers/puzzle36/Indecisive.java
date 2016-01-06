package com.github.liuxboy.jdk.source.code.java.puzzlers.exceptional.puzzlers.puzzle36;

public class Indecisive {
    public static void main(String[] args) {
        System.out.println(decision());
    }

    static boolean decision() {
        try {
            return true;
        } finally {
            return false;
        }
    } 
}
