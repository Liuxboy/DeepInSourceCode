package com.github.liuxboy.jdk.source.code.java.puzzlers.expressive.puzzlers.puzzle06;

public class Multicast {
    /**
     * 窄的整型转换成较宽的整型时符号扩展规则：如果最初的数值类型是有符号的，那么就执行符号扩展（即如果符号位为1，则扩展为1，如果为零，则扩展为0）；
     * 如果它是char，那么不管它将要被提升成什么类型，都执行零扩展。
     */
    public static void main(String[] args) {
        System.out.println((int) (char) (byte) -1);
    }
}
