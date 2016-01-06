package com.github.liuxboy.jdk.source.code.pattern;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * <p>Title: ProducerAndConsumer</p>
 * <p>Copyright: Copyright(c)2015</p>
 * <p>Company: JD.JR </p>
 * <p>Time: 2016/1/6 18:10</p>
 * <p>Description: 最简单的生产者-消费者模式 </p>
 *
 * @author wyliuchundong
 * @version 0.0.1
 */
public class ProducerAndConsumer {
    /**
     * 生产线程
     */
    public static class ProducerThread extends Thread {

        //阻塞队列
        private BlockingQueue<String> queue;
        //产品编号
        int i = 0;

        public ProducerThread(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            //生产产品，推向队列
            for (; ;) {
                String task = "产品" + (++i);
                try {
                    queue.put(task);
                    System.out.println(Thread.currentThread()+"生产了:"+task);
                    sleep(3000);
                } catch (InterruptedException e) {
                    //TODO
                }
            }
        }
    }

    /**
     * 消费线程
     */
    public static class ConsumerThread extends Thread {
        private BlockingQueue<String> queue;

        //
        public ConsumerThread(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            String task = null;
            //消费产品
            try {
                while ((task = queue.take()) != null) {
                    System.out.println(Thread.currentThread()+"消费了:" + task);
                }
            } catch (InterruptedException e) {
                //TODO
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<String> queue = new SynchronousQueue<String>();
        new ProducerThread(queue).start();  //启动生产者线程
        new ConsumerThread(queue).start();  //启动消费者线程
    }
}
