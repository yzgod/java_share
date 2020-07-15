package com.java_share.algorithm.jzoffer;

import java.util.Arrays;

/**
 * @author yz
 * @date 2020-07-15 13:39
 * <br>
 *      剑指 Offer 17. 打印从1到最大的n位数
 *      输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。
 *      比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 * </br>
 * <br>
 * </br>
 **/
public class JZ17_PrintNumbers {

    public static void main(String[] args){
        int[] arr = new Solution().printNumbers(2);
        System.out.println(Arrays.toString(arr));
    }

    static class Solution {
        public int[] printNumbers(int n) {
            int min_n_plus1 = 1;// n+1位的最小数
            for (int i = 0; i < n; i++) {
                min_n_plus1 *= 10;
            }
            int max = min_n_plus1 -1; //n位的最大数
            int[] arr = new int[max];
            for (int i = 0; i < max - 1; i++) {
                arr[i] = i + 1;//填充1~max
            }
            return arr;
        }
    }
}
