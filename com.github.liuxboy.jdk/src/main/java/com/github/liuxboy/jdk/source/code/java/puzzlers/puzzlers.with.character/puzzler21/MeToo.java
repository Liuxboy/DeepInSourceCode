package com.github.liuxboy.jdk.source.code.java.puzzlers.puzzlers.with.character.puzzler21;

import java.io.File;

public class MeToo {
    public static void main(String[] args) {
    System.out.println(MeToo.class.getName().
        replaceAll("\\.", File.separator) + ".class");
  }
}
