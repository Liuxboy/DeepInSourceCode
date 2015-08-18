package com.github.liuxboy.jdk.source.code.java.puzzlers.expressive.puzzlers.puzzle02;

import java.math.BigDecimal;

public class Change {
    /**
     * 要精确计算浮点数，最好用new BigDecimal("String")这样的构造参数。
     * 比较两个浮点数的大小，要使用BigDecimal的compareTo方法。
     *
     * @param args
     */
    public static void main(String args[]) {
        System.out.println(2.00 - 1.10);
        System.out.println(new BigDecimal("2.0").subtract(new BigDecimal("1.10")));// 0.9
        System.out.println(BigDecimal.valueOf(2.0).subtract(BigDecimal.valueOf(1.10)));// 0.9
        System.out.println(new BigDecimal(0.1));    //double转String，精度丢失
        System.out.println(new BigDecimal("0.1"));
        System.out.println(new BigDecimal(0.1).compareTo(new BigDecimal(0)));
        System.out.println(new BigDecimal(0.1).compareTo(new BigDecimal(-0.1)));
        System.out.println(new BigDecimal(0.1).compareTo(new BigDecimal("-0.1")));
        System.out.println(new BigDecimal(0.1).compareTo(new BigDecimal(0.100)));
        System.out.println(new BigDecimal(0).compareTo(new BigDecimal(0.00)));
        System.out.println(new BigDecimal(0).compareTo(new BigDecimal(0)));
        System.out.println(new BigDecimal(0).compareTo(new BigDecimal("0.00")));
        System.out.println(new BigDecimal(0).compareTo(new BigDecimal("0.00")));
    }
}
