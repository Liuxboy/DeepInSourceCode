package com.github.liuxboy.jdk.source.code.java.puzzlers.expressive.puzzlers.puzzle06;

public class Multicast {
    /**
     * 窄的整型转换成较宽的整型时符号扩展规则：如果最初的数值类型是有符号的，那么就执行符号扩展（即如果符号位为1，则扩展为1，如果为零，则扩展为0）；
     * 如果它是char，那么不管它将要被提升成什么类型，都执行零扩展。因为char是无符号的，也即使全是整数，且java中的char是2个byte长度
     *
     * 规则：要为清晰表达你的意图而努力
     */
    public static void main(String[] args) {
        //-1在计算机中用补码方式表示：1(符号位)1111111(1的反码11111110 + 1 = 11111111)
        System.out.println((int) (char) (byte) -1);  //65535
        System.out.println((int) (short) (byte) -1); //65535
        System.out.println((byte) -1);               //1
        System.out.println((byte)0x90 == 0x90);
        //如果想转换一个char类型字符为更宽的类型，且不想有符号扩展，可以用掩码或者注释说明
        char c = 'C';
        int i0 = c & 0xffff;
        int i1 = c & 0xffff; //不带符号转型
        //如果想转换一个char类型字符为更宽的类型，希望它带符号扩展，先转化成short(16 位)
        int i2 = (short) c;
        //如果想把一个byte值b转化成char，且不想有符号扩展，则必须用掩码，这是一种通用做法，无需注释
        byte b = 2;
        char c0 = (char) (b & 0xff);
        //如果想把一个byte值b转化成char，且想有符号扩展，则直接转换，加一个注释
        char c1 = (char) b; //带符号转型
    }
}
