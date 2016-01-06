package com.github.liuxboy.jdk.source.code.java.puzzlers.classy.puzzlers.puzzle54;

public class Null {
    public static void greet() {
        System.out.println("Hello world!");
    }

    public static void main(String[] args) {
        ((Null) null).greet();
    } 
}
