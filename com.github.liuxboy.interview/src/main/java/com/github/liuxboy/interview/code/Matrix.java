package com.github.liuxboy.interview.code;

/**
 * Package: com.github.liuxboy.interview.code <br>
 * Author: liuchundong <br>
 * Date: 2017/7/22 <br>
 * Time: 10:29 <br>
 * Desc: 矩阵相关算法
 */
public class Matrix {

    public static void main(String[] args) {
        int[][] matrix_2 = new int[][]{
                {1, 2},
                {3, 4}
        };
        int[][] matrix_3 = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] matrix_4 = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        int[][] matrix_5 = new int[][]{
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };

        printMatrix(matrix_5);
        rotateMatrix(matrix_5, 0, 4);
        printMatrix(matrix_5);
    }

    /**
     * 顺时针旋转矩阵一步
     *
     * @param matrix
     */
    /*
    将一个n*n方阵沿中心顺时针旋转一步，比如：
    n=2时，旋转前：
    1 2
    3 4
    旋转后：
    3 1
    4 2

    n=3时，旋转前：
    1 2 3
    4 5 6
    7 8 9
    旋转后：
    4 1 2
    7 5 3
    8 9 6

    n=4时，旋转前：
    1   2   3   4
    5   6   7   8
    9   10  11  12
    12  13  14  15
    旋转后(注意内外两圈)：
    5   1   2   3
    9   10  6   4
    12  11  7   8
    13  14  15  12
    */
    private static void rotateMatrix(int[][] matrix, int upperLeft, int lowerRight) {
        if (lowerRight - upperLeft < 2) {
            return;
        }
        if (lowerRight - upperLeft > 2 ) {
            rotateMatrix(matrix, upperLeft + 1, lowerRight - 1);
        }
        int temp = matrix[upperLeft][upperLeft];    //将左上角的值赋值给临时变量;
        for (int i = upperLeft; i < lowerRight; i++) {
            matrix[i][upperLeft] = matrix[i + 1][upperLeft];        //行操作，下往上
        }
        for (int j = upperLeft; j < lowerRight; j++) {
            matrix[lowerRight][j] = matrix[lowerRight][j + 1];      //列操作，右往左
        }
        for (int i = lowerRight; i > upperLeft; i--) {
            matrix[i][lowerRight] = matrix[i - 1][lowerRight];      //行操作，上往下
        }
        for (int j = lowerRight; j > upperLeft; j--) {
            matrix[upperLeft][j] = matrix[upperLeft][j - 1];        //列操作，左往右
        }
        matrix[upperLeft][upperLeft + 1] = temp;    //原矩阵右一位
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {           //行
            for (int j = 0; j < matrix[0].length; j++) {    //列
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n---\n");
    }
}
