package com.janet.communication;

import java.util.concurrent.locks.LockSupport;

/**
 * @Description 测试LockSupport中park方法和unpark方法是否有先后顺序
 * @Author Janet
 * @Date 2021-09-06
 */
public class LockSupportTest {
    public static void main(String[] args) {

        //先创建一个线程，让其调用park方法进入等待状态
        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":start....");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":park....");

            LockSupport.park();

            System.out.println(Thread.currentThread().getName() + ":end....");
        }, "thread1");

        thread1.start();

        //再调用unpark方法，让unpark先执行
        System.out.println(Thread.currentThread().getName() + "：unpark....");

        //明确指定唤醒哪个线程！
        LockSupport.unpark(thread1);
    }
}
