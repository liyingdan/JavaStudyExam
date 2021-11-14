package com.janet.communication;

import java.util.concurrent.CountDownLatch;

/**
 * @Description 倒数门闩 - 多等一
 * @Author Janet
 * @Date 2021-09-20
 */
public class CountDownLatchTest2 {
    public static void main(String[] args) throws InterruptedException {
        /*
        * 1.创建倒数门闩对象，倒数计数器应该是1
         * */
        CountDownLatch countDownLatch = new CountDownLatch(1);

        /*
         * 2.模拟一堆用户进行等待状态
         * */
        for (int i = 1; i<= 10; i++){
            int finalI = i;
            new Thread(() -> {
                System.out.println("用户"+ finalI +"已到达");

                //此时还没开门，需要等待
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("用户"+ finalI +"开始抢购商品");

            }).start();
        }

        /*
         * 3.条件齐了，倒数器减1
         * */
        System.out.println("管理员等待时间是否到了8点整");
        Thread.sleep(9000);
        System.out.println("时间到点了，开门做生意！");
        countDownLatch.countDown();

    }
}
