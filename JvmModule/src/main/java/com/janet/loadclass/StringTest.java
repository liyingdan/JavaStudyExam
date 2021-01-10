package com.janet.loadclass;

/**
 * @Description TODO
 * @Author Janet
 * @Date 2021-01-10
 */
public class StringTest {
    public static void main(String[] args) {
        String string = new String();

//        //null,表示由 BootstrapClassLoader负责加载，而 BootstrapClassLoader 是由 C++ 写的，所以在 Java 代码中无法获取
//        System.out.println(string.getClass().getClassLoader()); //null
//
////         自定义类由 AppClassLoader 加载
//        Order order = new Order();
//        System.out.println(order.getClass().getClassLoader()); // sun.misc.Launcher$AppClassLoader@58644d46
//
//        ClassLoader classLoader = order.getClass().getClassLoader();
////        ExtClassLoader
//        System.out.println(classLoader.getParent()); //sun.misc.Launcher$ExtClassLoader@4554617c
////        null(BootstrapClassLoader)
//        System.out.println(classLoader.getParent().getParent()); //null
    }
}
