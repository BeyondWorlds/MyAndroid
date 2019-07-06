package com.example.jvm;

import java.lang.reflect.Field;
import java.util.Vector;

/**
 * @author wq
 * @date 2018/10/19
 */

public class JvmTest {

    static int a = 10;
    int b = 11;

    static {
        System.out.println("init static ");
        System.out.println("a=" + a);
        // System.out.println(b);
    }

    public static void main(String[] args) {
        System.out.println("Hello World ");
//        Student student = new Student();
//        student = null;
//        student = new Student();
        getLoaderClass();
        System.out.println("age="+Student.age);
        getLoaderClass();
    }

    /**
     * 获取加载的类
     */
    public static void getLoaderClass() {
        try {
            Field f = ClassLoader.class.getDeclaredField("classes");
            f.setAccessible(true);
            Vector classes = (Vector) f.get(ClassLoader.getSystemClassLoader());
            System.out.println(classes);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
