package com.java_share.algorithm.jzoffer;

/**
 * @author yz
 * @date 2020-07-20 17:52
 * <p>
 *      剑指 Offer 58 - I. 翻转单词顺序
 *      输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
 *      为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，
 *      则输出"student. a am I"。
 *
 * </p>
 **/
public class JZ58_1_ReverseWords {

    public static void main(String[] args){
        System.out.println(new Solution().reverseWords("  hello world     55  "));
    }

    static class Solution{
        public String reverseWords(String s) {
            if (s == null) return null;
            String[] arr = s.trim().split(" ");
            StringBuilder sb = new StringBuilder();
            for (int i = arr.length -1; i >= 0; i--)
                if (!arr[i].equals("")) sb.append(arr[i]).append(" ");
            return sb.toString().trim();
        }
    }



}
