package com.janet.communication;

/**
 * @Description TODO
 * @Author Janet
 * @Date 2021-09-05
 */
public class SleepTest {
    private static Object lock = new Object();

    static class SleepThread extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + ":获取到锁");
                try {
                    System.out.println(Thread.currentThread().getName() + ":进入休眠");
                    //sleep并不会释放锁
                    Thread.sleep(6000);
                    System.out.println(Thread.currentThread().getName() + "：睡醒了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SleepThread sleepThread1 = new SleepThread();
        SleepThread sleepThread2 = new SleepThread();
        sleepThread1.start();
        sleepThread2.start();
    }
}
