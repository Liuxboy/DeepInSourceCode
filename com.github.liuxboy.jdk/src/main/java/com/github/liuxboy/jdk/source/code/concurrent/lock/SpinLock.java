package com.github.liuxboy.jdk.source.code.concurrent.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created In www.jdpay.com
 *
 * @author wyliuchundong
 * @version 1.0.0
 *          2017-08-03 10:29:32
 *          SpinLock
 *          ---自旋锁---
 *          自旋锁它是为为实现保护共享资源而提出一种锁机制。其实，自旋锁与互斥锁比较类似，它们都是为了解决对某项资源的互斥使用。
 *          无论是互斥锁，还是自旋锁，在任何时刻，最多只能有一个保持者，也就说，在任何时刻最多只能有一个执行单元获得锁。
 *          但是两者在调度机制上略有不同。对于互斥锁，如果资源已经被占用，资源申请者只能进入睡眠状态。但是自旋锁不会引起调用者睡眠，
 *          如果自旋锁已经被别的执行单元保持，调用者就一直循环在那里看是否该自旋锁的保持者已经释放了锁，其实是一种"忙等待"。
 *          "自旋"一词就是因此而得名。
 */
public class SpinLock {
    private static volatile long count;
    private static AtomicLong atomicLong = new AtomicLong();
    private static ExecutorService pool = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        final int deltaPerThreads = 10000;
        final int threadNum = 10;

        Callable<Boolean> rawCounter = () -> {
            for (int i = 0; i < deltaPerThreads; i++) count++;
            return true;
        };

        Callable<Boolean> synCounter = () -> {
            for (int i = 0; i < deltaPerThreads; i++) {
                synchronized (CLHLock.class) {
                    count++;
                }
            }
            return true;
        };

        Callable<Boolean> clhCounter = new Callable<Boolean>() {
            private CLHLock lock = new CLHLock();

            @Override
            public Boolean call() throws Exception {
                for (int i = 0; i < deltaPerThreads; i++) {
                    lock.lock();
                    count++;
                    lock.unlock();
                }
                return true;
            }
        };

        Callable<Boolean> mscCounter = new Callable<Boolean>() {
            private MCSLock lock = new MCSLock();

            @Override
            public Boolean call() throws Exception {
                for (int i = 0; i < deltaPerThreads; i++) {
                    lock.lock();
                    count++;
                    lock.unlock();
                }
                return true;
            }
        };

        Callable<Boolean> ticketLock = new Callable<Boolean>() {
            private TicketLock lock = new TicketLock();

            @Override
            public Boolean call() throws Exception {
                for (int i = 0; i < deltaPerThreads; i++) {
                    lock.lock();
                    count++;
                    lock.unlock();
                }
                return true;
            }
        };

        final Callable<Boolean> lckCounter = new Callable<Boolean>() {
            private ReentrantLock lock = new ReentrantLock();

            @Override
            public Boolean call() throws Exception {
                for (int i = 0; i < deltaPerThreads; i++) {
                    lock.lock();
                    count++;
                    lock.unlock();
                }
                return true;
            }
        };

        final Callable<Boolean> lckFairCounter = new Callable<Boolean>() {
            private ReentrantLock lock = new ReentrantLock(true);

            @Override
            public Boolean call() throws Exception {
                for (int i = 0; i < deltaPerThreads; i++) {
                    lock.lock();
                    count++;
                    lock.unlock();
                }
                return true;
            }
        };

        Callable<Boolean> semCounter = new Callable<Boolean>() {
            private Semaphore sem = new Semaphore(1);

            @Override
            public Boolean call() throws Exception {
                for (int i = 0; i < deltaPerThreads; i++) {
                    sem.acquire();
                    count++;
                    sem.release();
                }
                return true;
            }
        };

        Callable<Boolean> semFairCounter = new Callable<Boolean>() {
            private Semaphore sem = new Semaphore(1, true);

            @Override
            public Boolean call() throws Exception {
                for (int i = 0; i < deltaPerThreads; i++) {
                    sem.acquire();
                    count++;
                    sem.release();
                }
                return true;
            }
        };

