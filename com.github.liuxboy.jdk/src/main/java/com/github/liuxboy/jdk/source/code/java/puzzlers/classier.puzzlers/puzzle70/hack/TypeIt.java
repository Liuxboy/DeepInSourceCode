package com.github.liuxboy.jdk.source.code.java.puzzlers.classier.puzzlers.puzzle70.hack;

import com.github.liuxboy.jdk.source.code.java.puzzlers.classier.puzzlers.puzzle70.click.CodeTalk;

public class TypeIt {
    private static class ClickIt extends CodeTalk {
        void printMessage() {
            System.out.println("Hack");
        }
    }

    public static void main(String[] args) {
        ClickIt clickit = new ClickIt();
        clickit.doIt();
    }
}
