package understanding.the.jvm.chapter02;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Package: understanding.jvm.chapter02 <br>
 * Author: liuchundong <br>
 * Date: 2017/8/18 <br>
 * Time: 18:02 <br>
 * Desc:
 * JVM Options:-Xmx20M -XX:MaxDirectMemorySize=10M
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe unsafe = null;
        try {
            unsafe = (Unsafe) field.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
