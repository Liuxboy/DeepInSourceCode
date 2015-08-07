package com.github.liuxboy.jdk.source.code.concurrent;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Created In www.jdpay.com
 *
 * @author wyliuchundong
 * @version 1.0.0
 * @date 2015/7/30 21:04
 * @comment CLHLock
 * Author：山鸡
 * http://ifeve.com/java_lock_see2/
 */
public class CLHLock {
    // CLHLock是不停的查询前驱变量，导致不适合在NUMA，即非均匀访存模型(Non Uniform Memory Access)
    // 架构下使用（在这种结构下，每个线程分布在不同的物理内存区域）
    public static class CLHNode {
        private volatile boolean isLocked = true;
    }

    @SuppressWarnings("unused")
    private volatile CLHNode tail;
    private static final ThreadLocal<CLHNode> LOCAL = new ThreadLocal<CLHNode>();
    private static final AtomicReferenceFieldUpdater<CLHLock, CLHNode> UPDATER = AtomicReferenceFieldUpdater.newUpdater(CLHLock.class,
            CLHNode.class, "tail");

    public void lock() {
        CLHNode node = new CLHNode();
        LOCAL.set(node);
        CLHNode preNode = UPDATER.getAndSet(this, node);
        if (preNode != null) {
            while (preNode.isLocked) {
            }
            preNode = null;
            LOCAL.set(node);
        }
    }

    public void unlock() {
        CLHNode node = LOCAL.get();
        if (!UPDATER.compareAndSet(this, node, null)) {
            node.isLocked = false;
        }
        node = null;
    }
}
