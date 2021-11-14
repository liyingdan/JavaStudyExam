package com.janet.communication;

/**
 * @Description 1，演示两者的使用方式
 * 2，证明wait是会释放锁的
 * 3，sleep是不会释放锁的
 * @Author Janet
 * @Date 2021-09-05
 */
public class WaitNotifyTest {
    //定义一个共享的锁
    private static Object lock = new Object();

    static class WaitThread extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "：获取到锁");
                //进入阻塞状态
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //线程被唤醒之后，重新争取到使用权，继续往下执行
                System.out.println(Thread.currentThread().getName() + ":继续往下执行");
            }
        }
    }

    static class NotifyThread extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                //证明wait是会释放锁的，如果不释放锁，此处就等到上面的synchronized代码块执行结束
                System.out.println(Thread.currentThread().getName() + ":获取到锁");
                //随机唤醒lock锁对象的阻塞的线程
                lock.notify();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WaitThread waitThread = new WaitThread();
        NotifyThread notifyThread = new NotifyThread();
        waitThread.start();
        Thread.sleep(100);
        notifyThread.start();
    }


}
