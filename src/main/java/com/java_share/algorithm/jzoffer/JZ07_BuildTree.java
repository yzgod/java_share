package com.java_share.algorithm.jzoffer;

import java.util.Arrays;
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

    }

    static class Solution {

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            return null;
        }
    }
}
