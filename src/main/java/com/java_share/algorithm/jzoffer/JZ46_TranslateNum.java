package com.java_share.algorithm.jzoffer;

import java.util.PriorityQueue;

/**
 * @author yz
 * @date 2020-07-18 08:36
 * <p>
 *      剑指 Offer 46. 把数字翻译成字符串
 *
 *      给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，
 *      1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 *      一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
 * </p>
 **/
public class JZ46_TranslateNum {

    public static void main(String[] args){
        System.out.println(new Solution().translateNum(12258));
    }

    static class Solution {


        public int translateNum(int num) {
            String s = String.valueOf(num);
            // dp[0]=1,dp[1]=1
            int dp_i = 1, dp_i1 = 1, dp_i2 = 1;
            for(int i = 2; i <= s.length(); i++) {
                String sub = s.substring(i - 2, i);
                // 可翻译: dp[i]=dp[i-1]+dp[i-2] 不可翻译: dp[i]=dp[i-1]
                dp_i = sub.compareTo("10") >= 0 && sub.compareTo("25") <= 0
                        ? dp_i1 + dp_i2 : dp_i1;
                dp_i2 = dp_i1;// 移动
                dp_i1 = dp_i;// 移动
            }
            return dp_i;
        }

    }

}
