package com.java_share.algorithm.jzoffer;

/**
 * @author yz
 * @date 2020-07-15 13:39
 * <br>
 *      剑指 Offer 14- I. 剪绳子
 *
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
 * 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 2 <= n <= 58
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
 *      推理:切成3的倍数x, 3的x次方最大,根据余数不同有三种情况
 *
 * </br>
 **/
public class JZ14_1_CutRop {

    public static void main(String[] args){
        System.out.println(new Solution().cuttingRope(10));
        System.out.println(new Solution().cuttingRope(50));
    }

    static class Solution {
        public int cuttingRope(int n) {
            if(n <= 3) return n -1;
            int pow = n / 3, rem = n % 3;
            switch (rem){
                case 1:
                    return (int) Math.pow(3, pow-1) * 4;
                case 2:
                    return (int) Math.pow(3, pow) * 2;
                default:
                    return (int) Math.pow(3, pow);
            }
        }
    }
}
