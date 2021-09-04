package com.janet;

/**
 * @Description 简单示例，看下 synchronized 的字节码
 * @Author Janet
 * @Date 2021-08-09
 */
public class SynchronizedTest04 {
    private static Object lock = new Object();
    public static void main(String[] args){
        synchronized (lock){
            System.out.println("ok");
        }
    }


    public synchronized void test(){}
}
