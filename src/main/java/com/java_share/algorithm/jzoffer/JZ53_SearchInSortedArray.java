package com.java_share.algorithm.jzoffer;

import sun.java2d.xr.XRMaskImage;

/**
 * @author yz
 * @date 2020-07-20 17:52
 * <p>
 *      剑指 Offer 53 - I. 在排序数组中查找数字 I
 *      统计一个数字在排序数组中出现的次数。
 * </p>
 **/
public class JZ53_SearchInSortedArray {

    public static void main(String[] args){
        int[] arr = new int[]{5,6,6,8,8,10};
        int target = 8;
        int times = new Solution().search(arr, target);
        System.out.println(times);
    }

    static class Solution{

        // 两轮二分, 两个index相减. 这种方式太难想到了...
        public int search(int[] nums, int target) {
            return binSearch(nums, target) - binSearch(nums, target - 1);
        }

        //返回的是target的最大index, 若无target就是他应该在的位置index
        int binSearch(int[] nums, int target){
            int max = nums.length - 1, min = 0;
            while (max >= min) {
                int mid = (max + min) / 2;
                if (nums[mid] > target) max = mid - 1;
                else min = mid + 1;// 找最大的index
            }
            return min;
        }


        // 一次二分后循环
        public int search1(int[] nums, int target) {
            int max = nums.length - 1, min = 0, res = 0;// res为次数
            while (max >= min) {
                int mid = (max + min) / 2;
                if (nums[mid] > target) max --;
                else if (nums[mid] < target) min ++;
                else break;
            }
            // 若min<=max说明是break跳出的,计算res
            for (int i = min; i <= max; i++) if (nums[i]==target) res++;
            return res;
        }

    }



}
