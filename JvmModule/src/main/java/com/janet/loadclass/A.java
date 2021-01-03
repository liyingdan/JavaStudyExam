package com.janet.loadclass;

/**
 * @Description 类加载的准备阶段
 * @Author Janet
 * @Date 2021-01-02
 */
public class A {
    //加载类的初始化阶段执行静态代码块
    static {
        System.out.println("A static");
    }

    public A() {
        System.out.println("A construct");
    }
}

class B extends A {
    static {
        System.out.println("B static");
    }

    public B() {
        System.out.println("B construct");
    }

    public static void main(String[] args) {
        //类加载完毕之后，基于Class对象去才会创建AB对象
        A a = new B();
        a = new B();
    }

}
