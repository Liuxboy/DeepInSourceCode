package com.github.liuxboy.jdk.source.code.concurrent.copyonwrite;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Package: com.github.liuxboy.jdk.source.code.concurrent.copyonwrite <br>
 * Author: liuchundong <br>
 * Date: 2017/6/2 <br>
 * Time: 13:43 <br>
 * Desc:
 */
public class CopyOnWriteTest {

    private static CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
    private static CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet();
    private static CopyOnWriteMap copyOnWriteMap = new CopyOnWriteMap();
    public static void main(String[] args) {
        copyOnWriteArrayList.add(1);
        copyOnWriteArrayList.add(2);
        copyOnWriteArrayList.add(3);
        copyOnWriteArrayList.add(1);

        copyOnWriteArrayList.get(0);
        copyOnWriteArrayList.get(1);

        copyOnWriteArraySet.add(1);
        copyOnWriteArraySet.add(2);
        copyOnWriteArraySet.add(3);
        copyOnWriteArraySet.add(1);

        copyOnWriteMap.put("1", "NiHao");
        copyOnWriteMap.put("2", "HeHe");
        copyOnWriteMap.put("3", "HaHa");

        copyOnWriteMap.get("1");


    }
}
