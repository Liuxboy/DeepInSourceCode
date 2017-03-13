package com.github.liuxboy.jdk.source.code.commonclass;

/**
 * Package: com.github.liuxboy.jdk.source.code.commonclass <br>
 * Author: liuchundong <br>
 * Date: 2017/3/13 <br>
 * Time: 15:59 <br>
 * Desc:
 */
public class InitializationOrderTest {
    public static void main(String[] args) {
        new Child();
    }
}

class Parent {
    public Parent() {
        System.out.println("Parent Constructor");
    }

    {
        System.out.println("Parent initialisation block before static block");
    }

    static {
        System.out.println("Parent static block");
    }

    {
        System.out.println("Parent initialisation block after static block");
    }
}

class Child extends Parent {
    {
        System.out.println("Child initialisation block before static block");
    }

    static {
        System.out.println("Child static block");
    }

    {
        System.out.println("Child initialisation block after static block");
    }

    public Child() {
        System.out.println("Child Constructor");
    }
}

class Example {
    public static int step_1 = step(1);
    public int step_6 = step(6);

    static {
        step(2);
    }

    public Example() {
        step(7);
    }

    // Just for demonstration purposes:
    public static int step(int step) {
        System.out.println("Step " + step);
        return step;
    }
}

class ExampleSubclass extends Example {
    public static int step_3 = step(3);
    public int step_8 = step(8);

    static {
        step(4);
    }

    public ExampleSubclass() {
        step(9);
    }

    public static void main(String[] args) {
        step(5);
        new ExampleSubclass();
    }
}