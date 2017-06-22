package com.github.liuxboy.jdk.source.code.collection;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Package: com.github.liuxboy.jdk.source.code.collection <br>
 * Author: liuchundong <br>
 * Date: 2017/6/13 <br>
 * Time: 10:49 <br>
 * Desc:
 */
public class CollectionSubtract {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("lcd", 30, true));
        personList.add(new Person("zw", 26, true));
        personList.add(new Person("wj", 24, false));

        List<Person> hasExistPerson = new ArrayList<>();
        hasExistPerson.add(new Person("lcd", 30, true));
        hasExistPerson.add(new Person("wwy", 30, true));
        hasExistPerson.add(new Person("yyc", 25, true));
        System.out.println("去除已存在之前：" + personList);
        personList = subtractPerson(personList, hasExistPerson);
        System.out.println("去除已存在之后：" + personList);
    }

    private static List<Person> subtractPerson(List<Person> personList, List<Person> hasExistPerson) {
        List<Person> insertingPersonList = (List<Person>) CollectionUtils.subtract(personList, hasExistPerson);
        System.out.println("去除已存在之中：" + insertingPersonList);
        return insertingPersonList;
    }
}
