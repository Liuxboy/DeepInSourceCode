package com.github.liuxboy.jdk.source.code.java.puzzlers.puzzlers.with.character.puzzle12;

public class Abc {
    public static void main(String[] args) {
        String letters = "ABC";
        char[] numbers = { '1', '2', '3' };
        System.out.println(numbers);
        /**下面代码numbers重载了Object的toString()方法
         public String toString() {
            return getClass().getName() + "@" + Integer.toHexString(hashCode());
         }
         */
        System.out.println(letters + " easy as " + numbers);
        System.out.println(letters + " easy as " + numbers.toString());
        /** 以下方法重载了valueOf(char[] args)
        public static String valueOf(char data[]) {
            return new String(data);
        }
        */
        System.out.println(letters + " easy as " + String.valueOf(numbers));
    }
}
