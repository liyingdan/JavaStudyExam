package com.janet.lock;

import java.util.concurrent.atomic.LongAdder;

/**
 * @Description 比较AtomicLong和LongAdder的性能差异之LongAdder
 * @Author Janet
 * @Date 2021-09-04
 */
public class LongAdderTest {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        LongAdder longAdder = new LongAdder();

        Thread thread = new Thread(new CounterTask(longAdder));
        thread.start();
        thread.join();
        /*
        List<Thread> threads = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
        Thread thread = new Thread(new CounterTask(longAdder));
        thread.start();
        threads.add(thread);
        }
        for (Thread thread : threads) {
        thread.join();
        }
        */
        //求和
        System.out.println(longAdder.sum());

        long end = System.currentTimeMillis();
        System.out.println(end - start);//700 2450 1094 231 2180 1384
    }

    static class CounterTask implements Runnable {
        LongAdder longAdder;

        public CounterTask(LongAdder longAdder) {
            this.longAdder = longAdder;
        }

        @Override
        public void run() {
            for (int i = 0; i < 200000000; i++) {
                longAdder.increment();
            }
        }
    }

    //结论
    //单线程 AtomicLong性能表现比LongAdder要好
    //多线程 AtomicLong性能表现比LongAdder要差


}
