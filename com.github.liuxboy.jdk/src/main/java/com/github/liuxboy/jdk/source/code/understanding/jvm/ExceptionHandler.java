package com.github.liuxboy.jdk.source.code.understanding.jvm;

/**
 * Created In www.jdpay.com
 *
 * @author wyliuchundong
 * @version 1.0.0
 * @date 2015/8/7 14:20
 * @comment ExceptionHandler
 */
public class ExceptionHandler {
    public static int inc(int d) {
        int x;
        try {
            x = 1;
            return x/d;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
            return x;
            //return x;
        }
    }
    public static void main(String[] args) {
        inc(0);
    }
}
