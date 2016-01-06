package com.github.liuxboy.jdk.source.code.concurrent.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created In www.jdpay.com
 *
 * @author wyliuchundong
 * @version 1.0.0
 * @date 2015/7/30 puzzle20:28
 * @comment TicketLock
 * Author：山鸡
 * http://ifeve.com/java_lock_see2/
 */
public class TicketLock {
    private AtomicInteger serviceNum = new AtomicInteger();
    private AtomicInteger ticketNum = new AtomicInteger();
    private static final ThreadLocal<Integer> LOCAL = new ThreadLocal<Integer>();

    public void lock() {
        int myTicket = ticketNum.getAndIncrement();
        LOCAL.set(myTicket);
        while (myTicket != serviceNum.get()) {
        }
    }

    public void unlock() {
        int myTicket = LOCAL.get();
        serviceNum.compareAndSet(myTicket, myTicket + 1);
    }
}
