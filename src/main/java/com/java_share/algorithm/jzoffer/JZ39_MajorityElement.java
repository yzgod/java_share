package com.java_share.algorithm.jzoffer;

import java.util.*;

/**
 * @author yz
 * @date 2020-07-17 09:37
 * <br>
 *     剑指 Offer 39. 数组中出现次数超过一半的数字
 * </br>
 * <p>
 * </p>
 **/
public class JZ39_MajorityElement {

    public static void main(String[] args){
        int[] nums = {8,8,7,7,7};
        int num = new Solution().majorityElement(nums);
        System.out.println(num);
    }

    static class Solution {

        // 摩尔投票法
        public int majorityElement(int[] nums) {
            int k = nums[0], votes = 1;
            for (int i = 1; i < nums.length; i++) {
                if (votes == 0) {// 重置投票
                    k = nums[i];
                    votes = 1;
                    continue;
                }
                if (votes > nums.length - i ) return k; // votes大于剩下长度直接返回
                if (k == nums[i]) votes++;
                else votes--;
            }
            return k;
        }

        public int majorityElement0(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                int count = map.getOrDefault(num, 0);
                map.put(num, ++count);
                if (count >= nums.length/2+1) return num;
            }
            return -1;
        }

    }
}
