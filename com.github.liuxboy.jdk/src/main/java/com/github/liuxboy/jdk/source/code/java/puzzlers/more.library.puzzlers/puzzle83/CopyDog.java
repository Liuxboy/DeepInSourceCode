package com.github.liuxboy.jdk.source.code.java.puzzlers.more.library.puzzlers.puzzle83;

public class CopyDog {
    public static void main(String[] args) {
        Dog newDog = ??? ; // You figure out what to put here

        // This line should print false
        System.out.println(newDog == Dog.INSTANCE);

        // This line should print "Woof"
        System.out.println(newDog);
    }
}
