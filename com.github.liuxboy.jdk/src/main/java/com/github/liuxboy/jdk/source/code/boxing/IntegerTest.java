package com.github.liuxboy.jdk.source.code.boxing;

/**
 * Created In www.jdpay.com
 *
 * @author wyliuchundong
 * @version 1.0.0
 * @date 2015/7/29 9:59
 * @comment IntegerTest
 */
public class IntegerTest {
    public static void main(String[] args) {
        //对于基本类型的包装类，编译器自动拆
        Integer it1 = 59;
        int it2 = 59;
        Integer it3 = Integer.valueOf(59);
        Integer it4 = new Integer(59);
        System.out.println(it1 == it2);
        System.out.println(it1 == it3);
        System.out.println(it1 == it4);
        System.out.println(it2 == it3);
        System.out.println(it2 == it4);
        System.out.println(it3 == it4);
    }
}
