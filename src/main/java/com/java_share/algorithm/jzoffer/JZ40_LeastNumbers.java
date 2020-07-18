package com.java_share.algorithm.jzoffer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yz
 * @date 2020-07-17 09:37
 * <br>
 *     剑指 Offer 40. 最小的k个数
 * </br>
 * <p>
 * </p>
 **/
public class JZ40_LeastNumbers {

    public static void main(String[] args){
        int[] nums = {3,2,1};
//        int[] nums = {1,2,3,4,5};
//        int[] nums = {0,0,1,2,4,2,2,3,1,4};
        int[] res = new Solution().getLeastNumbers(nums, 2);
        System.out.println(Arrays.toString(res));
    }

    static class Solution {

        // 利用快排思想
        public int[] getLeastNumbers(int[] arr, int k) {
            if (k == 0 || arr.length == 0) return new int[0];
            int l = 0, r = arr.length -1;
            while (true){
                int mid = dfs(arr, l, r);//中间index
                if (mid == k - 1) return Arrays.copyOfRange(arr, 0, k);//刚好相等,终结返回
                else if (mid > k - 1) r = mid - 1;//index在k右侧,那么需要对[0,mid-1]快排
                else l = mid + 1;//index在左侧,对[mid+1,r]快排
            }
        }
        // 快排返回中间index
        private int dfs(int[] arr, int l, int r) {
            int lt = l, tmp = arr[lt];
            while (l < r){
                while (arr[r] >= tmp && l < r) r--;
                while (arr[l] <= tmp && l < r) l++;
                if (l < r) swap(arr, l, r);
            }
            swap(arr, l, lt);
            return l;
        }
        // 交换
        private void swap(int[] arr, int l, int r) {
            int t = arr[r];
            arr[r] = arr[l];
            arr[l] = t;
        }


    }
}
