package com.janet.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description Lock+Condition来实现阻塞队列，内部是通过等待+唤醒的机制来实现的
 * @Author Janet
 * @Date 2021-09-12
 */
public class MyBlockingQueue {
    /**
     * 1.定义对象数组来存放队列元素
     */
    private Object[] items;

    /**
     * 2.定义添加下标，删除下标，当前数组的包含的元素数量
     */
    private int addIndex;
    private int removeIndex;
    private int count;

    /**
     * 3.创建锁对象及对应的Condition
     * 这些属性的初始化都应该放在构造方法里面来实现
     */
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public MyBlockingQueue(int size) {
        items = new Object[size];
    }

    /**
     * 往队列放元素
     *
     * @param o
     */
    public void put(Object o) throws InterruptedException {
        lock.lock();
        try {
            //做好边界判断
            //判断当前队列是否已满
            while (count == items.length) { //这里不能用if，不然进去的瞬间已经满了怎么办？要一直判断
                //让线程进入等待状态
                notFull.await();
            }
            //如果队列没有达到满的状态，则往里面放东西
            items[addIndex] = o;
            if (++addIndex == items.length) {
                //重置回到第一位
                addIndex = 0;
            }
            //当前队列的数量加1
            count++;
            //当前队列已有元素，则唤醒那些因队列为空而进入等待状态的线程
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object get() throws InterruptedException {
        lock.lock();
        try {
            //判断当前队列是否已空
            while (count == 0) {
                notEmpty.await();
            }
            //如果队列不为空，则获取元素
            Object o = items[removeIndex];
            if (++removeIndex == items.length) {
                removeIndex = 0;
            }
            count--;
            //当前队列已经不是满的状态，所以唤醒那些因为队列满而进入等待状态的线程
            notFull.signal();
            return o;
        } finally {
            lock.unlock();
        }
    }

}
