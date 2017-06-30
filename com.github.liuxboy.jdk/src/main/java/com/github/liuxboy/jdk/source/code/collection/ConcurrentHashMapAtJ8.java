package com.github.liuxboy.jdk.source.code.collection;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Package: com.github.liuxboy.jdk.source.code.collection <br>
 * Author: liuchundong <br>
 * Date: 2017/6/28 <br>
 * Time: 15:19 <br>
 * Desc:
 */
public class ConcurrentHashMapAtJ8 {
    public static void main(String[] args) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(2);
        Person[] persons = new Person[10];
        persons[0] = new Person("刘亲", 27, true);
        persons[1] = new Person("张强", 27, true);
        persons[2] = new Person("王花", 27, false);
        persons[3] = new Person("李可白", 27, false);
        concurrentHashMap.put("1", persons[0]);
        concurrentHashMap.put("2", persons[1]);
        concurrentHashMap.put("3", persons[2]);
        System.out.println(concurrentHashMap.size());
        System.out.println(concurrentHashMap.toString());
    }
}
