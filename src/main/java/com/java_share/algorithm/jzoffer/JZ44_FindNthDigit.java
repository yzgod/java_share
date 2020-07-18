package com.java_share.algorithm.jzoffer;

/**
 * @author yz
 * @date 2020-07-18 08:36
 * <p>
 *      剑指 Offer 44.数字序列中的某一位数字
 * </p>
 **/
public class JZ44_FindNthDigit {

    public static void main(String[] args){
        System.out.println(new Solution().findNthDigit(10));
    }

    static class Solution {

        public int findNthDigit(int n) {
            int digit = 1;
            long start = 1, count = 9;
            while (n > count) {
                n -= count;
                start *= 10;
                count = (++digit) * start * 9;
            }
            long num = start + (n - 1) / digit;
            char c = Long.toString(num).charAt((n - 1) % digit);
            return c - '0';// '0'=48
        }

    }

}
