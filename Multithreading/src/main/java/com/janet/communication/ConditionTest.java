package com.janet.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description Lock+Condition实现等待通知机制
 * @Author Janet
 * @Date 2021-09-12
 */
public class ConditionTest {

    //lock解决互斥问题
    private static ReentrantLock lock = new ReentrantLock();
    //Condition解决通信问题
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        //创建一个线程，会进入等待状态
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + ":进入等待状态");
                condition.await();
                System.out.println(Thread.currentThread().getName() + ":被唤醒，继续执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        //另外一个线程会来唤醒该线程
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + ":完成工作，并唤醒其他线程");
                condition.signal();
            } finally {
                lock.unlock();
            }
        }).start();

    }
}
