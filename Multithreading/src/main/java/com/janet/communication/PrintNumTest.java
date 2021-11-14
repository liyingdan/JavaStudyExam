package com.janet.communication;

/**
 * @Description 要求：由两个线程来交替打印，完成1-100之间的输出
 * @Author Janet
 * @Date 2021-09-05
 */
public class PrintNumTest {
    private static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    for (int i = 1; i <= 100; i += 2) {
                        lock.notify();
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }, "线程1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    for (int i = 2; i <= 100; i += 2) {
                        lock.notify();
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }, "线程2").start();
    }
//synchronized修饰方法时，锁对象就是当前对象
}


