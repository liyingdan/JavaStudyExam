package com.janet.loadclass.classLoader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Description 自定义类加载器
 * @Author Janet
 * @Date 2021-01-10
 */
public class MyClassLoader extends ClassLoader {
    //指定从哪加载类
    private String classPath;

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }


    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                if (name.startsWith("com.janet")) {
                    //如果是我们自己项目自定义的类，则交给自己来加载
                    c = findClass(name);
                } else {
                    //交给父类加载器去加载
                    //保证核心类库的唯一性
                    c = this.getParent().loadClass(name);
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
//        return super.loadClass(name, resolve);
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        //1.根据类全名获取其对应的字节数组
        byte[] data = loadByte(className);
        //2.通过JDK提供的API，将字节数组转换为 Class 对象
        return defineClass(className, data, 0, data.length);
    }

    private byte[] loadByte(String className) {
        //1.将类全名转换为完整的类路径
        //om.janet.loadclass.Order   om/janet/loadclass/Order
        className = className.replaceAll("\\.", "/");
        //d://com/zengzhi/Order.class
        StringBuilder stringBuilder = new StringBuilder(classPath);
        stringBuilder.append(className);
        stringBuilder.append(".class");

        //2.采用文件字节流读取文件内容,并将其转换为字节数组
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(stringBuilder.toString());

            //3.创建字节数组，用于存放文件内容
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            return data;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //返回字节数组
        return null;
    }

}
