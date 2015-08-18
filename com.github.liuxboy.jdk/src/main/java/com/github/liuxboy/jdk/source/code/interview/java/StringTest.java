package com.github.liuxboy.jdk.source.code.interview.java;

/**
 * Created with IntelliJ IDEA13.0
 * Author:刘春东
 * E-mail:liuchundong2008@gamil.com
 * Date:puzzle14-8-1
 * Time:下午5:50
 * Version:0.1
 */
public class StringTest {
    static String say(String v){
        return v = v + "b";
    }
    static int say(int v){
        return v = v + 2;
    }
    static String say(StringBuffer v){
        v = v.append("b");
        return v.toString();
    }
    public static void main(String[] args){
        String v = "a";
        say(v);
        System.out.println(v);

        int i = 1;
        say(i);
        System.out.println(i);

        StringBuffer vf = new StringBuffer("a");
        say(vf);
        System.out.println(vf);
    }
}
