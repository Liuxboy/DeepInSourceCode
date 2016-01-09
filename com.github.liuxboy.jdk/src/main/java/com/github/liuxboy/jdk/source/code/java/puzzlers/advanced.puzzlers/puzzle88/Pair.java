package com.github.liuxboy.jdk.source.code.java.puzzlers.advanced.puzzlers.puzzle88;

import java.util.*;

public class Pair<T> {
    private final T first;
    private final T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T first() {
        return first;
    }
    public T second() {
        return second;
    }
    public List<String> stringList() {
        return Arrays.asList(String.valueOf(first),
                             String.valueOf(second));
    }

    public static void main(String[] args) {
        Pair p = new Pair<Object>(23, "skidoo");
        System.out.println(p.first() + " " + p.second());
        for (Object s : p.stringList())
            System.out.print(s + " ");
    }
}
