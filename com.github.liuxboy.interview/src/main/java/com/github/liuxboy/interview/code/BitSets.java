package com.github.liuxboy.interview.code;

import java.util.BitSet;

/**
 * Package: com.github.liuxboy.interview.code <br>
 * Author: liuchundong <br>
 * Date: 2017/7/19 <br>
 * Time: 17:57 <br>
 * Desc: 位集
 */
public class BitSets {

    public static void main(String[] args) {
        long a = Long.MIN_VALUE;
        long b = Long.MIN_VALUE;
        System.out.println((a + b) / 2 );
        System.out.println((a + b) >>> 1);
        System.out.println(a - (a - b) / 2);

        BitSet bitSet = new BitSet(64);
        bitSet.set(0, true);
        bitSet.set(50, true);
        bitSet.set(64, true);
        bitSet.set(65, true);
        System.out.println(bitSet.length());
        for (int i = 0; i < bitSet.length(); i++) {
            System.out.print(bitSet.get(i) + " ");
        }
    }
}
