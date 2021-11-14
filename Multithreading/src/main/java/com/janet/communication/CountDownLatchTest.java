package com.janet.communication;

import java.util.concurrent.CountDownLatch;

/**
 * @Description 倒数门闩 - 一等多
 * @Author Janet
 * @Date 2021-09-20
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {

        /*
        * 1. 创建倒数门闩对象，设置其倒数计数器为3
        * */
        CountDownLatch countDownLatch = new CountDownLatch(3);

        /*
        * 2. 模拟三个用户陆续到场
        * */
        for (int i = 1; i<= 3; i++){
            int finalI = i;
            new Thread(() -> {
                try {
                    Thread.sleep((long)(Math.random()*10000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("用户"+ finalI +"已经参团");
                // 2.1 倒数计数器减1
                countDownLatch.countDown();
            }).start();
        }

        /*
         * 3.模拟平台等待倒数计数器为0，则开团
         * */
        System.out.println("等待三人组团成功中...");

        countDownLatch.await();

        System.out.println("三人组团成功，开团出发到长城！");

    }
}
