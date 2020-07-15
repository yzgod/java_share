package com.java_share.algorithm.jzoffer;

/**
 * @author yz
 * @date 2020-07-15 13:39
 * <br>
 *      剑指 Offer 16. 数值的整数次方
 *      实现函数double Power(double base, int exponent)，
 *      求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。
 *
 *      说明:
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof
 * </br>
 * <br>
 * </br>
 **/
public class JZ16_Pow {

    public static void main(String[] args){
        System.out.println(new Solution().myPow(2, -2147483648));
        System.out.println(new Solution().myPow2(2, -2147483648));
    }

    static class Solution {

        // 利用位移运算,快速幂
        public double myPow(double x, int n) {
            if(x == 0) return 0;
            long ln = n > 0 ? n: -(long)n;
            double res = 1.0;
            while(ln > 0) {
                if((ln & 1) == 1) res *= x;//若有1位,结果*=x底数
                x *= x; //每移动一位x底数翻倍
                ln >>= 1;//右移动
            }
            return n>0 ? res: 1/res;
        }

        // 注意正负数
        public double myPow2(double x, int n) {
            return n>0?pow2(x, n): 1/pow2(x, -n);
        }

        public double pow2(double x, long n) {
            if(n == 0) return 1;
            double res = pow2(x , n/2);
            return n % 2 ==0? res * res : res * res * x;
        }

    }
}
