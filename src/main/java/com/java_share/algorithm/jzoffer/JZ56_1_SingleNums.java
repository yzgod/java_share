package com.java_share.algorithm.jzoffer;

import java.util.Arrays;

/**
 * @author yz
 * @date 2020-07-20 17:52
 * <p>
 *      剑指 Offer 56 - I. 数组中数字出现的次数
 *
 *      一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
 *      请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，
 *      空间复杂度是O(1)。
 *
 * 抑或规则:bit相同为0,不同为1
 *      a ^ a = 0;
 *      0 ^ b = b;
 *      a ^ b ^ a = b;
 *
 * 假设两个不同数为r1,r2
 *      1 1 0 0 0   r1
 *    ^ 1 0 1 0 0   r2
 *    = 0 1 1 0 0   r1^r2
 * 可以
 *    & 0 0 1 0 0   bit   左移找第一位1即是bit, 将所有数包括r1,r2 & bit
 *    分为两组, 分成的两组数,各包含r1,r2,其余数均两两相同,分别^后即为r1,r2
 *
 * </p>
 **/
public class JZ56_1_SingleNums {

    public static void main(String[] args){
        int[] nums = {4,1,4,6,5,3,1,6,2,5};
        int[] numbers = new Solution().singleNumbers(nums);
        System.out.println(Arrays.toString(numbers));
    }

    static class Solution{

        public int[] singleNumbers(int[] nums) {
            int r1r2 = 0, bit = 1, r1 = 0, r2 = 0;
            for (int num : nums) r1r2 ^= num; // r1 ^ r2
            while ((r1r2 & bit) == 0) bit <<= 1;// 求bit
            for (int num : nums) {
                if ((bit & num) == 0) r1 ^= num; // & bit分组求r1,r2
                else r2 ^= num;
            }
            return new int[]{r1, r2};
        }

    }



}
