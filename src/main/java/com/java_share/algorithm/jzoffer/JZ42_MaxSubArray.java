package com.java_share.algorithm.jzoffer;

/**
 * @author yz
 * @date 2020-07-18 08:36
 * <p>
 *      剑指 Offer 42. 连续子数组的最大和
 * </p>
 **/
public class JZ42_MaxSubArray {

    public static void main(String[] args){
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(new Solution().maxSubArray(nums));
    }

    static class Solution {

        public int maxSubArray(int[] dp) {
            int sum = dp[0];// 记录各个阶段的sum
            for (int i = 1; i < dp.length; i++) {
                dp[i] += Math.max(dp[i-1], 0);//dp[i-1]>0 -> dp[i]=dp[i]+dp[i-1]
                sum = Math.max(sum, dp[i]);//选出最大的sum
            }
            return sum;
        }
    }

}
