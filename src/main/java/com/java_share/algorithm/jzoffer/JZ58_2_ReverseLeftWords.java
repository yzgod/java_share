package com.java_share.algorithm.jzoffer;

import java.util.Arrays;

/**
 * @author yz
 * @date 2020-07-20 17:52
 * <p>
 *      剑指 Offer 58 - II. 左旋转字符串
 *      字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
 *      请定义一个函数实现字符串左旋转操作的功能。
 *      比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof
 * </p>
 **/
public class JZ58_2_ReverseLeftWords {

    public static void main(String[] args){
        System.out.println(new Solution().reverseLeftWords("abcdefgh", 3));
    }

    static class Solution{

        public String reverseLeftWords(String s, int n) {
            return s.substring(n) + s.substring(0, n);
        }

    }



}
