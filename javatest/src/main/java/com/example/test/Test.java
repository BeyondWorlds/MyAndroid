package com.example.test;

import com.example.sort.Sort;

/**
 * @author wq
 * @date 2019/1/8
 */
public class Test {
    public static void main(String[] args) {
        int[] a = {20, 16, 33, 24, 8, 66, 19, 28, 21, 7, 17};
        Sort.sortBySelect(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
