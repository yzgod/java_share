package com.java_share.algorithm.jzoffer;

import java.util.*;

/**
 * @author yz
 * @date 2020-07-16 14:20
 * <br>
 *     剑指 Offer 33. 二叉搜索树的后序遍历序列
 *
 *     输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。
 *     如果是则返回 true，否则返回 false。假设输入的数组的任意两个
 *     数字都互不相同。
 *
 * </br>
 * <p>
 *     二叉搜索树的的特性: 左子节点<中间节点<右子节点
 *     后续遍历,左->右->中;
 *     则最后一位为中间root节点
 * </p>
 **/
public class JZ33_VerifyPostorder {

    public static void main(String[] args){
        int[] nums  = {1,6,3,2,5};
        System.out.println(new Solution().verifyPostorder(nums));

        int[] nums1 = {1,3,2,6,5};
        System.out.println(new Solution().verifyPostorder(nums1));
    }

    static class Solution {
        public boolean verifyPostorder(int[] post) {
            Stack<Integer> stack = new Stack<>();
            int root = Integer.MAX_VALUE;
            for (int i = post.length - 1; i >= 0; i--) {
                if (post[i] > root) return false;
                while (!stack.isEmpty() && stack.peek() > post[i]) {
                    root = stack.pop();
                }
                stack.push(post[i]);
            }
            return true;
        }
    }
}
