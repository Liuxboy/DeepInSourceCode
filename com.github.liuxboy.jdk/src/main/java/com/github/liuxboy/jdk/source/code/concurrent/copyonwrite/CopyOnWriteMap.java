package com.github.liuxboy.jdk.source.code.concurrent.copyonwrite;

/**
 * Package: com.github.liuxboy.jdk.source.code.concurrent.copyonwrite <br>
 * Author: liuchundong <br>
 * Date: 2017/6/5 <br>
 * Time: 14:35 <br>
 * Desc:
 * 什么是CopyOnWrite容器？
 * CopyOnWrite容器即写时复制的容器。通俗的理解是当我们往一个容器添加元素的时候，不直接往当前容器添加，而是先将当前容器进行Copy，
 * 复制出一个新的容器，然后新的容器里添加元素，添加完元素之后，再将原容器的引用指向新的容器(删除元素同理)。
 * 这样做的好处是我们可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。
 * 所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。
 *
 * CopyOnWrite的应用场景
 * CopyOnWrite并发容器用于读多写少的并发场景，比如白名单，黑名单，商品类目的访问和更新场景。
 * 假如我们有一个搜索网站，用户在这个网站的搜索框中，输入关键字搜索内容，但是某些关键字不允许被搜索。
 * 这些不能被搜索的关键字会被放在一个黑名单当中，黑名单每天晚上更新一次。
 * 当用户搜索时，会检查当前关键字在不在黑名单当中，如果在，则提示不能搜索。
 *
 * 使用CopyOnWriteMap需要注意两件事情：
 * 1. 减少扩容开销。根据实际需要，初始化CopyOnWriteMap的大小，避免写时CopyOnWriteMap扩容的开销。
 * 2. 使用批量添加。因为每次添加，容器每次都会进行复制，所以减少添加次数，可以减少容器的复制次数。
 */


import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CopyOnWriteMap<K, V> implements Map<K, V>, Cloneable, Serializable {
    private transient volatile Map<K, V> internalMap;

    public CopyOnWriteMap() {
        internalMap = new HashMap<>();
    }

    @Override
    public V put(K key, V value) {
        synchronized (this) {
            Map<K, V> newMap = new HashMap<>(internalMap);
            V val = newMap.put(key, value);
            internalMap = newMap;
            return val;
        }
    }

    @Override
    public V get(Object key) {
        return internalMap.get(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> newData) {
        synchronized (this) {
            Map<K, V> newMap = new HashMap<>(internalMap);
            newMap.putAll(newData);
            internalMap = newMap;
        }
    }

    @Override
    public int size() {
        return internalMap.size();
    }

    @Override
    public boolean isEmpty() {
        return internalMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return internalMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return internalMap.containsValue(value);
    }

    @Override
    public V remove(Object key) {
        synchronized (this) {
            Map<K, V> newMap = new HashMap<>(internalMap);
            V v = newMap.remove(key);
            internalMap = newMap;
            return v;
        }
    }

    @Override
    public void clear() {
        synchronized (this) {
            Map<K, V> newMap = new HashMap<>(internalMap);
            newMap.clear();
            internalMap = newMap;
        }
    }

    @Override
    public Set<K> keySet() {
        return internalMap.keySet();
    }

    @Override
    public Collection<V> values() {
        return internalMap.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return internalMap.entrySet();
    }
}
