package com.github.liuxboy.jdk.source.code.java.puzzlers.expressive.puzzlers.puzzle05;

public class JoyOfHex {
    /**
     * 整数的数字字面常量 都是int型的，不管它是几进制
     * 十进制字面量都是正数，如果想要有十进制的负数，在前面加一个"-"即可；
     * 十六进制或八进制字面常量可就不一定是正数或负数，是正还是负，则要根据当前情况看：
     * 如果十六进制和八进制字面常量的最高位被设置成了1，那么它们就是负。
     */
    public static void main(String[] args) {
        //十六进制0xcafebabe等价于十进制中的-889275714
        //且"0xcafebabe"(32位)被提升类型为"0xffffffffcafebabeL"(64位)
        System.out.println(Long.toHexString(0x100000000L + 0xcafebabe));
        System.out.println(Long.toHexString(0x100000000L + (-889275714)));
        //如果要解决这种默认类型提升带来的问题，最好办法就是操作数都明确地声明为长类型，如下：
        //分别是0x0000000100000000和 0x00000000cafebabe
        System.out.println(Long.toHexString(0x100000000L + 0xcafebabeL));

        System.out.println(0x81);//129
        System.out.println(0x8001);//32769
        System.out.println(0x70000001);//1879048193
        System.out.println(0x80000001);//-2147483647（默认int型，32位）
        System.out.println(0x80000001L);//2147483649(转换成long型，64位)
        System.out.println(0xcafebabe);//-889275714,十六进制c(其二进制为1100)，最高位是1，表示负数，
        System.out.println(0xcafebabeL);
    }
}
