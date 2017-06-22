package com.github.liuxboy.jdk.source.code.java.puzzlers.classier.puzzlers.puzzle66;

class Base {
    public String className = "Base";
}

class Derived extends Base {
    public String className = "Derived";
}

public class PrivateMatter {
    public static void main(String[] args) {
        System.out.println(new Derived().className);
    }
}
