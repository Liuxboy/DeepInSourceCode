package com.github.liuxboy.jdk.source.code.java.puzzlers.expressive.puzzlers.puzzle04;

import java.util.ArrayList;
import java.util.List;

public class Elementary {
    /**
     * 最好的办法，使用长整形字面量时，用大写L，别用小写的l
     * 如：1253L
     * 最好也不要用l来做变量名
     */
    public static void main(String[] args) {
        System.out.println(12345 + 5432l);  //最后这个数是5432l(el)不是54321；17777
        System.out.println(12345 + 54321);  //66666
        System.out.println(12345 + 5432L);  //17777

        List<String> l = new ArrayList<String>();
        l.add("Foo");
        System.out.println(1);
        System.out.println(l);      //小写的l，而不是1
    }
}
