package com.github.liuxboy.interview.code;

/**
 * Package: com.github.liuxboy.interview.code <br>
 * Author: liuchundong <br>
 * Date: 2017/7/18 <br>
 * Time: 18:36 <br>
 * Desc: 全排列
 * 全排列的生成法通常有以下几种：
 * 字典序法
 * 递增进位数制法
 * 递减进位数制法
 * 邻位交换法
 * 递归类算法
 *
 */
public class Arrange {
    public static void main(String[] args) {
        int length = 3;
        int start = 0;
        int list[] = new int[length];
        for (int j = 0; j < length; j++) {
            list[j] = j + 1;
        }
        permulation(list, start, length);

    }
    private static void permulation(int[] a, int start, int length) {
        int i;
        if (start == length) {
            for (i = 0; i < length; i++) {
                System.out.print(a[i] + " ");
            }
            System.out.println();
        } else {
            for (i = start; i < length; i++) {
                swap(a, start, i);
                permulation(a, start + 1, length);
                swap(a, start, i);
            }
        }
    }

    private static void swap(int[] a, int start, int i) {
        int temp = a[start];
        a[start] = a[i];
        a[i] = temp;
    }
}
