package com.example.test;

/**
 * @author wq
 * @date 2019/1/8
 */
public class Test {
    public static void main(String[] args) {
//        int[] a = {20, 16, 33, 24, 8, 66, 19, 28, 21, 7, 17};
//        Sort.sortBySelect(a);
//        for (int i = 0; i < a.length; i++) {
//            System.out.println(a[i]);
//        }
        int x1 = ((0xff << 8) & 0xffff) + (0xfb & 0xffff);
        int y1 = ((0xff << 8) & 0xffff);
        int z1 = (0xfb & 0xffff);
        int d1 = ~((y1 + z1) - 1) & 0xffff;
        int a1 = ~(x1 - 1) & 0xffff;
        System.out.println(x1);
        System.out.println(y1);
        System.out.println(z1);
        System.out.println(y1 & z1);
        System.out.println(d1);
        System.out.println(a1);
    }
}
