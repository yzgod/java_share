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
            long b = n;
            double res = 1.0;
            if(b < 0) {
                x = 1 / x;
                b = -b;
            }
            while(b > 0) {
                if((b & 1) == 1) res *= x;
                x *= x;
                b >>= 1;
            }
            return res;
        }

        public double myPow2(double x, int n) {
            return pow2(x, n);
        }

        public double pow2(double x, long n) {
            if(n == 0) return 1;
            boolean neg = false;
            if(n <  0) {
                n = -n;
                neg = true;
            }
            double res = pow2(x , n/2);
            res = n % 2 ==0? res * res : res * res * x;
            return neg? 1/res : res;
        }
    }
}
