package com.github.liuxboy.jdk.source.code.pattern.proxy;

/**
 * Package: com.github.liuxboy.jdk.source.code.pattern.proxy <br>
 * Author: liuchundong <br>
 * Date: 2017/8/1 <br>
 * Time: 11:03 <br>
 * Desc：代理模式使用代理对象完成用户请求，屏蔽用户对真实对象的访问。
 * 主题接口：定义代理类和真实主题的公共对外方法，也是代理类代理真实主题的方法；
 * 真实主题：真正实现业务逻辑的类；
 * 代理类：用来代理和封装真实主题；
 * 客户端：使用代理类和主题接口完成一些工作。
 * <p>
 * 代理模式使用场景：
 * 1、远程代理，也就是为一个对象在不同的地址空间提供局部代表，这样可以隐藏一个对象存在于不同地址空间的事实。
 * 比如说WebService，当我们在应用程序的项目中加入一个Web引用，引用一个WebService，
 * 此时会在项目中声称一个WebReference的文件夹和一些文件，这个就是起代理作用的，这样可以让那个客户端程序调用代理解决远程访问的问题；
 * 2、虚拟代理，是根据需要创建开销很大的对象，通过它来存放实例化需要很长时间的真实对象。这样就可以达到性能的最优化，
 * 比如打开一个网页，这个网页里面包含了大量的文字和图片，但我们可以很快看到文字，但是图片却是一张一张地下载后才能看到，
 * 那些未打开的图片框，就是通过虚拟代里来替换了真实的图片，此时代理存储了真实图片的路径和尺寸；
 * 3、安全代理，用来控制真实对象访问时的权限。一般用于对象应该有不同的访问权限的时候；
 * 4、指针引用，是指当调用真实的对象时，代理处理另外一些事。比如计算真实对象的引用次数，这样当该对象没有引用时，可以自动释放它，
 * 或当第一次引用一个持久对象时，将它装入内存，或是在访问一个实际对象前，检查是否已经释放它，以确保其他对象不能改变它。
 * 这些都是通过代理在访问一个对象时附加一些内务处理；
 * 5、延迟加载，用代理模式实现延迟加载的一个经典应用就在Hibernate框架里面。当Hibernate加载实体Bean时，
 * 并不会一次性将数据库所有的数据都装载。默认情况下，它会采取延迟加载的机制，以提高系统的性能。
 * Hibernate中的延迟加载主要分为属性的延迟加载和关联表的延时加载两类。实现原理是使用代理拦截原有的getter方法，
 * 在真正使用对象数据时才去数据库或者其他第三方组件加载实际的数据，从而提升系统性能。
 * </p>
 */
public class ProxyClient {
    public static void main(String[] args) {
        IDBQuery q = new DBQueryProxy(); //使用代里
        System.out.println(q.request()); //在真正使用时才创建真实对象
    }
}

interface IDBQuery {
    String request();
}

class DBQuery implements IDBQuery {
    public DBQuery() {
        try {
            Thread.sleep(1000);//假设数据库连接等耗时操作
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String request() {
        // TODO Auto-generated method stub
        return "request string";
    }
}

class DBQueryProxy implements IDBQuery {
    private DBQuery real = null;

    @Override
    public String request() {
        // TODO Auto-generated method stub
        //在真正需要的时候才能创建真实对象，创建过程可能很慢
        if (real == null) {
            real = new DBQuery();
        }//在多线程环境下，这里返回一个虚假类，类似于 Future 模式
        return real.request();
    }
}
