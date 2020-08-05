package com.java_share.algorithm.jzoffer;

import java.util.Arrays;

/**
 * @author yz
 * @date 2020-07-20 17:52
 * <p>
 *      剑指 Offer 59 - I. 滑动窗口的最大值
 *
 *      给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 *
 * </p>
 **/
public class JZ59_1_MaxSlidingWindow {

    public static void main(String[] args){
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int[] window = new Solution().maxSlidingWindow(nums, 3);
        System.out.println(Arrays.toString(window));
    }

    static class Solution{

        public int[] maxSlidingWindow(int[] nums, int k) {
            if (k == 0 || nums.length == 0) return new int[0];
            int[] res = new int[nums.length - k + 1];
            for (int l = 0; l <= nums.length - k; l++) {
                int r = l + k - 1;
                if (l > 0 && nums[l-1] < res[l-1]) {// 优化算法,去掉一个小的数,不影响,不需要重新走滑动的循环判断逻辑
                    res[l] = Math.max(res[l-1], nums[r]);
                    continue;
                }
                // 以下为滑动逻辑
                int max = nums[l];
                for (int j = l + 1; j <= r; j++) max = Math.max(max, nums[j]);
                res[l] = max;
            }
            return res;
        }

    }



}
