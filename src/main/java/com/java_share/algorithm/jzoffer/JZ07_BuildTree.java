package com.java_share.algorithm.jzoffer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author yz
 * @date 2020-07-14 14:26
 * <br>
 *     剑指 Offer 07. 重建二叉树
 *     输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。
 *     假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * </br>
 * <br>
 *
 * </br>
 **/
public class JZ07_BuildTree {

    public static void main(String[] args){
        int[] preorder = {3,9,8,10,20,15,7};
        int[] inorder = {8,9,10,3,15,20,7};
        TreeNode tree = new Solution().buildTree(preorder, inorder);
        System.out.println(tree);
    }

    static class Solution {

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder == null || preorder.length == 0) {
                return null;
            }
            int length = preorder.length;
            Map<Integer, Integer> inMap = new HashMap<>(length);
            for (int i = 0; i < length; i++) {
                inMap.put(inorder[i], i);
            }
            TreeNode root = buildTree(preorder, 0, length - 1, 0, length - 1, inMap);
            return root;
        }

        public TreeNode buildTree(int[] pre, 
                                  int ps, int pe, 
                                  int is, int ie, 
                                  Map<Integer, Integer> inMap) {
            if (ps > pe) {
                return null;
            }
            int val = pre[ps];
            TreeNode head = new TreeNode(val);
            if (ps != pe) {
                int in = inMap.get(val);
                int l = in - is, r = ie - in;
                TreeNode left = buildTree(pre, ps + 1, ps + l, is, in - 1, inMap);
                TreeNode right = buildTree(pre, pe - r + 1, pe, in + 1, ie, inMap);
                head.left = left;
                head.right = right;
            }
            return head;
        }

    }
}
