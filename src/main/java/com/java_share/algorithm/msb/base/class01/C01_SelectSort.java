package com.java_share.algorithm.msb.base.class01;

import java.util.Arrays;

/**
 * @author yz
 * @date 2020-08-10 23:04
 * <p>
 *     选择排序
 * </p>
 **/
public class C01_SelectSort {

    public static void main(String[] args){
        int[] nums = {3,8,12,7,6,18,5,1};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void sort(int[] nums) {
        if (nums==null || nums.length==0) return;
        for (int i = 0; i < nums.length; i++) {
            int min = i, tmp = nums[i];
            for (int j = i + 1; j < nums.length; j++)
                if (nums[j] < nums[min]) min = j;
            nums[i] = nums[min];// 交换
            nums[min] = tmp;
        }
    }


}
