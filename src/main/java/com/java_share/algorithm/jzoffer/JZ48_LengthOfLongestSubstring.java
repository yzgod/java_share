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
            int max = 0, curLen = 0;//max记录不间断的最大长度,curLen记录当前不重复的长度
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int preIndex = map.getOrDefault(c, -1);//上次出现索引,不存在为-1
                map.put(c, i);//更新至当前索引
                int cLen = i - preIndex;//这个元素的间隔
                if (curLen < cLen) curLen++;//curLen++
                else curLen = cLen;//curLen重置
                max = Math.max(max, curLen);
            }
            return max;
        }

    }

}
