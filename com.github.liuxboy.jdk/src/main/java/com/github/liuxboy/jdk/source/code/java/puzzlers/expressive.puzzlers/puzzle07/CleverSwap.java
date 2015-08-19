package com.github.liuxboy.jdk.source.code.java.puzzlers.expressive.puzzlers.puzzle07;

public class CleverSwap {
    //不要在一个表达式中对一个相同变量赋值两次
    public static void main(String[] args) {
        int x = 1984;   //(0x07c0)
        int y = 2001;   //(0x07d1)
        //x ^= y;       //(x = 17(二进制10001); y = 2001)
        //y ^= x ^= y;  //(x = 17; y = 1984)
        //x ^= y ^= x ^= y;   //(x = 0; y = 1984)
        //System.out.println("x = " + x + "; y = " + y);

        //以上表达式(x ^= y ^= x ^= y)分解为：
        int tmp1 = x;
        int tmp2 = y;
        int tmp3 = x ^ y;
        x = tmp3;
        y = tmp2 ^ tmp3;
        x = tmp1 ^ y;
        //上式可以修改为 x ^= (x ^= (y ^= x)) ^y，但是理解起来过于复杂，并不比多加一个临时变量来得清楚，
        //所以不推荐这样使用
    }
}
