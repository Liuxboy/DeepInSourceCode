package com.github.liuxboy.jdk.source.code.java.puzzlers.expressive.puzzlers.puzzle08;

public class DosEquis {
    public static void main(String[] args) {
        char x = 'X';
        int i = 0;
        float j = 0.0f;
        // 第二个操作数是char类型，但第三个操作数是常量0，所以整个选择表达式是char类型，其重载的方法如下：
        // public void println(char x) {
        //    synchronized (this) {
        //        print(x);
        //        newLine();
        //    }
        //}
        System.out.println(true  ? x : 0);  //X
        // 第二个操作数是int类型变量，第三个是char类型，所以整个选择表达式进行了类型提升，其重载的方法如下：
        // public void println(int x) {
        //synchronized (this) {
        //    print(x);
        //    newLine();
        //  }
        //}
        System.out.println(false ? i : x);  //88
        // 道理同上
        System.out.println(false ? j : x);  //88.0
    }
}
