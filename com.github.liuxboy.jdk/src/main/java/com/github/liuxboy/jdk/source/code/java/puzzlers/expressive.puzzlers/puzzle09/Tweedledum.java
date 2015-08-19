package com.github.liuxboy.jdk.source.code.java.puzzlers.expressive.puzzlers.puzzle09;

public class Tweedledum {
    public static void main(String[] args) {
        //作如下声明，使得第一个表达式合法，第二个表达式不合法
        short x = 0;
        short i = 0;
        x += i;     // Must be LEGAL，里面包含了隐式的类型转换
        //x = x + i;  // Must be ILLEGAL
        x = (short) (x + i);  // 显式类型转换

        short y = 0;
        int j = 0;
        y += j;     //LEGAL
        //y = y + j;  //ILLEGAL，右边表达式结果值为int型，但是左边变量是short,
        j = j + y;  //LEGAL，右边表达式结果值为int型，左边亦是int型
    }
}
