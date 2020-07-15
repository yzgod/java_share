package com.java_share.algorithm.jzoffer;

/**
 * @author yz
 * @date 2020-07-14 14:26
 * <br>
 *     剑指 Offer 10- I. 斐波那契数列
 *      写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。
 *      斐波那契数列的定义如下：
 *
 *          F(0) = 0,   F(1) = 1
 *          F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 * 答案需要取模 1e9+7（1000000007），
 * 如计算初始结果为：1000000008，请返回 1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof
 * </br>
 * <br>
 *      这种场景问题,千万不能傻递归
 * </br>
 **/
public class JZ10_1_Fib {

    public static void main(String[] args){
        System.out.println(new Solution().fib(48));
        System.out.println(new Solution().fib2(48));
    }

    static class Solution {

        public int fib(int n) {
            return dfs(n, 0, 1);
        }

        private int dfs(int n, int preResult, int result) {
            if(n == 0)
                return 0;
            if(n == 1)
                return result;
            int sum = (preResult + result) % 1000000007;
            return dfs(--n, result, sum);
        }


        public int fib2(int n) {
            int s0 = 0, s1 = 1, sum = 0;
            for(int i = 1; i < n; i++){
                sum = (s0 + s1) % 1000000007;
                s0 = s1;
                s1 = sum;
            }
            return sum;
        }



    }

}
