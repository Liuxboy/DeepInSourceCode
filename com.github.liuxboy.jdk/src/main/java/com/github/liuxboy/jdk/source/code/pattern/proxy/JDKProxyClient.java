package com.github.liuxboy.jdk.source.code.pattern.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * Package: com.github.liuxboy.jdk.source.code.pattern.proxy <br>
 * Author: liuchundong <br>
 * Date: 2017/8/15 <br>
 * Time: 14:17 <br>
 * Desc: 通过JDK自带的代理技术实现动态代理
 * 主要是Proxy.newProxyInstance，以及实现InvocationHandler的invoke方法
 * 重点是要理解invoke方法中的三个参数的意义
 *
 * JDK动态代理。具体有如下四步骤：
 * 1、通过实现InvocationHandler接口创建自己的调用处理器；
 * 2、通过为Proxy类指定ClassLoader对象和一组interface来创建动态代理类；
 * 3、通过反射机制获得动态代理类的构造函数，其唯一参数类型是调用处理器接口类型；
 * 4、通过构造函数创建动态代理类实例，构造时调用处理器对象作为参数被传入。
 */
public class JDKProxyClient {
    public static void main(String[] args) throws InterruptedException {
        final IAnimal animal = new Dog();
        Object proxy = Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                animal.getClass().getInterfaces(),
                (proxy1, method, args1) -> {
                    try {
                        /*
                        A:
                          这里调用InvocationHandler.invoke的第一个参数proxy1的任何实例方法(toString,hashCode,equals)
                          都会引起死循环
                          Debug可以得出结论：proxy1与proxy是同一个对象，如果再调用proxy1的实例方法，则又会invoke拦截
                          又进入本方法
                          System.out.println("代理类：" + proxy1);
                         */
                        System.out.println("被拦截的方法：" + method.getName());
                        return method.invoke(animal, args1);
                    } catch (IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        return null;
                    } catch (IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        return null;
                    } catch (InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        return null;
                    }
                });

        if (proxy instanceof IAnimal) {
            System.out.println("the proxyObj is an animal!");
        } else {
            System.out.println("the proxyObj isn't an animal!");
        }

        if (proxy instanceof Dog) {
            System.out.println("the proxyObj is a dog!");
        } else {
            System.out.println("the proxyObj isn't a dog!");
        }

        IAnimal animalProxy = (IAnimal) proxy;
        animalProxy.info();
        System.out.println(animalProxy.hashCode());
        System.out.println(animalProxy.toString());
        System.out.println(animalProxy.equals("xx"));
        System.out.println(animalProxy.getClass()); //不会被invoke代理
        System.out.println(animalProxy.getClass().getName());
    }
}

interface IAnimal {
    void info();
}

class Dog implements IAnimal {
    public void info() {
        System.out.println("I am a dog!");
    }
}




