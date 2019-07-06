package com.example.datastructure.leetcode;

/**
 * Created by {wq} on 2018/5/2.
 */

public class LeetCode {
    public static int[] solve(int[] nums, int tartet) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[i] + nums[j] == tartet)) {
                    return new int[]{nums[i], nums[j]};
                }
            }
        }
        return null;
    }

    public static void main(String args[]) {

    }
}
