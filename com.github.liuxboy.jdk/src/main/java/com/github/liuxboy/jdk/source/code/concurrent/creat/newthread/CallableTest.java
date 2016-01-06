package com.github.liuxboy.jdk.source.code.concurrent.creat.newthread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created In www.jdpay.com
 *
 * @author wyliuchundong
 * @version 1.0.0
 * @date 2015/9/3 19:37
 * @comment CallableTest
 */
public class CallableTest implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int i = 0;
        for(;i<100;i++)
        {
            System.out.println(Thread.currentThread().getName()+" "+i);
        }
        return i;
    }
}
