package com.janet.synchronize;

/**
 * @Description 原子性问题
 * @Author Janet
 * @Date 2021-07-07
 */
public class AtomicityTest02 {

    private static int count = 0;

    private static Object lock = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {

        //创建两个线程，分别执行count++，执行1000次
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (lock) {
                    count++;
                }
            }
            System.out.printf("%s执行完毕%n", Thread.currentThread().getName());
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (lock2) {
                    count++;
                }
            }
            System.out.printf("%s执行完毕%n", Thread.currentThread().getName());
        });
        thread1.start();
        thread2.start();

        //需要等待两个子线程执行结束之后，主线程才会继续往下执行输出最终count的值
        thread1.join();
        thread2.join();

        System.out.println("count=" + count);
    }

}
