package com.java_share.algorithm.jzoffer;

import java.util.Arrays;

/**
 * @author yz
 * @date 2020-07-15 13:39
 * <br>
 *      剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 *  输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 *  使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 * </br>
 * <br>
 *  双指针
 * </br>
 **/
public class JZ21_Exchange {

    public static void main(String[] args){
        int[] nums = {1,3,5,6,7,9,8,5,10,11};
        int[] exchange = new Solution().exchange(nums);
        System.out.println(Arrays.toString(exchange));
    }

    static class Solution {

        public int[] exchange(int[] nums) {
            int l = 0, r = nums.length -1;
            while (l < r){
                while (nums[r]%2==0 && r > l) r--;
                while (nums[l]%2==1 && l < r) l++;
                if(l == r) break;
                // 此时左边为偶数,右边为奇数,交换
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
            }
            return nums;
        }

    }
}
