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
            // 宽度优先
            return root==null?true:bfs(root.left, root.right);
        }

        private boolean bfs(TreeNode left, TreeNode right) {
            // true 的终结条件:左右都为空
            if(left == null && right == null) return true;
            // false的终结条件:左右有一个为空(另一个就不为空了),或者 两个都不为空,但值不同
            if(left == null || right == null || left.val != right.val) return false;
            // left.left与right.right 和 left.right与right.left
            return bfs(left.left, right.right) && bfs(left.right, right.left);
        }

    }
}
