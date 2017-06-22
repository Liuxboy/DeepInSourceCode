package com.github.liuxboy.interview.code;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Package: com.github.liuxboy.interview.code <br>
 * Author: liuchundong <br>
 * Date: 2017/6/16 <br>
 * Time: 10:50 <br>
 * Desc:
 * 将数据库的数据抓出来，写到redis缓存起来，但是这个过程会有1000个线程同时操作
 * 待明确问题：
 * 1、数据库数据是动态的么？数据库新增数据也还要搬家否？
 * 2、这1000个线程是多机执行还是单机执行
 * 解决方案：
 * 方案一：
 * 方案二：
 */
public class DataTransferUtil {
    private ConcurrentLinkedQueue concurrentLinkedQueue;

    public static void main(String[] args) {

    }

}
