package com.wq.manager;

/**
 * Created by ${wq} on 2017/5/31.
 */

public class ChangeManager {

    /**
     * Convert any type to  string
     */
    public static String toString(Object o) {
        return String.valueOf(o);
    }

    /**
     * The contents of string must be numeric types
     * parse方法和valueOf方法的区别就是返回值的类型，一个返回基本数据类型，一个返回包装类型（比如int和Integer）
     *
     * @param s
     * @return
     */
    public static int string2int(String s) {
        Byte.parseByte(s);
        Long.parseLong(s);
        Float.parseFloat(s);
        Double.parseDouble(s);
        Integer.parseInt(s);
        return Integer.valueOf(s);
    }

    /**
     * byte转16进制
     *
     * @param b
     * @return
     */
    public static String byte2HexStr(byte[] b) {

        String stmp = "";
        StringBuilder sb = new StringBuilder("");
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            sb.append((stmp.length() == 1) ? "0" + stmp : stmp);
            sb.append(" ");
        }
        return sb.toString().toUpperCase().trim();
    }


    //把进制数为radix的s转换为10进制数
    private void parseInt(String s, int radix) {
        int a = Integer.parseInt(s, radix);
        //for example
        String b = "10";
        int a1 = Integer.parseInt(b, 2);
        int a2 = Integer.parseInt(b, 8);
        int a3 = Integer.parseInt(b, 10);
        int a4 = Integer.parseInt(b, 16);
        // a1=2  a2=12 a3=10  a4=0xA
        //Integer.parseInt(b,10)=Integer.parseInt(b);默认进制数为10
    }
}
