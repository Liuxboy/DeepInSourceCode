package org.arcie.dong.interview.java;

/**
 * Created with IntelliJ IDEA13.0
 * User:刘春东
 * E-mail:liuchundong2008@gmail.com
 * Date:14-7-7
 * Time:下午5:20
 * Version:0.1
 */
//求素数
public class Prime {
    //传统方法
    public static boolean isPrime1(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {//从2开始看该数是否能整除2...num之间的数，如是则不是素数
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    //筛选法
    public static void isPrime2(int n){
        int[] array = new int[n];
        for (int i = 2; i < n; i++) {
            array[i] = i;
        }
        for (int i = 2; i < n; i++) {
            if (array[i] != 0) {
                int j, temp;
                temp = array[i];
                for (j = 2 * temp; j < n; j = j + temp) {
                    array[j] = 0;
                }
                System.out.println(array[i] + " is a Prime $");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("调用第一个通用法求素数:");
        for (int j = 2; j <= 20; j++) {
            if (isPrime1(j)) {
                System.out.println(j + " is a Prime￥");
            }
        }
        System.out.println("调用第二个筛选法求素数:");
        isPrime2(20);
    }
}
