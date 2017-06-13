package com.github.liuxboy.jdk.source.code.java.puzzlers.classier.puzzlers.puzzle71;

import java.util.Arrays;

class ImportDuty {
    public static void main(String[] args) {
        printArgs(1, 2, 3, 4, 5);
    }

    static void printArgs(Object... args) {
        System.out.println(Arrays.toString(args));
    }


}
