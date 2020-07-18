package com.java_share.algorithm.jzoffer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yz
 * @date 2020-07-18 08:36
 * <p>
 *      剑指 Offer 48. 最长不含重复字符的子字符串
 *
 *      请从字符串中找出一个最长的不包含重复字符的子字符串，
 *      计算该最长子字符串的长度。
 *
 * </p>
 **/
public class JZ48_LengthOfLongestSubstring {

    public static void main(String[] args){
        System.out.println(new Solution().lengthOfLongestSubstring("abcabcbb"));
    }

    private static class Solution {


        public int lengthOfLongestSubstring(String s) {
            Map<Character, Integer> map = new HashMap<>();
            int res = 0, tmp = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int index = map.getOrDefault(c, -1);
                map.put(c, i);
                tmp = tmp < i-index ? tmp+1:i-index;
                res = Math.max(res, tmp);
            }
            return res;
        }

    }

}
