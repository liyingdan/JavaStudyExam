package com.janet.gc;

import java.lang.ref.WeakReference;

/**
 * @Description 弱引用
 * @Author Janet
 * @Date 2021-01-30
 */
public class WeakReferenceTest {
    public static void main(String[] args) {
        //1. 创建一个强引用对象
        Girl girl = new Girl(18, "Janet");

        //2. 创建一个弱引用
        WeakReference<Girl> weakReference = new WeakReference<>(girl);

        //3. 将强引用设置为 null，确保只有弱引用
        girl = null;

        //4. 看 gc 前效果
        System.out.println("before gc.................");
        System.out.println(weakReference.get());

        //5. 执行一次主动 GC
        System.gc();
        System.out.println("after gc....................");
        //无论内存够不够，弱引用都会被回收
        System.out.println(weakReference.get());
    }
}
