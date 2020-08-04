package com.java_share.algorithm.jzoffer;

import java.util.Arrays;

/**
 * @author yz
 * @date 2020-07-20 17:52
 * <p>
 *      剑指 Offer 57. 和为s的两个数字
 *  输入一个递增排序的数组和一个数字s，在数组中查找两个数，
 *  使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 *
 * </p>
 **/
public class JZ57_1_Sorted2Sum {

    public static void main(String[] args){
        int[] nums = {2, 6, 11, 15 , 18 , 20};
        int[] twoSum = new Solution().twoSum(nums, 26);
        System.out.println(Arrays.toString(twoSum));
    }

    static class Solution{

        //因为排序了,直接双指针解法时间O(n)
        public int[] twoSum(int[] nums, int target) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int res = nums[l] + nums[r];
                if (res == target) return new int[]{nums[l], nums[r]};
                else if (res > target) r --;
                else l ++;
            }
            return null;
        }

    }



}
