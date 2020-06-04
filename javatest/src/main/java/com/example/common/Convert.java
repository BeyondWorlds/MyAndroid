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

    /**
     * int转16进制字符串
     * @param a
     * @return
     */
    public static String intToHex(int a){
        return Integer.toHexString(a);
    }

    /**
     * byte数组转16进制字符串
     * @param bytes
     * @return
     */
    public static String byteToHex(byte bytes[]) {
        if ((bytes == null) || (bytes.length == 0)) {
            return "";
        }
        StringBuffer sb = new StringBuffer(bytes.length * 2);
        for (int index = 0; index < bytes.length; index++) {
            int bt = bytes[index] & 0xff;
            if (bt < 0x10) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(bt).toUpperCase());
        }
        return sb.toString();
    }
}
