package com.java_share.algorithm.jzoffer;

import java.util.Arrays;

/**
 * @author yz
 * @date 2020-07-20 17:52
 * <p>
 *      剑指 Offer 60. n个骰子的点数
 *      把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 *      你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof
 * </p>
 **/
public class JZ60_NShaizi {

    public static void main(String[] args){
        double[] doubles = new Solution().twoSum(2);
        System.out.println(Arrays.toString(doubles));
    }

    static class Solution {

        // 动态规划
        public double[] twoSum(int n) {
            double[] dp = {1/6d, 1/6d, 1/6d ,1/6d, 1/6d, 1/6d};
            for (int i = 2; i <= n; i++) {
                double[] tmp = new double[5 * i +1];
                for (int j = 0; j < dp.length; j++) {
                    for (int m = 0; m < 6; m++) {
                        tmp[j+m] += dp[j]/6;
                    }
                }
                dp = tmp;
            }
            return dp;
        }

    }


}
