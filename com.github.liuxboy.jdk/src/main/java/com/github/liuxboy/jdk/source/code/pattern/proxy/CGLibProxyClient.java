package com.github.liuxboy.jdk.source.code.pattern.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Package: com.github.liuxboy.jdk.source.code.pattern.proxy <br>
 * Author: liuchundong <br>
 * Date: 2017/8/15 <br>
 * Time: 15:51 <br>
 * Desc: 通过CGLib动态生成代理类
 */
public class CGLibProxyClient {
    public static void main(String[] args) {
        BookProxyLib cglib = new BookProxyLib();
        BookImpl bookCglib = (BookImpl) cglib.getInstance(new BookImpl());
        bookCglib.addBook();
    }
}

interface IBook {
    public void addBook();
}

/**
 * 这里并没有要求实现BookProxy接口
 */
class BookImpl {
    public void addBook() {
        System.out.println("增加图书的普通方法...");
    }
}

class BookProxyLib implements MethodInterceptor {
    private Object target;

    /**
     * 创建代理对象
     *
     * @param target
     * @return
     */
    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args,
                            MethodProxy methodProxy) throws Throwable {
        System.out.println("事务开始");
        methodProxy.invokeSuper(obj, args);
        System.out.println("事务结束");
        return null;
    }
}