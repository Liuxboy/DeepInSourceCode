package com.github.liuxboy.jdk.source.code.pattern.strategy;

/**
 * Package: com.github.liuxboy.jdk.source.code.pattern <br>
 * Author: liuchundong <br>
 * Date: 2017/7/22 <br>
 * Time: 13:03 <br>
 * Desc: 策略模式
 Context(应用场景):
          需要使用ConcreteStrategy提供的算法。
          内部维护一个Strategy的实例。
          负责动态设置运行时Strategy具体的实现算法。
          负责跟Strategy之间的交互和数据传递。
 StrategyTest(抽象策略类)：
          定义了一个公共接口，各种不同的算法以不同的方式实现这个接口，Context使用这个接口调用不同的算法，
          一般使用接口或抽象类实现。
 ConcreteStrategy(具体策略类)：
          实现了Strategy定义的接口，提供具体的算法实现。

 策略模式的优点
 　　（1）策略模式提供了管理相关的算法族的办法。策略类的等级结构定义了一个算法或行为族。
        恰当使用继承可以把公共的代码移到父类里面，从而避免代码重复。
 　　（2）使用策略模式可以避免使用多重条件(if-else)语句。多重条件语句不易维护，
        它把采取哪一种算法或采取哪一种行为的逻辑与算法或行为的逻辑混合在一起，统统列在一个多重条件语句里面，
        比使用继承的办法还要原始和落后。
 策略模式的缺点
 　　（1）客户端必须知道所有的策略类，并自行决定使用哪一个策略类。这就意味着客户端必须理解这些算法的区别，
        以便适时选择恰当的算法类。换言之，策略模式只适用于客户端知道算法或行为的情况。
 　　（2）由于策略模式把每个具体的策略实现都单独封装成为类，如果备选的策略很多的话，那么对象的数目就会很可观。

 测试模式适用场景
     做面向对象设计的，对策略模式一定很熟悉，因为它实质上就是面向对象中的继承和多态，在看完策略模式的通用代码后，
     我想，即使之前从来没有听说过策略模式，在开发过程中也一定使用过它吧？至少在在以下两种情况下，大家可以考虑使用策略模式，
     几个类的主要逻辑相同，只在部分逻辑的算法和行为上稍有区别的情况。
     有几种相似的行为，或者说算法，客户端需要动态地决定使用哪一种，那么可以使用策略模式，将这些算法封装起来供客户端调用。
     策略模式是一种简单常用的模式，我们在进行开发的时候，会经常有意无意地使用它，一般来说，策略模式不会单独使用，
     跟模版方法模式、工厂模式等混合使用的情况比较多
 */
public class StrategyTest {
    public static void main(String[] args) {
        Context context = new Context(new StrategyImplA());
        context.execute();

        context = new Context(new StrategyImplB());
        context.execute();

        context = new Context(new StrategyImplC());
        context.execute();
    }
}
interface IStrategy {
    void doSomething();
}

class Context {
    private IStrategy strategy;

    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        strategy.doSomething();
    }
}

class StrategyImplA implements IStrategy {
    @Override
    public void doSomething() {
        System.out.println("实现策略A：");
    }
}

class StrategyImplB implements IStrategy {
    @Override
    public void doSomething() {
        System.out.println("实现策略B：");
    }
}

class StrategyImplC implements IStrategy {
    @Override
    public void doSomething() {
        System.out.println("实现策略C：");
    }
}



