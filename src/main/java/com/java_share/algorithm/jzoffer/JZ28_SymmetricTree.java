package com.java_share.algorithm.jzoffer;

/**
 * @author yz
 * @date 2020-07-15 13:39
 * <br>
 *      剑指 Offer 28. 对称的二叉树
 * </br>
 * <br>
 * </br>
 **/
public class JZ28_SymmetricTree {


    static class Solution {

        public boolean isSymmetric(TreeNode root) {
            return root==null? true: bfs(root.left, root.right);
        }

        private boolean bfs(TreeNode left, TreeNode right) {
            if(left == null && right == null) return true;
            if(left == null || right == null || left.val != right.val) return false;
            return bfs(left.left, right.right) && bfs(left.right, right.left);
        }

    }
}
