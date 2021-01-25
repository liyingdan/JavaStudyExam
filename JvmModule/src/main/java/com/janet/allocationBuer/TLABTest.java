package com.janet.allocationBuer;

/**
 * @Description TODO
 * @Author Janet
 * @Date 2021-01-17
 */
public class TLABTest {
    private static Object o;
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000_00; i++) {
            o = new Object();
        }
        long end = System.currentTimeMillis();
        //Hotspot默认是开启了 TLAB 优化的措施，开启消耗时间：530
        //关闭后：-XX:-UseTLAB
        //消耗时间：1380
        System.out.printf("消耗时间：%s",end-start);
    }
}
