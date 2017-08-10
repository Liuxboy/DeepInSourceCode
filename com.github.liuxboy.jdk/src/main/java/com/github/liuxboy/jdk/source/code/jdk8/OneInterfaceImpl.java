package com.github.liuxboy.jdk.source.code.jdk8;

/**
 * Package: com.github.liuxboy.jdk.source.code.jdk8 <br>
 * Author: liuchundong <br>
 * Date: 2017/8/1 <br>
 * Time: 17:27 <br>
 * Desc:
 */
interface OneInterface {
    default void aDefaultMethod() {
        System.out.println("Java8的接口能实现一个默认方法");
    }

    default void anotherDefaultMethod() {
        System.out.println("Java8的接口能实现多个默认方法");
    }

    static void aStaticMethod() {
        System.out.println("Java8的接口能实现一个静态方法");
    }

    static void anotherStaticMethod() {
        System.out.println("Java8的接口能实现多个静态方法");
    }
}

public class OneInterfaceImpl implements OneInterface {
    public static void main(String[] args) {
        OneInterfaceImpl oneInterface = new OneInterfaceImpl();
        oneInterface.aDefaultMethod();
        oneInterface.anotherDefaultMethod();
        OneInterface.aStaticMethod();
        OneInterface.anotherStaticMethod();
    }

}
