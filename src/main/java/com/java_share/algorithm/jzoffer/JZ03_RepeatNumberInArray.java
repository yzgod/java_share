package com.java_share.algorithm.jzoffer;

import java.util.HashSet;

/**
 * @author yz
 * @date 2020-07-14 10:15
 * <br>
 *      3.找出数组中重复的数字。
 *      在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 *      数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个
 *      数字重复了几次。请找出数组中任意一个重复的数字。
 *
 *      来源：力扣（LeetCode）
 *      链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 * </br>
 * <br>
 *      hash表,碰撞之后就返回重复的数字,最坏的情况下时间空间复杂度均为O(n)
 * </br>
 **/
public class JZ03_RepeatNumberInArray {

    public static void main(String[] args){
        int[] nums = {1,2,3,4,3,2,5};
        System.out.println(new Solution().findRepeatNumber(nums));
    }

    static class Solution {
        public int findRepeatNumber(int[] nums) {
            HashSet<Integer> set = new HashSet<>(nums.length);
            for (int num : nums) {
                // 添加不进hash冲突
                if (!set.add(num))
                    return num;
            }
            return -1;
        }
    }

}
