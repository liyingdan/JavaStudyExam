package com.janet.communication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description 采用wait和notify来实现生产者消费者模式
 * @Author Janet
 * @Date 2021-09-05
 */
public class ProducerConsumerTest {
    /**
     * 仓库类
     */
    static class Storage {
        private int maxSize;
        private List<Date> list;

        public Storage() {
            this.maxSize = 10;
            this.list = new ArrayList<>();
        }

        public Storage(int maxSize) {
            if (maxSize <= 0) {
                this.maxSize = 10;
            } else {
                this.maxSize = maxSize;
            }
            this.list = new ArrayList<>();
        }

        //定义两个方法
        public synchronized void put() {
            //当仓库已满，则当前线程需要进入等待状态
            if (list.size() == maxSize) {
                try {
                    System.out.println("仓库已满");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //当被唤醒之前，继续进入生产
            list.add(new Date());
            System.out.println(Thread.currentThread().getName() + "：生产了一个对象，当前共有" + list.size() + " 件商品");
            //是否完成了？？？
            //唤醒消费者线程进行消费(消费者线程有可能因为仓库已空，而进入等待状态)
            this.notify();
        }

        public synchronized void get() {
            //当仓库已空，则当前线程进入等待状态
            if (list.size() == 0) {
                System.out.println("仓库已空");
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //被唤醒了之后，继续消费
            System.out.println(Thread.currentThread().getName() + ":消费了一个对象：" + list.remove(list.size() - 1));
            this.notify();
        }
    }

    /**
     * 生产者
     */
    private static class Producer extends Thread {
        private Storage storage;

        public Producer(Storage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                storage.put();
            }
        }
    }

    /**
     * 消费者
     */
    private static class Consumer extends Thread {
        private Storage storage;

        public Consumer(Storage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                storage.get();
            }
        }
    }

    public static void main(String[] args) {
        //1.仓库
        Storage storage = new Storage();
        //2.生产者
        Producer producer = new Producer(storage);
        producer.setName("producer");
        //3.消费者
        Consumer consumer = new Consumer(storage);
        consumer.setName("consumer");
        //启动干活
        producer.start();
        consumer.start();
    }
}



