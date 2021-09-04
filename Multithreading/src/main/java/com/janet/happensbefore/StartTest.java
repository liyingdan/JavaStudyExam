package com.janet.happensbefore;

/**
 * @Description TODO
 * @Author Janet
 * @Date 2021-08-08
 */
public class StartTest {
    public static void main(String[] args){

        int a = 666;

        //创建了一个子线程
        new Thread(()->{
            System.out.println(a);
        }).start();

    }
}
