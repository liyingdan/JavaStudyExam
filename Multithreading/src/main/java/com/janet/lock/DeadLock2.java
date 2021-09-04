package com.janet.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 使用超时机制来解决死锁问题
 * @Author Janet
 * @Date 2021-08-30
 */
public class DeadLock2 {
    /*A线程拥有资源a，等待资源b，且不释放资源a；B线程拥有资源b，等待资源a，且不释放资源b；系统中没有任何一方愿意主动放弃已有资源*/

    /**
     * 改变1：创建两个锁资源
     */
    private static Lock lock1 = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();

    /*
    * 增加随机因素
    * */
    private static Random random = new Random();

    public static void main(String[] args) {
        //启动两个线程去争抢lock1和lock2资源
        new Thread(new DeadLockTask(1)).start();
        new Thread(new DeadLockTask(0)).start();
    }

    static class DeadLockTask implements Runnable {
        private int flag;

        public DeadLockTask(int flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            while (true){
                if (flag == 1) {
                    //路线1，先争抢lock1，再争抢lock2
                    //改变2：synchronized->lock
                    try {
                        //改变3：设置不同的等待时间
                        int randomNum = random.nextInt(100); //产生100以内的随机数
                        if (lock1.tryLock(randomNum, TimeUnit.MILLISECONDS)) {
                            System.out.println(Thread.currentThread().getName() + ":获取到lock1");
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            randomNum = random.nextInt(100); //产生100以内的随机数
                            if (lock2.tryLock(randomNum, TimeUnit.MILLISECONDS)) {
                                System.out.println(Thread.currentThread().getName() + ":获取到lock2");
                                lock1.unlock();
                                lock2.unlock();
                                break;
                            }else {
                                System.out.println(Thread.currentThread().getName() + ":由于获取lock2超时，所以主动释放lock1");
                                lock1.unlock();
                            }
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    //路线2，先争抢lock2，再争抢lock1
                    try {
                        int randomNum = random.nextInt(100); //产生100以内的随机数
                        if (lock2.tryLock(randomNum, TimeUnit.MILLISECONDS)) {
                            System.out.println(Thread.currentThread().getName() + ":获取到lock2");
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            randomNum = random.nextInt(100); //产生100以内的随机数
                            if (lock1.tryLock(randomNum, TimeUnit.MILLISECONDS)) {
                                System.out.println(Thread.currentThread().getName() + ":获取到lock1");
                                lock1.unlock();
                                lock2.unlock();
                                break;
                            }else {
                                System.out.println(Thread.currentThread().getName() + ":由于获取lock1超时，所以主动释放lock2");
                                lock2.unlock();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}



