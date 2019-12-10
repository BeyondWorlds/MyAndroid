package com.example;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by {wq} on 2018/6/28.
 */

public class Test {

    public static void main(String[] args) {
//        String[] test = {"flow", "flower", "flay"};
        String[] test = {"a", "b", "c"};
        String result = "a";
        System.out.println(result.substring(0, 1));
//        System.out.println( longestCommonPrefix(test));
    }

    public static String longestCommonPrefix(String[] strs) {

        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        if (strs[0].equals("")) {
            return "";
        }
        String result = strs[0];
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].indexOf(result) != 0) {
                result = result.substring(0, result.length() - 1);
                if(result.isEmpty()) return "";
            }

        }
        return "";
    }
}
