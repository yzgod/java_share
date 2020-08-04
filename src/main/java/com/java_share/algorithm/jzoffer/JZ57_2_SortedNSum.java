package com.java_share.algorithm.jzoffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yz
 * @date 2020-07-20 17:52
 * <p>
 *      剑指 Offer 57 - II. 和为s的连续正数序列
 *
 *      输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 *      序列内的数字由小到大排列，不同序列按照首个数字从小到大排列
 *
 * </p>
 **/
public class JZ57_2_SortedNSum {

    public static void main(String[] args){
        long start = System.currentTimeMillis();
        int[][] sequenceIndex = new Solution().findContinuousSequenceIndex(10_0000_0000);
        long end = System.currentTimeMillis();
        System.out.println((end-start));
        for (int[] ints : sequenceIndex) {
            System.out.println(Arrays.toString(ints));
        }
//        int[][] numsArr = new Solution().findContinuousSequence(10_0000);
//        for (int[] ints : numsArr) {
//            System.out.println(Arrays.toString(ints));
//        }
    }

    static class Solution{

        public int[][] findContinuousSequence(int target) {
            List<int[]> list = new LinkedList<>();
            int l = 1, r = 2;
            while (l < r){
                int sum = (l + r) * (r - l + 1)/2;
                if (sum == target) {
                    int[] nums = new int[r - l + 1];
                    for (int i = 0; i < r - l + 1; i++) nums[i] = l + i;
                    list.add(nums);
                    l += 2;
                    r ++;
                }
                else if (sum < target) r ++;
                else l ++;
            }
            return list.toArray(new int[list.size()][]);
        }

        // 输出开始结尾的数
        public int[][] findContinuousSequenceIndex(int target) {
            List<int[]> list = new LinkedList<>();
            int l = 1, r = 2;
            while (l < r){
                long sum = ((long) (l + r)) * (r - l + 1)/2;
                if (sum == target) {
                    list.add(new int[]{l, r});
                    l += 2;
                    r ++;
                }
                else if (sum < target) r ++;
                else l ++;
            }
            return list.toArray(new int[list.size()][]);
        }

    }



}
