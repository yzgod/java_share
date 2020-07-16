package com.java_share.algorithm.jzoffer;

import java.util.Stack;

/**
 * @author yz
 * @date 2020-07-16 14:20
 * <br>
 *     剑指 Offer 31. 栈的压入、弹出序列
 *     输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
 *     假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1}
 *     是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof
 * </br>
 **/
public class JZ31_ValidStackSequeuece {

    public static void main(String[] args){
        int[] pushed = {1,2,3,4,5}, popped ={4,5,2,3,1};
        System.out.println(new Solution().validateStackSequences(pushed, popped));
    }

    static class Solution {
        public boolean validateStackSequences(int[] pushed, int[] popped) {
            Stack<Integer> stack = new Stack<>();
            int k = 0;
            for (int i = 0; i < pushed.length; i++) {
                stack.push(pushed[i]);
                while (!stack.isEmpty() && stack.peek() == popped[k]){
                    k++;
                    stack.pop();
                }
            }
            return stack.isEmpty();
        }
    }
}
