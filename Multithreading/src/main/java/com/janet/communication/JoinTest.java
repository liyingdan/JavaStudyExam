package com.janet.communication;

/**
 * @Description TODO
 * @Author Janet
 * @Date 2021-09-05
 */
public class JoinTest {
    private static class JoinTask implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "：开始执行");
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "：执行结束");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //1.创建两个子线程
        Thread thread1 = new Thread(new JoinTask());
//        Thread thread2 = new Thread(new JoinTask());
        //2.让两个子线程分别启动，并且让他们先执行
        thread1.start();
//        thread2.start();
        thread1.join();
//        thread2.join();
        //3.主线程等待子线程执行结束后，继续往下执行
        System.out.println(Thread.currentThread().getName() + "：等待的子线程已经执行结束，主线程执行结束");
    }
}
