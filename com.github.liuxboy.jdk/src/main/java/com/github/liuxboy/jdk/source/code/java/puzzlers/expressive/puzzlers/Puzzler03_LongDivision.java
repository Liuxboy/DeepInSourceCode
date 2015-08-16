package com.github.liuxboy.jdk.source.code.java.puzzlers.expressive.puzzlers;

/**
 * Created In www.jdpay.com
 *
 * @author wyliuchundong
 * @version 1.0.0
 * @date 2015/8/14 16:43
 * @comment Puzzler03_LongDivision
 */
public class Puzzler03_LongDivision {
    public static void main(String[] args) {
        /**
         * 前一个计算工会溢出，因为这个计算式完全是以int运算来执行的，
         * 只有在运算完成之后，其结果才被提升为long，而此时已经溢出了
         * 类似的double和float运算时也要注意，不过java默认浮点都是双精度double的
         */
        final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
        final long MICROS_PER_DAY_WRONG = 24 * 60 * 60 * 1000 * 1000;
        final long MICROS_PER_DAY_RIGHT = 24L * 60 * 60 * 1000 * 1000;
        System.out.println(MILLIS_PER_DAY);
        System.out.println(MICROS_PER_DAY_WRONG);
        System.out.println(MICROS_PER_DAY_RIGHT);
        System.out.println(MICROS_PER_DAY_RIGHT / MILLIS_PER_DAY);

    }

}
