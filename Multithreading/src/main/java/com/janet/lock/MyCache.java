package com.janet.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description 使用 HashMap 来实现线程安全的缓存组件
 * @Author Janet
 * @Date 2021-09-04
 */
public class MyCache {
    //存储数据的对象
    private static Map<String,Object> map = new HashMap<>();

    //创建读锁和写锁对象
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();

    /**
     * 写缓存
     */
    public static void put(String key,Object value){
        //获取写锁
        writeLock.lock();
        try{
            map.put(key,value);
        }finally {
            writeLock.unlock();
        }
    }

    /**
     * 读缓存
     */
    public static Object get(String key){
        //获取读锁
        //让多个线程可以并行走
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    /*
    * 清空缓存
    * */
    public static void clean(){
        //获取写锁
        writeLock.lock();
        try {
            map.clear();
        } finally {
            writeLock.unlock();
        }
    }


}
