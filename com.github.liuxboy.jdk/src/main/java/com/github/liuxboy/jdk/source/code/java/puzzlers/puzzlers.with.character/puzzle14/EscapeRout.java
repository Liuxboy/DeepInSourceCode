package com.github.liuxboy.jdk.source.code.java.puzzlers.puzzlers.with.character.puzzle14;

public class EscapeRout {
  public static void main(String[] args) {
    // \u0022 is the Unicode escape for double-quote (")
    /**
     *  Java对在字符串字面量中的Unicode转义字符没有做任何特殊处理，编译器在将程序解析成各种符号之前，
     *  会先将Unicode转义字符转换成它所表示的字符，如\u0022表示一个双引号["]，所以在这种情况下，最好用
     *  转义字符\，如：\'、\n、\t、\\等。
     *  在字符串和字符字面量中优选选择的是转义字符序列，而不是Unicode转义字符，更不要用Unicode转义字符来表示ASCII字符。
     */
    System.out.println("a\u0022.length() + \u0022b".length());
    System.out.println("a".length() + "b".length());
    System.out.println("a\".length() + \"b".length());
    System.out.println("a.length() + b.".length());
  }
}
