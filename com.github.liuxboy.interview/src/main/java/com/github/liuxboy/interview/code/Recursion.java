package com.github.liuxboy.interview.code;

/**
 * Package: com.github.liuxboy.interview.code <br>
 * Author: liuchundong <br>
 * Date: 2017/7/10 <br>
 * Time: 14:16 <br>
 * Desc: 递归
 */
public class Recursion {
    public static void main(String[] args) {
        Recursion recursion = new Recursion();
        for (int i = 0; i < 10; i++) {
            System.out.print(recursion.fibonacci(i) + " ");
        }
        System.out.println("\n------");
        for (int i = 0; i < 10; i++) {
            System.out.print(recursion.fibonacci1(i) + " ");
        }
        System.out.println("\n");
    }

    /**
     * Fibonacci递归实现
     *          |   0                       (n = 0)
     * Fib(n)=  |   1                       (n = 1)
     *          |   Fib(n-1) + Fib(n-2)     (n > 1)
     *
     * @param n - 自然数
     * @return
     */
    private int fibonacci(int n) {
        if (n < 2)
            return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * Fibonacci循环实现
     *          |   0                       (n = 0)
     * Fib(n)=  |   1                       (n = 1)
     *          |   Fib(n-1) + Fib(n-2)     (n > 1)
     *
     * @param n - 自然数
     * @return
     */
    private int fibonacci1(int n) {
        if (n < 2)
            return n;
        int a = 0, b = 1, c = 0;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}
