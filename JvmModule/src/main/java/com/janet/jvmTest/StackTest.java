package com.janet.jvmTest;

/**
 * @Description 测试 栈的大小会影响到方法调用的深度
 * @Author Janet
 * @Date 2021-06-01
 */
public class StackTest {
    /**
     * 记录方法调用的最大深度
     */
    private static int count = 0;

    /**
     * 编写一个递归调用自身的方法
     */
    public static void recursion(int a,int b,int c) {
        count++;
        recursion(a,b,c);
    }

    /**
     * java.lang.StackOverflowError
     */
    public static void main(String[] args) {
        try {
            recursion(1,2,3);
        } catch (Throwable e) {
            //-Xss128k count=1097
            //-Xss256k count=3092
            System.out.println("count=" + count);
            e.printStackTrace();
        }
    }

}
