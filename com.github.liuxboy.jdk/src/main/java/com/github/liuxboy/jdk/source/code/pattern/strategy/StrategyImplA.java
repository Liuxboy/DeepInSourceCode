package com.github.liuxboy.jdk.source.code.pattern.strategy;

/**
 * Package: com.github.liuxboy.jdk.source.code.pattern.strategy <br>
 * Author: liuchundong <br>
 * Date: 2017/7/22 <br>
 * Time: 13:13 <br>
 * Desc:
 */
public class StrategyImplA implements IStrategy {
    @Override
    public void doSomething() {
        System.out.println("实现策略A：");
    }
}
