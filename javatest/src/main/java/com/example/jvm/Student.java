package com.example.jvm;

/**
 * @author wq
 * @date 2018/10/18
 */
public class Student {
    String name;
    static int age=26;

    static {
        System.out.println("init stutent static");
    }

    public static void getName() {
        System.out.println("Student");
    }
}
