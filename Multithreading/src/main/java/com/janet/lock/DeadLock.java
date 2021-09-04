package com.janet.lock;

/**
 * @Description 演示死锁问题
 * @Author Janet
 * @Date 2021-08-29
 */
public class DeadLock {
    //A线程拥有资源a，等待资源b，且不释放资源a；B线程拥有资源b，等待资源a，且不释放资源b；系统中没有任何一方愿意主动放弃已有资源

    /**
     * 创建两个锁资源
     */
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args){

        //启动两个线程去争抢lock1和lock2资源
        new Thread(new DeadLockTask(1)).start();
        new Thread(new DeadLockTask(0)).start();

    }

    static class DeadLockTask implements Runnable{
        private int flag;

        public DeadLockTask(int flag){
            this.flag = flag;
        }

        @Override
        public void run() {
            if (flag==1) {
                //路线1，先争抢lock1，再争抢lock2
                synchronized (lock1){
                    System.out.println(Thread.currentThread().getName()+":获取到lock1");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2){
                        System.out.println(Thread.currentThread().getName()+":获取到lock2");
                    }
                }
            }else{

                //路线2，先争抢lock2，再争抢lock1
                synchronized (lock2){
                    System.out.println(Thread.currentThread().getName()+":获取到lock2");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock1){
                        System.out.println(Thread.currentThread().getName()+":获取到lock1");
                    }
                }
            }
        }
    }

}
