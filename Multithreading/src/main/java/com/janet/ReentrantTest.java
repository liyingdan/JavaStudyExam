package com.janet;

/**
 * @Description 示例什么是可重入性
 * @Author Janet
 * @Date 2021-08-09
 */
public class ReentrantTest {
    public static void main(String[] args) {
        new Thread(new MyRunnale()).start();
        new Thread(new MyRunnale()).start();
    }

    static class MyRunnale implements Runnable {
        @Override
        public void run() {
            synchronized (MyRunnale.class) {//t1 t2
                System.out.println(Thread.currentThread().getName() + ",01");//t1
                synchronized (MyRunnale.class) {//t1
                    System.out.println(Thread.currentThread().getName() + ",02");
                }
            }
        }
    }

}
