package com.java_share.algorithm.msb.base.class01;

/**
 * @author yz
 * @date 2020-08-10 23:04
 * <p>
 *     2. 在有序数组中,查找>=某个数最左侧的位置.
 * </p>
 **/
public class C05_BsNearLeft {

    public static void main(String[] args){
        int[] nums = {1, 2, 4, 5,5,5,5,5,5, 8, 9, 11, 18};
        System.out.println(leftNearestIndex(nums, 5));
        System.out.println(leftNearestIndex(nums, -5));
        System.out.println(leftNearestIndex(nums, 100));
    }


    public static int leftNearestIndex(int[] nums, int tar) {
        if (nums == null || nums.length == 0) return -1;

        int l = 0, r = nums.length - 1, index = -1;
        while (l <= r) {
            int m = (l+r)/2;
            if (nums[m] >= tar) {
                index = m;
                r = m - 1;
            }else {
                l = m + 1;
            }
        }
        return index;
    }

}
