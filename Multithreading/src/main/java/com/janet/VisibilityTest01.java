package com.janet;

/**
 * @Description CPU引入多级缓存架构带来了可见性问题
 * @Author Janet
 * @Date 2021-07-05
 */
public class VisibilityTest01 {
    //定义一个共享变量
//    private static volatile boolean run = true; //加了 volatile 之后，程序就结束了
    private static boolean run = true; //加了 volatile 之后，程序就结束了

    //定义一个锁对象
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        //开启两个线程，其中一个线程默认一直执行
        //第二个线程来改变共享变量的值
        Thread t1 = new Thread(() -> {
            //基于共享变量来决定线程是否继续执行
            while (run) {
                //关键性代码，解决了可见性问题
//                synchronized (lock){
//                }
                //看看源码一目了然！也清晰了为什么在程序不建议大量采用这类传统的输出
                System.out.println("t1 run.....");

            }
        });
        t1.start();

        System.out.println("t1 start....");

        Thread.sleep(100);

        Thread t2 = new Thread(() -> {
            //改变共享变量的值，希望可以停止线程1
            run = false;
            System.out.println("t2 set run false!");
        });

        t2.start();
        System.out.println("t2 start...");

    }
}
