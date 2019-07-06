package com.wq.manager;

/**
 * Created by ${wq} on 2017/6/16.
 */

public class StringManager {

    public static void main(String[] args) {
        //subString
        String s = "abc-def-ghi-jkl-m";
        System.out.println(s.substring(2));
        System.out.println(s.substring(0, 5));
        //split
        String sp[] = s.split("-");
        System.out.println(sp.length);
    }
}
