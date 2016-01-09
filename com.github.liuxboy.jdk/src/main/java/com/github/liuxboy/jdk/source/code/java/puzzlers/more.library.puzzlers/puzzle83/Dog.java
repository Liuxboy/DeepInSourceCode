package com.github.liuxboy.jdk.source.code.java.puzzlers.more.library.puzzlers.puzzle83;

public class Dog extends Exception {
    public static final Dog INSTANCE = new Dog();
    private Dog() { }

    public String toString() {
        return "Woof";
    }
}