        Callable<Boolean> casCounter = new Callable<Boolean>() {
            private AtomicBoolean locked = new AtomicBoolean();

            @Override
            public Boolean call() throws Exception {
                for (int i = 0; i < deltaPerThreads; i++) {
                    while (true) {
                        if (locked.compareAndSet(false, true)) {
                            count++;
                            locked.getAndSet(false);
                            break;
                        }
                    }
                }
                return true;
            }
        };

        Callable<Boolean> pureCas = () -> {
            for (int i = 0; i < deltaPerThreads; i++) {
                atomicLong.incrementAndGet();
            }
            return true;
        };

        test("raw", rawCounter, deltaPerThreads, threadNum);
        test("syn", synCounter, deltaPerThreads, threadNum);
        test("CLH", clhCounter, deltaPerThreads, threadNum);
        test("MSC", mscCounter, deltaPerThreads, threadNum);
        test("Ticket", ticketLock, deltaPerThreads, threadNum);
        test("lock", lckCounter, deltaPerThreads, threadNum);
        test("lockFair", lckFairCounter, deltaPerThreads, threadNum);
        test("sem", semCounter, deltaPerThreads, threadNum);
        test("semFair", semFairCounter, deltaPerThreads, threadNum);
        test("CAS", casCounter, deltaPerThreads, threadNum);
        test("pureCAS", pureCas, deltaPerThreads, threadNum);
        /*
        raw 表示不进行任何同步，现在没有保护的情况下计数累加产生了错误
        syn 表示使用内置锁同步方式即使用synchronized块，在几种方式里表现最好
        CLH 表示使用自己实现的一个CLH锁（具有排队功能的自旋锁，自旋查询前驱节点是否释放锁），这里的CLH的实现方式决定了它是一个公平锁
        MSC 表示使用自己实现的一个MSC锁（具有排队功能的自旋锁，自旋查询当前节点是否获得锁），这里的MSC的实现方式决定了它也是一个公平锁
        lock 表示用ReentrantLock进行数据保护，速度仅次于内置锁，默认是非公平版本；lockFair是它的公平版本，不过速度上有大幅下降，变慢了将近20倍
        sem 表示使用Semaphore也就是信号量进行数据保护，速度也不错，默认是非公平版本；semFair是它的公平版本，和ReentrantLock上出现的情况一样，公平版本比非公平版本出现了大幅的速度下降，慢了10倍
        CAS 用CAS操作实现简单的自旋锁，不具有排队功能
        pureCAS 表示直接用使用AtomicLong类型的count变量进行计数，就不需要锁保护了，速度也是非
        */

        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println((Runtime.getRuntime().freeMemory() >>> 20) + "M");
        System.out.println((Runtime.getRuntime().maxMemory() >>> 20) + "M");
        System.out.println((Runtime.getRuntime().totalMemory() >>> 20) + "M");
    }

    private static void test(String name, Callable<Boolean> callable, final int deltaPerThreads, final int threadNum) {
        count = 0;
        atomicLong.set(0);
        pool = Executors.newCachedThreadPool();

        List<Callable<Boolean>> calls = new ArrayList<>();
        for (int i = 0; i < threadNum; i++) {
            calls.add(callable);
        }
        long start = System.nanoTime();
        try {
            pool.invokeAll(calls);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdownNow();
        long end = System.nanoTime();
        System.out.println("case   : " + name);
        System.out.println("counted: " + (name.equals("pureCAS") ? atomicLong.get() : count));
        System.out.println("target : " + threadNum * deltaPerThreads);
        System.out.println("time   : " + (end - start) / 1e6 + "ms");
        System.out.println();
    }
}

/**
 * Author：山鸡
 * http://ifeve.com/java_lock_see2/
 * Ticket Lock 虽然解决了公平性的问题，但是多处理器系统上，每个进程/线程占用的处理器都在读写同一个变量serviceNum，
 * 每次读写操作都必须在多个处理器缓存之间进行缓存同步，这会导致繁重的系统总线和内存的流量，大大降低系统整体的性能。
 */
class TicketLock {
    private AtomicInteger serviceNum = new AtomicInteger();
    private AtomicInteger ticketNum = new AtomicInteger();
    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public void lock() {
        int myTicket = ticketNum.getAndIncrement();
        threadLocal.set(myTicket);
        while (myTicket != serviceNum.get()) {
        }
    }

    public void unlock() {
        int myTicket = threadLocal.get();
        serviceNum.compareAndSet(myTicket, myTicket + 1);
    }
}

