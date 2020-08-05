package com.java_share.algorithm.leetcode.m08;

/**
 * @author yz
 * @date 2020-08-05 15:35
 * <p>
 *     1534. 统计好三元组
 * </p>
 **/
public class L1534_CountGoodTriplets {

    public static void main(String[] args){
        int[] arr = {5,5,2,6,4};
        System.out.println(new Solution().countGoodTriplets(arr, 5,4,5));
    }

    static class Solution {
        public int countGoodTriplets(int[] arr, int a, int b, int c) {
            int sum = 0, len = arr.length;
            for (int i = 0; i < len - 2; i++){
                for (int j = i + 1; j < len - 1; j++){
                    if (Math.abs(arr[i] - arr[j]) > a) continue;
                    for (int k = j + 1; k < len; k++)
                        if(Math.abs(arr[j]-arr[k]) <= b && Math.abs(arr[i] - arr[k]) <=c) sum++;
                }
            }
            return sum;
        }
    }
}
