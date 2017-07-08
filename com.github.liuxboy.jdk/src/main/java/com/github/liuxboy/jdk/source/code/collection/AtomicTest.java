package com.github.liuxboy.jdk.source.code.collection;

import sun.misc.Unsafe;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Package: com.github.liuxboy.jdk.source.code.collection <br>
 * Author: liuchundong <br>
 * Date: 2017/7/5 <br>
 * Time: 09:58 <br>
 * Desc:
 */
public class AtomicTest implements Serializable {
    private byte _byte;
    private short _short;
    private int _int;
    private long _long;

    private float _float;
    private double _double;

    private boolean _boolean;
    private char _char;

    private String _string;

    public static void main(String[] args) {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            Unsafe unsafe = (Unsafe) f.get(null);
            //查找field在对象实例中的相对位置
            long _byte = unsafe.objectFieldOffset(AtomicTest.class.getDeclaredField("_byte"));
            long _short = unsafe.objectFieldOffset(AtomicTest.class.getDeclaredField("_short"));
            long _int = unsafe.objectFieldOffset(AtomicTest.class.getDeclaredField("_int"));
            long _long = unsafe.objectFieldOffset(AtomicTest.class.getDeclaredField("_long"));
            long _float = unsafe.objectFieldOffset(AtomicTest.class.getDeclaredField("_float"));
            long _double = unsafe.objectFieldOffset(AtomicTest.class.getDeclaredField("_double"));
            long _boolean = unsafe.objectFieldOffset(AtomicTest.class.getDeclaredField("_boolean"));
            long _char = unsafe.objectFieldOffset(AtomicTest.class.getDeclaredField("_char"));
            long _string = unsafe.objectFieldOffset(AtomicTest.class.getDeclaredField("_string"));

            System.out.println("_byte:" + _byte);
            System.out.println("_short:" + _short);
            System.out.println("_int:" + _int);
            System.out.println("_long:" + _long);

            System.out.println("_float:" + _float);
            System.out.println("_double:" + _double);
            System.out.println("_boolean:" + _boolean);
            System.out.println("_char:" + _char);
            System.out.println("_string:" + _string);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }
}
