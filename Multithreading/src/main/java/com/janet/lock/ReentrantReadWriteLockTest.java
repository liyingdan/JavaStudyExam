package com.janet.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description 实例读写锁是否可以多线程同时获取到锁
 * @Author Janet
 * @Date 2021-09-02
 */
public class ReentrantReadWriteLockTest {
    //1.创建一个读写锁对象
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    //2.获取写锁
    private static Lock readLock = readWriteLock.readLock();
    //3.获取读锁
    private static Lock writeLock = readWriteLock.writeLock();

    /**
     * 演示读锁的规则
     */
    public static void read() {
        //1.获取读锁
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ": get readLock");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + ": remove readLock");
            readLock.unlock();
        }
    }

    /**
     * 演示写锁的规则
     */
    public static void write() {
        //1.获取到写锁
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ":get writeLock");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + ":remove writeLock");
            //2.释放写锁
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        //启动两个线程去执行读操作，获取读锁
        new Thread(() -> { read(); }, "t1").start();
        new Thread(() -> { read(); }, "t2").start();

        //启动两个线程去执行写操作，获取写锁
        new Thread(() -> { write(); }, "t3").start();
        new Thread(() -> { write(); }, "t4").start();
    }
}


