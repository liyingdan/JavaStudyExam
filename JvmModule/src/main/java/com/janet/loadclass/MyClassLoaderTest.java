package com.janet.loadclass;

import com.janet.loadclass.classLoader.MyClassLoader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description TODO
 * @Author Janet
 * @Date 2021-01-10
 */
public class MyClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //1.创建 ClassLoader对象，加载版本 1 的 Order
        MyClassLoader myClassLoader1 = new MyClassLoader("d://version1//");
        Class<?> clazz1 = myClassLoader1.loadClass("com.janet.loadclass.Order");
        System.out.println(clazz1.getClassLoader());
        Method hello = clazz1.getDeclaredMethod("hello");
        hello.invoke(clazz1.newInstance());

        //2.创建 ClassLoader 对象，加载版本 2 的 Order
        MyClassLoader myClassLoader2 = new MyClassLoader("d://version2//");
        Class<?> clazz2 = myClassLoader2.loadClass("com.janet.loadclass.Order");
        System.out.println(clazz2.getClassLoader());
        Method hello2 = clazz2.getDeclaredMethod("hello");
        hello2.invoke(clazz2.newInstance());
    }

}
