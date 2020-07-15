package com.java_share.algorithm.jzoffer;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author yz
 * @date 2020-07-15 13:39
 * <br>
 *      剑指 Offer 14- II. 剪绳子 II
 *
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
 * 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 2 <= n <= 1000
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jian-sheng-zi-lcof
 * </br>
 * <br>
 *     贪心算法:
 *      n=2   2 最好不切分
 *      n=3   3 最好不切分
 *      n=4  切成 2 + 2
 *      n =5 切成 2 + 3
 *      n =6 切成 3 + 3
 *      n =7 切成2 + (2 + 3)
 *      n =8 切成2 + (3 + 3)
 *      n =9 切成3 + (3 + 3)
 *      n =10 切成(2 + 2) + (3 + 3)
 *      n =11 切成(2 + 3) + (3 + 3)
 *
 *      推理:切成3的倍数x, 3的x次方
 * </br>
 **/
public class JZ14_2_CutRop {

    public static void main(String[] args){
        System.out.println(new Solution().cuttingRope(120));
    }

    static class Solution {

        public int cuttingRope(int n) {
            if(n <= 3) return n - 1;
            int rem = n % 3;
            long res = 1L;
            for(int i = 0; i < n / 3 - 1; i++) {
                res = (res * 3) % 1000000007;
            }
            // 少了一次幂 i < n/3-1
            if(rem == 0) {
                return (int) ((res * 3) % 1000000007);
            } else if(rem == 1) {
                return (int) ((res * 4) % 1000000007);
            } else{
                return (int) ((res * 6) % 1000000007);
            }
        }

        public int cuttingRope0(int n) {
            if(n <= 3) return n -1;
            int pow = n / 3, remainder = n % 3;
            BigInteger res;
            BigInteger num3 = new BigInteger("3");
            switch (remainder){
                case 1:
                    res = num3.pow(pow-1).multiply(new BigInteger("4"));
                    break;
                case 2:
                    res = num3.pow(pow).multiply(new BigInteger("2"));
                    break;
                default:
                    res = num3.pow(pow);
                    break;
            }
            return res.divideAndRemainder(new BigInteger("1000000007"))[1].intValue();
        }

    }
}
