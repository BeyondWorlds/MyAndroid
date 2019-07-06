package com.wq.manager;

import java.util.Random;

/**
 * Created by ${wq} on 2017/6/14.
 */

public class CommonTool {

    /**
     * 生成随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    /**
     * 取反
     */
    public static void takeBack(int i)
    {
        String s2=Integer.toBinaryString(~i);
    }

}
