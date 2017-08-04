package com.github.liuxboy.jdk.source.code.pattern.singleton;

/**
 * Created with IntelliJ IDEA13.0
 * Author:刘春东
 * E-mail:liuchundong2008@gamil.com
 * Date:2017-07-23
 * Time:上午10:26
 * Version:0.1
 * <p>
 * 单例模式:
 * 这个设计模式主要目的是想在整个系统中只能出现一个类的实例
 * 1.私有（private）的构造函数，表明这个类是不可能形成实例了。这主要是怕这个类会有多个实例。
 * 2.私有（private）、静态的成员变量，保存自己的实例。
 * 3.即然这个类是不可能形成实例，那么，我们需要一个静态的方式让其形成实例：getSingleton()。
 *   注意这个方法是在new自己，因为其可以访问私有的构造函数，所以他是可以保证实例被创建出来的。
 * 3.在getInstance()中，先做判断是否已形成实例，如果已形成则直接返回，否则创建实例。
 * 5.我们取实例时，只需要使用Singleton.getSingleton()就行了。
 */

/**
 * 写法一：lazy-loading懒汉模式
 * 最常见也最容易理解，非线程安全，仅限于单线程访问，多线程访问会有问题
 */

public class Singleton {
    private static Singleton instance = null;

    private Singleton() {
    }

    public static Singleton getSingleton() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

/**
 * 写法二：lazy-loading懒汉模式
 * 同步处理getSingleton，同步开销很大，浪费计算资源，但是很多情况下，不需要同步处理
 */
class SynchronizedSingleton {
    private static SynchronizedSingleton instance = null;

    private SynchronizedSingleton() {
    }

    public static synchronized SynchronizedSingleton getSingleton() {
        if (instance == null) {
            instance = new SynchronizedSingleton();
        }
        return instance;
    }
}

/**
 * 写法三：lazy-loading懒汉模式
 * Double-Check
 */
class DoubleCheckSingleton {
    private volatile static DoubleCheckSingleton instance = null;

    private DoubleCheckSingleton() {
    }

    public static DoubleCheckSingleton getSingleton() {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                instance = new DoubleCheckSingleton();
            }
        }
        return instance;
    }
}

/**
 * 写法四：lazy-loading饿汉模式
 * 通过静态内部类加载实例，保证线程安全
 */
class InnerStaticSingleton {
    private static class LazyHolder {
        private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }

    private InnerStaticSingleton() {
    }

    public static InnerStaticSingleton getInstance() {
        return LazyHolder.INSTANCE;
    }
}

/**
 * 写法五：lazy-loading懒汉模式
 */
enum EnumSingleton {
    INSTANCE;

    public void dosomething() {
    }
}

/**
 * 写法六：hungry-loading饿汉模式
 * 直接在初始化时完成单例创建，这种方式基于classloder机制避免了多线程的同步问题，
 * 不过，instance在类装载时就实例化，虽然导致类装载的原因有很多种，在单例模式中大多数都是调用getSingleton方法，
 * 但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，这时候初始化instance显然没有达到lazy loading的效果。
 */
class StaticInitSingleton {
    private static StaticInitSingleton instance = new StaticInitSingleton();

    private StaticInitSingleton() {
    }

    public static StaticInitSingleton getSingleton() {
        return instance;
    }
}

/**
 * 写法七：hungry-loading饿汉模式
 * 直接在静态代码块完成单例创建
 * 通过类加载保证单例完成
 */
class StaticBlockSingleton {
    private static StaticBlockSingleton instance = null;

    static {
        instance = new StaticBlockSingleton();
    }

    private StaticBlockSingleton() {
    }

    public static StaticBlockSingleton getSingleton() {
        return instance;
    }
}