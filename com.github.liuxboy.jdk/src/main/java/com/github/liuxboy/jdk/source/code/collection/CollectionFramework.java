package com.github.liuxboy.jdk.source.code.collection;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @param <E>
 * @author 刘春东
 * @time 2014年1月30日下午4:30:37
 */
public class CollectionFramework<E> {
    /**
     * 容器类Collection
     */
    Collection<?> cl;
    List<?> list = null;
    LinkedList linkedList;
    LinkedHashMap linkedHashMap;
    LinkedHashSet linkedHashSet;
    Set<?> st;
    Queue<?> queue;
    Deque<?> deque;

    ListIterator listIterator;
    TreeSet<?> treeSet;
    HashSet<?> hashSet;

    ArrayList<?> arrayList = null;
    HashMap<?, ?> hm;
    WeakHashMap<?, ?> weakHashMap;
    AbstractCollection<E> sc;
    EnumSet<?> es;
    Collections collections;
    Arrays arrays;

    Vector<?> vector;
    Stack<?> stack;
    Hashtable<?, ?> ht;
    Enumeration enumeration;

    public static void main(String[] args) {
        //testArrayList();
        traceTheArrayListGrow();
    }

    private static void testArrayList() {
        HashMap<String, String> hashMap1 = new HashMap<String, String>();
        HashMap<String, Person> hashMap2 = new HashMap<String, Person>(100, 0.8f);
        Person[] persons = new Person[10];
        persons[0] = new Person("刘亲", 27, true);
        persons[1] = new Person("张强", 27, true);
        persons[2] = new Person("王花", 27, false);
        persons[3] = new Person("李可白", 27, false);
        hashMap1.put("第一个1", "刘春花");
        hashMap1.put("第一个2", "张强");
        hashMap1.put("第一个3", "王花");
        hashMap1.put("第一个4", "李可白");
        hashMap2.put("one", persons[0]);
        hashMap2.put("two", persons[1]);
        hashMap2.put("three", persons[2]);
        hashMap2.put("four", persons[3]);
        System.out.println(hashMap1.put("第一个1", "梅西"));//打印的是第一个值"刘春花"
    }

    /**
     * 查询ArrayList的扩容原理
     */
    private static void traceTheArrayListGrow() {
        ArrayList<String> stringArrayList = new ArrayList<>(1);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(
                    () -> {
                        stringArrayList.add("LCD");
                        stringArrayList.add("ZW");
                        stringArrayList.add("XX");
                    });
        }
        System.out.println(stringArrayList);
    }
}
