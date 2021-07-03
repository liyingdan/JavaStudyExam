package com.janet.gc;

import java.lang.ref.SoftReference;

/**
 * @Description 软引用
 * @Author Janet
 * @Date 2021-01-30
 *
 * -Xms10m -Xmx10m -XX:+PrintGC
 */
public class SoftRefenceTest {
    public static void main(String[] args) {
        //1. 创建一个强引用对象
        Girl girl = new Girl(18, "Janet");

        //2. 创建一个软引用
        SoftReference<Girl> girlSoftReference = new SoftReference<>(girl);

        //3. 将强引用设置为 null，确保只有软引用
        girl = null;

        //4. 看 gc 前效果
        System.out.println("before gc.................");
        System.out.println(girlSoftReference.get());

        //5. 执行一次主动 GC
        System.gc();
        System.out.println("after gc....................");
        // 当内存足够的时候，软引用关联的对象是不会被回收的
        System.out.println(girlSoftReference.get());

        try {
            //模拟内存不足的情况
            byte[] size = new byte[30*1024*1024];
        } catch (Throwable e) {
            System.out.println("after OOM..............");
            System.out.println(girlSoftReference.get());
        }

    }
}
