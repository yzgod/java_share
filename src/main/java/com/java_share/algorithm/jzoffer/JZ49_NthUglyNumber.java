package com.java_share.algorithm.jzoffer;

import java.util.PriorityQueue;
import java.util.Queue;

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
        System.out.println(new Solution().nthUglyNumber(1690));
        System.out.println(new Solution().nthUglyNumber1(1690));
    }

    private static class Solution {

        public int nthUglyNumber(int n) {
            int[] uglyNums = {2,3,5};
            //小顶堆
            Queue<Long> heap = new PriorityQueue<>();
            heap.add(1L);
            int count = 0;
            while (true){
                long cut = heap.poll();
                //如果出堆的个数>=n,当前cut就是第n个丑数
                if (++count >= n) return (int) cut;
                for(int num : uglyNums){
                    if(!heap.contains(num * cut)) heap.add(num * cut);
                }
            }
        }

        public int nthUglyNumber1(int n) {
            int n2 = 0, n3 = 0, n5 = 0;
            int[] dp = new int[n];
            dp[0] = 1;
            for (int i = 1; i < n; i++) {
                int s2 = dp[n2] * 2, s3 = dp[n3] * 3, s5 = dp[n5] * 5;
                dp[i] = Math.min(Math.min(s2,s3),s5);
                if (dp[i] == s2) n2++;
                if (dp[i] == s3) n3++;
                if (dp[i] == s5) n5++;
            }
            return dp[n-1];
        }
    }

}
