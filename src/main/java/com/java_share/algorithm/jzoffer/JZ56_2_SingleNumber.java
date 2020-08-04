package com.java_share.algorithm.jzoffer;

import java.util.Arrays;

/**
 * @author yz
 * @date 2020-07-20 17:52
 * <p>
 *      剑指 Offer 56 - II. 数组中数字出现的次数 II
 *
 *      在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。
 *      请找出那个只出现一次的数字。
 *
 *      思路:
 *      1 1 1
 *      1 1 1
 *      1 1 1
 *    1 1 0 1   单独的数s
 *  = 1 4 3 4   bit位相加
 *
 *    1 1 0 1   对bit累加出现次数取3的余数, 刚好是s
 *
 * </p>
 **/
public class JZ56_2_SingleNumber {

    public static void main(String[] args){
        int[] nums = {4,4,4,2,2,2,88888, 44444, 44444, 44444};
        System.out.println(new Solution().singleNumber(nums));
    }

    static class Solution{

        public int singleNumber(int[] nums) {
            int[] counts = new int[32];
            for (int num : nums) {
                for (int i = 0; i < 32; i++) {
                    counts[i] += (num & 1);
                    num >>= 1;
                }
            }
            int sum = 0, times = 3;
            for (int i = 0; i < 32; i++)
                if (counts[i] % times == 1) sum += 1<<i;
            return sum;
        }

    }



}
