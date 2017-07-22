package com.github.liuxboy.interview.code;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Package: com.github.liuxboy.interview.code <br>
 * Author: liuchundong <br>
 * Date: 2017/6/9 <br>
 * Time: 10:11 <br>
 * Desc:
 */
public class ArrayUtils {
    public static void main(String[] args) {
        String[] array = new String[]{"a", "b", "a", "a", "b"};
        System.out.println(arrayAlias(array));

        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        revertArray(a, 5);
        for (int i : a) {
            System.out.print(i + " ");
        }
    }

    /**
     * @param array
     * @return
     * @link /resources/rest/ArrayAlias.png
     */
    private static String arrayAlias(String[] array) {

        String[] alias = new String[array.length];

        Map<String, Integer> aliasMap = new HashMap<>();
        Integer count;
        String str = "";
        for (int i = 0; i < array.length; i++) {
            str = array[i];
            count = aliasMap.get(str);
            if (count == null) {
                alias[i] = array[i];
                aliasMap.put(str, 0);
            } else {
                alias[i] = array[i] + (count + 1);
                aliasMap.put(str, count + 1);
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int j = 0; j < alias.length; j++) {
            //最后一个，不用加逗号分割
            if (j == alias.length - 1) {
                stringBuffer.append(alias[j]);
                return stringBuffer.toString();
            }
            stringBuffer.append(alias[j]).append(",");
        }
        return stringBuffer.toString();
    }

    /**
     * 一个排好序的数组A，长度为n，现在将数组A从位置m(m<n，m已知)分开，并将两部分互换位置，设计一个O(n)的算法实现这样的倒置，只允许使用一个额外空间。
     * (另一种描述：数组循环右移位，每一步都是将数组尾移到数组头)
     * 分析:利用向量反转，ab两个向量，翻转公式：(a'b')'= ba，ab各自分别翻转，再整体反转得结果
     *
     * @param a
     * @param m
     */
    private static void revertArray(int[] a, int m) {
        if (m > a.length)
            return;
        //翻转(0,m)之间的数据
        for (int i = 0, j = m; i < j; i++, j--) {
            swap(a, i, j);
        }
        //翻转(m+1,n)之间的数据
        for (int i = m + 1, j = a.length - 1; i < j; i++, j--) {
            swap(a, i, j);
        }
        //整体翻转
        for (int i = 0, j = a.length - 1; i < j; i++, j--) {
            swap(a, i, j);
        }
    }

    private static void swap(int[] array, int a, int b) {
        int x = array[a];
        array[a] = array[b];
        array[b] = x;
    }
}

