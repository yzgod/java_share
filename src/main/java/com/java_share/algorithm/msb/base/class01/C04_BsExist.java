package com.java_share.algorithm.msb.base.class01;

import java.util.Arrays;

/**
 * @author yz
 * @date 2020-08-10 23:04
 * <p>
 *     1. 在有序数组中,查找某个数是否存在.
 * </p>
 **/
public class C04_BsExist {

    public static void main(String[] args){
        int[] nums = {1, 2, 4, 5, 8, 9, 11, 18};
        System.out.println(exist(nums, 6));
    }


    public static boolean exist(int[] nums, int tar) {
        if (nums == null || nums.length == 0) return false;

        int l = 0, r = nums.length -1;
        while (l <= r) {
            int m = (l + r)/2;
            if (nums[m] > tar)  r = m - 1;
            else if (nums[m] < tar) l = m + 1;
            else return true;
        }
        return false;
    }

}
