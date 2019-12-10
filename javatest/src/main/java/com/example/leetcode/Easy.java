package com.example.leetcode;

import java.util.HashMap;

public class Easy {
    public static void main(String[] args) {
        System.out.println(isValid("([]){[]}"));
    }

    /**
     *  20.有效括号
     *  给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     * @param s
     * @return
     */
    public static boolean isValid(String s) {

        if (s == null || s.length() % 2 == 1) {
            return false;
        }
        if (s.length() == 0) {
            return true;
        }
        HashMap map = new HashMap();
        map.put(")", "(");
        map.put("]", "[");
        map.put("}", "{");
        int length = s.length();
        StringBuffer sb = new StringBuffer();
        sb.append(s.substring(0, 1));
        for (int i = 1; i < length; i++) {
            String c;
            if (i == length - 1) {
                c = s.substring(i);
            } else {
                c = s.substring(i, i + 1);
            }
            int sbSize = sb.toString().length();
            if (sbSize > 0) {
                if (sb.toString().substring(sbSize - 1).equals(map.get(c))) {
                    sb.deleteCharAt(sbSize - 1);
                } else {
                    sb.append(c);
                }
            } else {
                sb.append(c);
            }

        }
        if (sb.toString().length() > 0) {
            return false;
        }
        return true;
    }


    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[index] != nums[i]) {
                index++;
                nums[index] = nums[i];
            }
        }
        return index+1;
    }
}
