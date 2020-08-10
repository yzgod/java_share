package com.java_share.algorithm.msb.base.class01;

import java.util.Arrays;

/**
 * @author yz
 * @date 2020-08-10 23:04
 * <p>
 *     插入排序
 * </p>
 **/
public class C03_InsertSort {

    public static void main(String[] args){
        int[] nums = {3,8,12,7,6,18,5,1,0,1,5};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }


    private static void sort(int[] nums) {
        if (nums == null || nums.length == 0) return;
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0 && nums[j] < nums[j-1]; j--)
                swap(nums, j-1); // 比前面小交换
        }
    }

    // 或运算的定理 a ^ b ^ b = a;
    private static void swap(int[] nums, int i) {
        nums[i]   = nums[i] ^ nums[i+1];// a = a ^ b
        nums[i+1] = nums[i] ^ nums[i+1];// b = (a ^ b) ^ b = a (满足交换)
        nums[i]   = nums[i] ^ nums[i+1];// a = (a=a^b) ^ (b=a) = a ^ b ^ a = b (满足交换)
    }


}
