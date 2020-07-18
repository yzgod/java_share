package com.java_share.algorithm.jzoffer;

import java.util.*;

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
            List<String> res = new LinkedList<>();
            dfs(res, s.toCharArray(), 0);
            return res.toArray(new String[res.size()]);
        }
        private void dfs(List<String> res, char[] chars, int k) {
            if (k == chars.length -1) { //终结条件
                res.add(String.valueOf(chars));
                return;
            }
            Set<Character> set = new HashSet<>();// 定义set如果重复continue
            for(int i = k; i < chars.length; i++) {
                if (set.contains(chars[i])) continue;
                set.add(chars[i]);
                swap(chars, i, k);//交换i,k顺序
                dfs(res, chars, k + 1);// 向下递归
                swap(chars, i, k);//还原i,k顺序
            }
        }
        private void swap(char[] chars, int i, int k) {
            char tmp = chars[i];
            chars[i] = chars[k];
            chars[k] = tmp;
        }
    }
}
