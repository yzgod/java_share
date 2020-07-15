package com.java_share.algorithm.jzoffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yz
 * @date 2020-07-14 14:26
 * <br>
 *     剑指 Offer 05. 替换空格
 *     请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 *       示例 1：
 *          输入：s = "We are happy."
 *          输出："We%20are%20happy."
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof
 * </br>
 **/
public class JZ05_ReplaceSpace {

    public static void main(String[] args){
        System.out.println(new Solution().replaceSpace("we are happy"));
    }

    static class Solution {

        public String replaceSpace(String s) {
            if(s == null)
                return null;
            char[] chars = s.toCharArray();
            char[] newChars = new char[chars.length * 3];
            int size = 0;
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if(c == ' '){
                    newChars[size++] = '%';
                    newChars[size++] = '2';
                    newChars[size++] = '0';
                }else {
                    newChars[size++] = c;
                }
            }
            return new String(newChars, 0, size);
        }
    }
}
