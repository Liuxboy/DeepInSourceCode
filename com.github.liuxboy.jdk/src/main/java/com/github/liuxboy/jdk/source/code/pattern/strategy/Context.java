package com.github.liuxboy.jdk.source.code.pattern.strategy;

/**
 * Package: com.github.liuxboy.jdk.source.code.pattern.strategy <br>
 * Author: liuchundong <br>
 * Date: 2017/7/22 <br>
 * Time: 13:11 <br>
 * Desc:
 */
public class Context {
    private IStrategy strategy;

    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        strategy.doSomething();
    }
}
