package com.github.liuxboy.jdk.source.code.interview.java.constantpool;

import com.github.liuxboy.jdk.source.code.interview.java.other.Other;

/**
 * Created with IntelliJ IDEA13.0
 * Author:刘春东
 * E-mail:liuchundong2008@gamil.com
 * Date:14-8-8
 * Time:下午2:44
 * Version:0.1
 */
public class ConstantPool {
    public static void classOfPoolAndHeap(){
        int i0 = 1;
        Integer i1 = new Integer(1);
        Integer i2 = new Integer(1);
        Integer i3 = 1;
        Integer i4 = 1;
        Integer i5 = new Integer(1);
        Integer i6 = Integer.valueOf(1);
        //
        System.out.println(i0 == i1);
        //i1和i2分别位于堆中不同的内存空间
        System.out.println(i1 == i2);
        //i3和i4指向常量池中同一个内存空间
        System.out.println(i3 == i4);
        //i1和i3分别位于堆中不同的内存空间
        System.out.println(i1 == i3);
        System.out.println(i0 == i3);
        //i4与i5不指向不同空间，但数学运算在栈中完成，
        //JVM会先对i5进行拆箱，再运算，其实比较的就是
        //两数的值
        System.out.println(i3 == i4 + i5);
        System.out.println(i3 == i6);
        //同上
        System.out.println(i1 == i2 + i5);
    }

    public static void wrapOfPrimitiveType(){
        //5种整形的包装类Byte,Short,Integer,Long,Character的对象，
        //在值小于127时可以使用常量池
        Integer i1=127;
        Integer i2=127;
        System.out.println(i1==i2);     //输出true
        //值大于127时，不会从常量池中取对象
        Integer i3=128;
        Integer i4=128;
        System.out.println(i3==i4);     //输出false
        //Boolean类也实现了常量池技术
        Boolean bool1=true;
        Boolean bool2=true;
        System.out.println(bool1==bool2);//输出true
        //浮点类型的包装类没有实现常量池技术
        Double d1=1.0;
        Double d2=1.0;
        Float f1 = 2.0f;
        Float f2 = 2.0f;
        System.out.println(d1 == d2);     //输出false
        System.out.println(f1 == f2);     //输出false
    }

    public static void stringPool(){
        //s1,s2分别位于堆中不同空间
        String s1 = new String("hello");
        String s2 = new String("hello");
        System.out.println(s1 == s2);//输出false
        //s3,s4位于池中同一空间
        String s3 = "hello";
        String s4 = "hello";
        System.out.println(s3 == s4);//输出true
    }

    public static void complexStringPool(){
        String hello = "Hello", lo = "lo";
        //在同包同类下，引用自同一String对象.
        System.out.print((hello == "Hello") + " ");
        //在同包不同类下，引用自同一String对象.
        System.out.print((Other.hello == hello) + " ");
        //在不同包不同类下，引用自同一String对象.
        System.out.print((com.github.liuxboy.jdk.source.code.interview.java.other.Other.hello == hello) + " ");
        //在编译成.class能识别为同一字符串的，自动优化成常量，放入常量池，所以也引用自同一对象.
        System.out.print((hello == ("Hel"+"lo")) + " ");
        //在运行时创建的字符串具有独立的内存地址，所以不引用自同一对象.
        System.out.print((hello == ("Hel"+lo)) + " ");
        //String的intern()方法会查找在常量池中是否存在一份equal相等的字符串，如果有，
        //则返回一个引用，没有则添加自己的字符串进入常量池，注意，只是字符串部分，所以这时会存在2份拷贝，
        //常量池部分被String类私有持有并管理，自己的那份按对象生命周期继续使用.
        System.out.println(hello == ("Hel"+lo).intern());
    }

    public static void main(String[] args){
        System.out.println("堆中不同对象与常量池中对象:");
        classOfPoolAndHeap();
        System.out.println("基本类型包装类实现的常量池对象:");
        wrapOfPrimitiveType();
        System.out.println("字符串实现了常量池对象:");
        stringPool();
        System.out.println("字符串的复杂常量池对象:");
        complexStringPool();
    }
}