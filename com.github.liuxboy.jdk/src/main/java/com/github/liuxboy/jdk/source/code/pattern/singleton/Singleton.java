package com.github.liuxboy.jdk.source.code.pattern.singleton;

/**
 * Created with IntelliJ IDEA13.0
 * Author:刘春东
 * E-mail:liuchundong2008@gamil.com
 * Date:puzzle14-7-25
 * Time:上午10:26
 * Version:0.1
 */

/**
 * 单例模式:
 * 这个设计模式主要目的是想在整个系统中只能出现一个类的实例
 * 1.私有（private）的构造函数，表明这个类是不可能形成实例了。这主要是怕这个类会有多个实例。
 * 2.即然这个类是不可能形成实例，那么，我们需要一个静态的方式让其形成实例：getSingleton()。
 *   注意这个方法是在new自己，因为其可以访问私有的构造函数，所以他是可以保证实例被创建出来的。
 * 3.在getInstance()中，先做判断是否已形成实例，如果已形成则直接返回，否则创建实例。
 * 4.所形成的实例保存在自己类中的私有成员中。
 * 5.我们取实例时，只需要使用Singleton.getSingleton()就行了。
 * -----------From CoolShell
 */
//写法一：最常见也最容易理解，lazy-loading懒汉模式
public class Singleton {
    private static Singleton instance = null;
    private Singleton(){}
    public static Singleton getSingleton(){
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}

/**写法二：lazy-loading懒汉模式，同步处理getSingleton，
 * 同步开销很大，浪费计算资源，但是很多情况下，不需要同步处理
 */
class SycSingleton {
    private static SycSingleton instance = null;
    private SycSingleton(){}
    public static synchronized SycSingleton getSingleton(){
        if (instance == null){
            instance = new SycSingleton();
        }
        return instance;
    }
}

/**写法三：hungry-loading懒汉模式，直接在初始化时完成单例创建
 * 这种方式基于classloder机制避免了多线程的同步问题，不过，instance在类装载时就实例化，
 * 虽然导致类装载的原因有很多种，在单例模式中大多数都是调用getSingleton方法，
 * 但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，这时候初始化instance显然没有达到lazy loading的效果。
 */
class HungrySingleton {
    private static HungrySingleton instance = new HungrySingleton();
    private HungrySingleton(){}
    public static HungrySingleton getSingleton(){
        return instance;
    }
}

//写法四：hungry-loading懒汉模式，直接在初始化时完成单例创建
class HungrySingletonPlus {
    private static HungrySingletonPlus instance = null;
    static {
        instance = new HungrySingletonPlus();
    }
    private HungrySingletonPlus(){}
    public static HungrySingletonPlus getSingleton(){
        return instance;
    }
}


















