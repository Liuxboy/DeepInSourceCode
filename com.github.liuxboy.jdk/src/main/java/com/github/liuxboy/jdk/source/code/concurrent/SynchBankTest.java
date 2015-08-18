package com.github.liuxboy.jdk.source.code.concurrent;

/**
 * Created In www.jdpay.com
 *
 * @author wyliuchundong
 * @version 1.0.0
 * @date 2015/7/28 puzzle16:puzzle24
 * @comment SynchBankTest
 * <p/>
 * 作者：kai_wei_zhang
 * URL：http://blog.csdn.net/kai_wei_zhang/article/details/8196130
 */

import java.util.concurrent.locks.*;

/**
 * This program shows how multiple threads can safely access a data structure.
 */
public class SynchBankTest {
    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;

    public static void main(String[] args) {
        Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
        int i;
        for (i = 0; i < 100; i++) {
            TransferRunnable r = new TransferRunnable(b, i, INITIAL_BALANCE);
            Thread t = new Thread(r);
            t.start();
        }
    }

}

/**
 * A bank with a number of bank accounts.
 */
class Bank {
    /**
     * Constructs the bank.
     *
     * @param n
     *            the number of accounts
     * @param initialBalance
     *            the initial balance for each account
     */

    private final double[] accounts;
    private Lock bankLock;
    private Condition sufficientFunds;

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        for (int i = 0; i < accounts.length; i++)
            accounts[i] = initialBalance;
        /*
        * 每一个Bank对象都有它自己的ReentrantLock对象。如果两个线程试图访问同一个Bank对象，锁就会串行的服务于访问。但是，如果两个线程访问不同的Bank对象
        * 那么每一个线程都会得到一个不同的锁，两者都不会发生阻塞。这正是我们期待的结果，因为当线程操作不同的Bank实例时，彼此之间不会相互影响
        */
        bankLock = new ReentrantLock();// 构建一个可被用来保护临界区的可重入锁
        sufficientFunds = bankLock.newCondition();// 获得一个条件对象，一个锁对象可以有一个或多个相关联的条件对象。
    }

    /**
     * Transfers money from one account to another.
     *
     * @param from
     *            the account to transfer from
     * @param to
     *            the account to transfer to
     * @param amount
     *            the amount to transfer
     */
    public void transfer(int from, int to, double amount)
            throws InterruptedException {
        bankLock.lock();
        try {
            while (accounts[from] < amount)
                sufficientFunds.await();// 当前线程现在被阻塞了 ，并且放弃了锁
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
            // 跟signalAll()功能类似的还有signal()方法，signal则是随机解除等待集中某个线程的阻塞状态。这比解除所有线程的阻塞状态更有用
            // 但是也存在危险。如果在随机选中的线程发现自己还是无法运行，他会再次被阻塞。如果没有任何其他的线程再次调用signal方法，那么系统就会死锁了
            sufficientFunds.signalAll();

        } finally {
            bankLock.unlock();
        }
    }

    /**
     * Gets the sum of all account balances.
     *
     * @return the total balance
     */
    public double getTotalBalance() {
        bankLock.lock();
        try {
            double sum = 0;

            for (double a : accounts)
                sum += a;

            return sum;
        } finally {
            bankLock.unlock();
        }
    }

    /**
     * Gets the number of accounts in the bank.
     *
     * @return the number of accounts
     */
    public int size() {
        return accounts.length;
    }

}

/**
 * A runnable that transfers money from an account to other accounts in a bank.
 */
class TransferRunnable implements Runnable {
    /**
     * Constructs a transfer runnable.
     *
     * @param b
     *            the bank between whose account money is transferred
     * @param from
     *            the account to transfer money from
     * @param max
     *            the maximum amount of money in each transfer
     */
    private Bank bank;
    private int fromAccount;
    private double maxAmount;
    private int repetitions;
    private int DELAY = 10;

    public TransferRunnable(Bank b, int from, double max) {
        bank = b;
        fromAccount = from;
        maxAmount = max;
    }

    public void run() {
        try {
            while (true) {
                int toAccount = (int) (bank.size() * Math.random());
                double amount = maxAmount * Math.random();
                bank.transfer(fromAccount, toAccount, amount);
                Thread.sleep((int) (DELAY * Math.random()));
            }
        } catch (InterruptedException e) {
        }
    }

}
