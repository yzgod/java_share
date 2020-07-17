package com.java_share.algorithm.jzoffer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author yz
 * @date 2020-07-17 09:37
 * <br>
 *     剑指 Offer 38. 字符串的排列
 * </br>
 * <p>
 * </p>
 **/
public class JZ38_String_Permutation {

    public static void main(String[] args){
        String[] strings = new Solution().permutation("abc");
        System.out.println(Arrays.asList(strings));
    }

    static class Solution {

        public String[] permutation(String s) {
            if (s == null) return new String[0];
            LinkedList<String> res = new LinkedList<>();
            char[] chars = s.toCharArray();
            dfs(res, chars, 0);
            return res.toArray(new String[res.size()]);
        }

        private void dfs(LinkedList<String> res, char[] chars, int k) {
            if (k == chars.length -1) {
                res.add(String.valueOf(chars));
                return;
            }
            Set<Character> hash = new HashSet<>();
            for(int i = k; i < chars.length; i++) {
                if (hash.contains(chars[i])) continue;
                hash.add(chars[i]);
                swap(chars, i, k);
                dfs(res, chars, k + 1);
                swap(chars, i, k);
            }
        }

        private void swap(char[] chars, int i, int k) {
            if (i == k) return;
            char tmp = chars[i];
            chars[i] = chars[k];
            chars[k] = tmp;
        }
    }
}
