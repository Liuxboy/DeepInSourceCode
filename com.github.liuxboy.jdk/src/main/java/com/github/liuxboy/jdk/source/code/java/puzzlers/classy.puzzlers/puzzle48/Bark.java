package com.github.liuxboy.jdk.source.code.java.puzzlers.classy.puzzlers.puzzle48;

class Dog {
    public static void bark() {
        System.out.print("woof ");
    }
}

class Basenji extends Dog {
    public static void bark() { }
}

public class Bark {
    public static void main(String args[]) {
        Dog woofer = new Dog();
        Dog nipper = new Basenji();
        woofer.bark();
        nipper.bark();
    }
}
