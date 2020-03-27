package com.example.test;

/**
 * @author wq
 * @date 2019/1/8
 */
public class Man {
    public void run(Animal animal) {
        float weight = byteToShort(new byte[]{0x11, (byte)0xBC}) / 10.0f;
    }

    /**
     * 注释：字节数组到short的转换！
     *
     * @param b
     * @return
     */
    public static int byteToShort(byte[] b) {
        int s = 0;
        int s0 = (b[1] & 0xff);// 最低位
        int s1 =  (b[0] & 0xff);
        s1 <<= 8;
        s =  (s0 | s1);
        return s;
    }
}
