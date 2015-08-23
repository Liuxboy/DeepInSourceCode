package com.github.liuxboy.jdk.source.code.java.puzzlers.expressive.puzzlers.puzzle10;

public class Tweedledee {
    public static void main(String[] args) {
        // + 运算符，是Java唯一重载过的运算符，如果参数+运算的参数有String类型，会默认变成连接符，将两边参数连成一个字符串
        // += 加法复合赋值运算符，先进行加法，再赋值
        Object x = "Hello World";
        String i= " Babe";
        x = x + i;  // Must be LEGAL
        //x += i;     // Must be ILLEGAL
    }
}
