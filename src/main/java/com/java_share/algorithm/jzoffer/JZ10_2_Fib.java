package com.java_share.algorithm.jzoffer;

/**
 * @author yz
 * @date 2020-07-14 14:26
 * <br>
 *     剑指 Offer 10- II. 青蛙跳台阶问题
 *      一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。
 *      求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *
 *      答案需要取模 1e9+7（1000000007），
 *      如计算初始结果为：1000000008，请返回 1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof
 * </br>
 * <br>
 *      这种场景问题,千万不能傻递归
 * </br>
 **/
public class JZ10_2_Fib {

    public static void main(String[] args){
        System.out.println(new Solution().numWays(45));
        System.out.println(new Solution().numWays2(45));
    }

    static class Solution {

        public int numWays(int n) {
            return dfs(n, 1, 2);
        }

        // 尾递归
        private int dfs(int n, int preResult, int result) {
            if(n < 2)
                return 1;
            if(n == 2)
                return result;
            int sum = (preResult + result) % 1000000007;
            return dfs(--n, result, sum);
        }

        // 循环
        public int numWays2(int n) {
            if(n < 2)
                return 1;
            if(n == 2)
                return 2;
            int s0 = 1, s1 = 2, sum = 0;
            for(int i = 2; i < n; i++){
                sum = (s0 + s1) % 1000000007;
                s0 = s1;
                s1 = sum;
            }
            return sum;
        }



    }

}
