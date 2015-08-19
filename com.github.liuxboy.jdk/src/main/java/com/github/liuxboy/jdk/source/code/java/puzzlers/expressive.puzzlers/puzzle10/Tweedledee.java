package com.github.liuxboy.jdk.source.code.java.puzzlers.expressive.puzzlers.puzzle10;

public class Tweedledee {
    public static void main(String[] args) {
        // Put your declarations for x and i here
        char i= 'A';
        int x = 0;
        x = x + i;  // Must be LEGAL
        x += i;     // Must be ILLEGAL
    }
}
