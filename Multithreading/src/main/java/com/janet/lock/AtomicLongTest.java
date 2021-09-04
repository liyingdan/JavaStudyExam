package com.janet.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description 比较AtomicLong和LongAdder的性能差异之AtomicLong
 * @Author Janet
 * @Date 2021-09-04
 */
public class AtomicLongTest {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        AtomicLong atomicLong = new AtomicLong();
        /*
        Thread thread = new Thread(new CounterTask(atomicLong));
        thread.start();
        thread.join();
        */
        List<Thread> threads = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new CounterTask(atomicLong));
            thread.start();
            threads.add(thread);
        }
        //保证子线程执行完之后输入主线程的结果
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(atomicLong.get());

        long end = System.currentTimeMillis();
        System.out.println(end - start); //1727
    }

    static class CounterTask implements Runnable {
        private AtomicLong atomicLong;

        public CounterTask(AtomicLong atomicLong) {
            this.atomicLong = atomicLong;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                atomicLong.incrementAndGet();
            }
        }
    }

}
