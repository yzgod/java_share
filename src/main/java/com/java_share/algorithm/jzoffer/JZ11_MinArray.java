package com.java_share.algorithm.jzoffer;

/**
 * @author yz
 * @date 2020-07-14 14:26
 * <br>
 *      剑指 Offer 11. 旋转数组的最小数字
 *      把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 *      输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 *      例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 * </br>
 * <br>
 *      本来数组是递增的,那么这种情况可以考虑二分查找
 * </br>
 **/
public class JZ11_MinArray {

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,7,7,1,2,3};
        System.out.println(new Solution().minArray(nums));
    }

    static class Solution {
        //二分查找
        public int minArray(int[] nums) {
            int left = 0, right = nums.length - 1;
            while (left != right){
                int mid = (left + right)/2;
                if (nums[mid] > nums[right]) left ++;
                if (nums[mid] <= nums[right]) right --;
            }
            return nums[left];
        }
    }

}
