package com.java_share.algorithm.msb.base.class01;

import java.util.Arrays;

/**
 * @author yz
 * @date 2020-08-10 23:04
 * <p>
 *     冒泡排序
 * </p>
 **/
public class C02_BubbleSort {

    public static void main(String[] args){
        int[] nums = {3,8,12,7,6,18,5,1,0,1,5};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }


    private static void sort(int[] nums) {
        if (nums == null || nums.length == 0) return;
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++)
                if (nums[j] > nums[j+1])
                    swap(nums, j);
        }
    }



    private static void swap1(int[] nums, int i) {
        int tmp = nums[i];
        nums[i] = nums[i+1];
        nums[i+1] = tmp;
    }

    // 或运算的定理 a ^ b ^ b = a;
    private static void swap(int[] nums, int i) {
        nums[i]   = nums[i] ^ nums[i+1];// a = a ^ b
        nums[i+1] = nums[i] ^ nums[i+1];// b = (a ^ b) ^ b = a (满足交换)
        nums[i]   = nums[i] ^ nums[i+1];// a = (a=a^b) ^ (b=a) = a ^ b ^ a = b (满足交换)
    }

    private static void sort1(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int last = nums.length - 1;
        while (last > 0) {
            for (int i = 0; i < last; i++) {
                if (nums[i] > nums[i+1]) {
                    swap(nums, i);
                }
            }
            last --;
        }
    }


}
