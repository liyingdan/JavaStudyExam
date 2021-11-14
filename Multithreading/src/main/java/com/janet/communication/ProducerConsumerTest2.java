package com.janet.communication;

import java.util.Date;

/**
 * @Description 采用wait和notify来实现生产者消费者模式
 * @Author Janet
 * @Date 2021-09-05
 */
public class ProducerConsumerTest2 {
    /**
     * 仓库类
     */
    private MyBlockingQueue myBlockingQueue;

    /**
     * 生产者
     */
    private static class Producer extends Thread {
        private MyBlockingQueue myBlockingQueue;

        public Producer(MyBlockingQueue myBlockingQueue) {
            this.myBlockingQueue = myBlockingQueue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                Date date = new Date();
                System.out.println(Thread.currentThread().getName()+":put"+date);
                try {
                    myBlockingQueue.put(date);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 消费者
     */
    private static class Consumer extends Thread {
        private MyBlockingQueue myBlockingQueue;

        public Consumer(MyBlockingQueue myBlockingQueue) {
            this.myBlockingQueue = myBlockingQueue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println(Thread.currentThread().getName()+":get"+myBlockingQueue.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        //1.仓库
        MyBlockingQueue myBlockingQueue = new MyBlockingQueue(10);
        //2.生产者
        Producer producer = new Producer(myBlockingQueue);
        producer.setName("producer");
        //3.消费者
        Consumer consumer = new Consumer(myBlockingQueue);
        consumer.setName("consumer");
        //启动干活
        producer.start();
        consumer.start();
    }
}



