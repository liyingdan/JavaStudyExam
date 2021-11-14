package com.janet.communication;

import java.util.concurrent.Semaphore;

import static io.micrometer.core.instrument.Timer.start;

/**
 * @Description TODO
 * @Author Janet
 * @Date 2021-09-12
 */
public class SemaphoreTest {
    public static void main(String[] args) {

        /**
         * 1.创建一个信号量对象，并且许可证数量初始化为3
         */
        Semaphore semaphore = new Semaphore(3, true);

        /**
         * 模拟6个请求
         */
        for (int i = 0; i < 6; i++){
            new Thread(() -> {
                //获取到许可证
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "：获取到许可证");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + ":释放许可证");

                semaphore.release();
            }).start();
        }

    }
}