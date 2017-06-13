package com.github.liuxboy.jdk.source.code.collection;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Package: com.github.liuxboy.jdk.source.code.collection <br>
 * Author: liuchundong <br>
 * Date: 2017/3/15 <br>
 * Time: 18:36 <br>
 * Desc:
 */
public class StreamHandle {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("lcd", 30, true));
        personList.add(new Person("lcd", 30, true));
        personList.add(new Person("zw", 26, true));
        personList.add(new Person("wj", 24, false));
        personList.stream()
                .filter(o -> o.isMale())
                .sorted((Person o1, Person o2) -> o1.getAge() > o2.getAge() ? 1 : (o1.getAge() == o2.getAge()) ? 0 : -1)
                .distinct() //重写了hashCode与equals才能去重的
                .forEach(System.out::println);
        System.out.println("结果：" + personList);
    }
}
