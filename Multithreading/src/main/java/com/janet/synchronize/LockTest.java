package com.janet.synchronize;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Description 演示重量级锁
 * @Author Janet
 * @Date 2021-08-29
 */
public class LockTest {

    public static void main(String[] args) {
        new MyThread().start();
        new MyThread().start();
    }

    static class MyThread extends Thread {
        private static Object lock = new Object(); //因为锁是静态变量，所以无论起几个线程，都是公用这个锁

        @Override
        public void run() {

            for (int i = 0; i < 3; i++) {
                synchronized (lock) {
                    //由于有了锁竞争，就会晋升为重量级锁
                    System.out.println(ClassLayout.parseInstance(lock).toPrintable());
                }
            }
        }
    }

}
