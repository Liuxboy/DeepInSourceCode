package com.github.liuxboy.jdk.source.code.interview.java;

import java.util.*;

/**
 * Created with IntelliJ IDEA13.0
 * Author:刘春东
 * E-mail:liuchundong2008@gamil.com
 * Date:puzzle14-7-23
 * Time:下午3:36
 * Version:0.1
 */
public class DuplicateValue {
    //消除List中相同的值
    public void removeDuplicateValue(List list) {
        Set set = new HashSet(list);
        /* list = new ArrayList(set);
         * Iterator it = list.iterator();
         * 上面的将set转换成list是完全没有必要的,set本身也有迭代器
         */
        Iterator it = set.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
    public static void main(String[] args) {
        List myList = new ArrayList();
        myList.add(1);
        myList.add(2);
        myList.add(1);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        myList.add(6);
        myList.add(5);
        DuplicateValue dv = new DuplicateValue();
        dv.removeDuplicateValue(myList);
    }
}
