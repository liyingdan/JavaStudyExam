package com.janet.jvmTest;

/**
 * @Description 设置堆堆大小
 * 建议：-Xms10M -Xmx10M 两者设置为一样大
 *
 * @Author Janet
 * @Date 2021-05-18
 */
public class HeapTest {
    public static void main(String[] args) {
        System.out.println("老年代/新生代="+7168/2560);
        System.out.println("Eden/Surivor="+2048/512);

        //为什么是9M？而不是10M?
        System.out.println("当前堆的最大可用内存："+Runtime.getRuntime().maxMemory()/1024/1024); //Runtime.getRuntime().maxMemory()拿到的是字节，要转换为M

        //因为新生代中，我们有一块的Survivor区是空闲状态（复制算法用到的另一半survivor）
        System.out.println("当前堆的最大可用内存："+ (Runtime.getRuntime().maxMemory()+512*1024)/1024/1024);








    }




}
