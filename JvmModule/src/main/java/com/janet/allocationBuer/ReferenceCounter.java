package com.janet.allocationBuer;

/**
 * @Description TODO
 * @Author Janet
 * @Date 2021-01-25
 */
public class ReferenceCounter {
    private Object instance = null;

    //设置创建对象需要占据的内存，默认给 1 M，方便观察 GC
    private byte[] size = new byte[1024*1024];

    public static void main(String[] args) {
        // 1. 创建两个对象
        ReferenceCounter a = new ReferenceCounter();
        ReferenceCounter b = new ReferenceCounter();

        //2. 相互引用，形成循环
        a.instance = b;
        b.instance = a;

        // 3. 将对象设置为 null
        a = null;
        b = null;

        // 4. 主动 GC 操作，观察效果
        System.gc();
        System.out.println(" GC 完毕");

    }
}
