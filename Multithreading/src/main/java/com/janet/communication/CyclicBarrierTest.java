package com.janet.communication;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description TODO
 * @Author Janet
 * @Date 2021-09-15
 */
public class CyclicBarrierTest {
    public static void main(String[] args){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4,()->{
            System.out.println("凑齐4人开一桌！");
        });

        for (int i = 0; i < 12; i++) {
            new Thread(new MaJiangTask(i, cyclicBarrier)).start();
        }
    }


    private static class MaJiangTask implements Runnable{
        private int id;
        private CyclicBarrier cyclicBarrier;
        public MaJiangTask(int id,CyclicBarrier cyclicBarrier){
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("编号"+id+"正在飞奔赶往现场");
            try {
                Thread.sleep((long) (Math.random()*10000));
                System.out.println("编号"+id+"已经达到了现场，打卡成功！");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
