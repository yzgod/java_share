package com.java_share.algorithm.jzoffer;

import java.util.*;

/**
 * @author yz
 * @date 2020-07-18 08:36
 * <p>
 *      剑指 Offer 50. 第一个只出现一次的字符
 *
 *      在字符串 s 中找出第一个只出现一次的字符。
 *      如果没有，返回一个单空格。 s 只包含小写字母。
 *
 * </p>
 **/
public class JZ50_FirstUniqChar {

    public static void main(String[] args){
//        System.out.println(new Solution().firstUniqChar("abaccdeff"));
        System.out.println(new Solution().firstUniqChar2("loveleetcode"));
    }

    private static class Solution {


        public char firstUniqChar2(String str) {
            int[] nums = new int[26];// 0-25 a-z
            char[] orders = new char[26];
            char[] chars = str.toCharArray();
            int k = 0;
            for (char c : chars) {
                int i = c - 'a';
                if (nums[i] >= 1) nums[i]++;
                else {
                    nums[i] = 1;
                    orders[k++] = c;
                }
            }
            // notRepeat
            List<Character> notRepeats = new ArrayList<>(26);
            for (int i = 0; i < 26; i++)
                if(nums[i] == 1) notRepeats.add((char) (i + 'a'));
            for (char c : orders)
                if (notRepeats.contains(c)) return c;
            return ' ';
        }

        public char firstUniqChar(String str) {
            char[] chars = str.toCharArray();
            LinkedHashMap<Character, Boolean> map = new LinkedHashMap<>();
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                Boolean b = map.get(c);
                if (b == null) map.put(c, false);
                else map.put(c, true);
            }
            for (Character key : map.keySet())
                if(!map.get(key)) return key;
            return ' ';
        }

    }

}
