package com.github.liuxboy.jdk.source.code.java.puzzlers.exceptional.puzzlers.puzzle37;

import java.io.IOException;

public class Arcane1 {
    public static void main(String[] args) {
        try {
            System.out.println("Hello world");
        } catch(IOException e) {
            System.out.println("I've never seen println fail!");
        }
    }
}