/**
 * @link <a href = "http://www.cnblogs.com/lailailai/p/4609275.html" />
 * CLH Lock，名字来源于其发明人：Craig, Landin, and Hagersten locks。
 * 是一个自旋锁，能确保无饥饿性，提供先来先服务的公平性。
 * CLH锁也是一种基于链表的可扩展、高性能、公平的自旋锁。
 * 申请线程只在本地变量上自旋，它不断轮询前驱的状态，如果发现前驱释放了锁就结束自旋。
 * CLH队列锁的优点就是空间复杂的度低（如果有n个线程，L个锁，每个线程每次只获取一个锁，那么需要的存储空间就是O(L+n)，
 * n个线程有n个Node，L个锁有L个tail），CLH的一种变体被应用到Java并发框架(AbstractQueuedSynchronizer.Node)。
 * CLH在SMP系统结构下该法非常有效。但是在NUMA系统结构下，每个线程都有自己的内存，如果前驱节点的内存位置较远，
 * 自旋判断前驱节点的locked域，性能将大打折扣，一种解决NUMA系统结构的思路是MCS队列锁
 */
class CLHLock {
    private static class CLHNode {
        private volatile boolean isLocked = false;
    }

    private static final ThreadLocal<CLHNode> threadLocalNode = new ThreadLocal<>();
    private static final AtomicReference<CLHNode> tail = new AtomicReference<>(new CLHNode());

    public void lock() {
        CLHNode lastTail;
        CLHNode newTail = new CLHNode();
        newTail.isLocked = true;

        while (true) {
            lastTail = tail.get();
            if (tail.compareAndSet(lastTail, newTail)) {
                break;
            }
        }
        while (lastTail.isLocked) {
            Thread.yield();
        }
        threadLocalNode.set(newTail);
    }

    public void unlock() {
        CLHNode node = threadLocalNode.get();
        if (node == null) {
            throw new IllegalMonitorStateException();
        }
        node.isLocked = false;
        threadLocalNode.set(null);
    }
}

/**
 * MCS Lock也是一种自旋锁，名字与CLH类似，来自发明人名字首字母：John Mellor-Crummey和Michael Scott。
 * MSC与CLH最大的不同并不是链表是显示还是隐式，而是线程自旋的规则不同：CLH是在前趋结点的locked域上自旋等待，
 * 而MCS是在自己的结点的locked域上自旋等待。正因为如此，它解决了CLH在NUMA系统架构中获取locked域状态内存过远的问题
 * MCS队列锁的具体实现如下：
 * a. 队列初始化时没有结点，tail=null
 * b. 线程A想要获取锁，于是将自己置于队尾，由于它是第一个结点，它的locked域为false
 * c. 线程B和C相继加入队列，a->next=b,b->next=c。且B和C现在没有获取锁，处于等待状态，所以它们的locked域为true，
 * 尾指针指向线程C对应的结点
 * d. 线程A释放锁后，顺着它的next指针找到了线程B，并把B的locked域设置为false。这一动作会触发线程B获取锁
 */
class MCSLock {
    private static class MCSNode {
        volatile boolean isLocked = true;
        volatile MCSNode next;
    }

    private static final ThreadLocal<MCSNode> threadLocalNode = new ThreadLocal<>();
    private static final AtomicReference<MCSNode> tail = new AtomicReference<>();

    public void lock() {
        MCSNode currentNode = new MCSNode();
        MCSNode preNode = tail.getAndSet(currentNode);
        //if the predecessor isn't null, lock currentNode
        if (preNode != null) {
            currentNode.isLocked = true;
            preNode.next = currentNode;
            //wait util the predecessor gives up the lock
            while (currentNode.isLocked) {

            }
        }
        threadLocalNode.set(currentNode);
    }

    public void unlock() {
        MCSNode currentNode = threadLocalNode.get();
        //if the current node is the tail
        if (currentNode.next == null) {
            //cas set the current node is null
            if (tail.compareAndSet(currentNode, null))
                return;
            //wait until the predecessor fills in its next filed
            while (currentNode.next == null) {

            }
        }
        //else invoke next node
        currentNode.next.isLocked = false;
        currentNode.next = null;
        threadLocalNode.set(null);
    }
}

