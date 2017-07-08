package com.github.liuxboy.interview.code;


/**
 * Package: com.github.liuxboy.interview.code <br>
 * Author: liuchundong <br>
 * Date: 2017/7/6 <br>
 * Time: 11:34 <br>
 * Desc:
 */
public class DoubleDimensionalArray {
    public static void main(String[] args) {
        int[][] array = {
                {1, 2, 8, 9},
                {4, 7, 10, 13}
        };

        System.out.println(array[1][0]);
        System.out.println(array[0][1]);

        /*
        System.out.println(findNumber(6, array));
        System.out.println(findNumber(3, array));
        System.out.println(findNumber(7, new int[0][0]));
        System.out.println(findNumber(7, null));

        System.out.println(find(6, array));
        System.out.println(find(3, array));
        System.out.println(find(7, new int[0][0]));
        System.out.println(find(7, null));
        */


    }

    /**
     * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * @param target
     * @param array
     * @return
     */
    private static boolean findNumber_0(int target, int[][] array) {
        if (array.length == 0)
            return false;
        int left, mid, right, length = array[0].length;
        for (int i = 0; i < array.length; i++) {
            if (array[i][0] == target || array[i][length - 1] == target)
                return true;
            if (array[i][0] < target && target < array[i][length - 1]) {
                left = 0;
                right = length - 1;
                while (left <= right) {
                    mid = right - (right - left) / 2;
                    if (target == array[i][mid]) {
                        return true;
                    } else if (target < array[i][mid])
                        right = mid - 1;
                    else
                        left = mid + 1;
                }
            }
        }
        return false;
    }

    private static boolean findNumber_1(int target, int[][] array) {
        int len = array.length - 1;
        int i = 0;
        while ((len >= 0) && (i < array[0].length)) {
            if (array[len][i] > target) {
                len--;
            } else if (array[len][i] < target) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }
}
