package com.java_share.algorithm.jzoffer;

/**
 * @author yz
 * @date 2020-07-18 08:36
 * <p>
 *      剑指 Offer 49. 丑数
 *
 *      我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。
 *      求按从小到大的顺序的第 n 个丑数。
 *
 * </p>
 **/
public class JZ49_NthUglyNumber {

    public static void main(String[] args){
        System.out.println(new Solution().nthUglyNumber(11));
    }

    private static class Solution {

        public int nthUglyNumber(int n) {
            int a = 0, b = 0, c = 0;
            int[] dp = new int[n];
            dp[0] = 1;
            for (int i = 1; i < n; i++) {
                int a0 = dp[a] * 2, b0 = dp[b] * 3, c0 = dp[c] * 5;
                dp[i] = Math.min(Math.min(a0,b0),c0);
                if (dp[i] == a0) a++;
                if (dp[i] == b0) b++;
                if (dp[i] == c0) c++;
            }
            return dp[n-1];
        }
    }

}
