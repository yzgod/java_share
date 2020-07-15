package com.java_share.algorithm.jzoffer;

/**
 * @author yz
 * @date 2020-07-15 13:39
 * <br>
 *      剑指 Offer 15. 二进制中1的个数
 * </br>
 * <br>
 * </br>
 **/
public class JZ15_HammingWeight {

    public static void main(String[] args){
        System.out.println(new Solution().hammingWeight(-127));
    }

    static class Solution {

        public int hammingWeight(int n) {
            int count = 0;
            for (int i = 0; i < 32; i++) {
                if((n & 1) == 1) count ++;
                n >>>= 1;
            }
            return count;
        }
    }
}
