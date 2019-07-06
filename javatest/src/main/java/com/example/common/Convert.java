package com.example.common;

/**
 * Created by {wq} on 2018/6/21.
 */

public class Convert {

    public static byte int2Byte(int a) {
        return (byte) a;
    }

    public static int byte2Int(byte a) {
        return a & 0xff;
    }
}
