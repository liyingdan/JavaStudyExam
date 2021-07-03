package com.janet.tools;

import java.util.concurrent.TimeUnit;

/**
 * @Description 测试jstat命令使用方式以及内存使用情况
 * @Author Janet
 * @Date 2021-06-01
 */
public class JpsTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("hello");
        while (true){
            byte[] bs = new byte[1024*1024];
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
