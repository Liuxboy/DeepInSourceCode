package com.github.liuxboy.jdk.source.code.rpc;

/**
 * Package: com.github.liuxboy.jdk.source.code.rpc <br>
 * Author: liuchundong <br>
 * Date: 2017/8/17 <br>
 * Time: 11:53 <br>
 * Desc:
 */
public class SayHelloServiceImpl implements SayHelloService {
    @Override
    public String sayHello(String args) {
        if (args.equals("hello"))
            return "hello";
        else
            return "goodbye";
    }
}
