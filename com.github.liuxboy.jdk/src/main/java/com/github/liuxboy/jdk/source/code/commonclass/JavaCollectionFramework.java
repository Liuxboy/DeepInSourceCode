package com.github.liuxboy.jdk.source.code.commonclass;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author 刘春东
 * @version 
 * @param <E>
 * @time 2014年1月30日下午4:30:37
 */
public class JavaCollectionFramework<E> {
	/**
	 * 容器类Collection
	 */
	Collection<?> cl;
	List<?> list = null;
    ArrayList<?> arrayList = null;
	Vector<?> vector = null;
	HashMap<?, ?> hm;
	Hashtable<?, ?> ht;
    Arrays arrays;
	AbstractCollection<E> sc;
	EnumSet<?> es;

    /**
     * 并发包
     */
    ConcurrentHashMap concurrentHashMap;
    ConcurrentLinkedQueue concurrentLinkedQueue;
    ConcurrentNavigableMap concurrentNavigableMap;
    ConcurrentMap concurrentMap;
    ConcurrentSkipListMap concurrentSkipListMap;
    ConcurrentSkipListSet concurrentSkipListSet;

    public static void main(String[] args){
        HashMap<String,String> hashMap1 = new HashMap<String,String>();
        HashMap<String,Person> hashMap2 = new HashMap<String,Person>(100,0.8f);
        Person[] persons = new Person[10];
        persons[0] = new Person("刘亲",27);
        persons[1] = new Person("张强",27);
        persons[2] = new Person("王花",27);
        persons[3] = new Person("李可白",27);
        hashMap1.put("第一个1","刘春东");
        hashMap1.put("第一个2","张强");
        hashMap1.put("第一个3","王花");
        hashMap1.put("第一个4","李可白");
        hashMap2.put("one",persons[0]);
        hashMap2.put("two",persons[1]);
        hashMap2.put("three",persons[2]);
        hashMap2.put("four", persons[3]);
        System.out.println(hashMap1.put("第一个1","梅西"));//打印的是第一个值"刘春东"
    }
}

class Person{
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    String name;
    int age;
}