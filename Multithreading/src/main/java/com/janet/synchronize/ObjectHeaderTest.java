package com.janet.synchronize;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Description TODO
 * @Author Janet
 * @Date 2021-08-26
 */
public class ObjectHeaderTest {
    public static void main(String[] args) {
        MyObject myObject = new MyObject();

        myObject.hashCode();
        //将hashcode转换为16进制输出，方便一会做对比
//        System.out.println("16进制hashcode:     "+Integer.toHexString(myObject.hashCode()));

        System.out.println(ClassLayout.parseInstance(myObject).toPrintable());

        //打开偏向锁机制： -XX:BiasedLockingStartupDelay=0
//        long start = System.currentTimeMillis();
//        long sum = 0;
//        for (int i = 0; i < 10000000; i++) {
            synchronized (myObject) {
                //只有一个主线程在运行
                //实际上不存在锁竞争的问题
                //改进：让线程获取到锁代价更低，所以就产生了偏向锁
                System.out.println(ClassLayout.parseInstance(myObject).toPrintable());
//                sum += i;
            }
//        }
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);//偏向锁 17 //轻量级锁 197  （关掉-XX:BiasedLockingStartupDelay=0设置就是轻量级锁）
    }
}
