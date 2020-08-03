package com.java_share.algorithm.jzoffer;

/**
 * @author yz
 * @date 2020-07-20 17:52
 * <p>
 *      剑指 Offer 55 - II. 平衡二叉树
 *     输入一棵二叉树的根节点，判断该树是不是平衡二叉树。
 *     如果某二叉树中任意节点的左右子树的深度相差不超过1，
 *     那么它就是一棵平衡二叉树。
 * </p>
 **/
public class JZ55_2_IsBalanced {

    public static void main(String[] args){
        TreeNode node = TreeNode.deserialize("[3,9,20,null,null,15,7,null,null,null,null]");
        System.out.println(new Solution().isBalanced(node));
    }

    static class Solution{
        public boolean isBalanced(TreeNode node) {
            return dfs(node) >= 0;
        }

        // 后序遍历
        private int dfs(TreeNode node) {
            if (node == null) return 0;
            int left = dfs(node.left);
            if (left  == -1) return -1;
            int right = dfs(node.right);
            if (right == -1) return -1;
            return Math.abs(left-right) <=1 ? Math.max(left,right) + 1 : -1;
        }

    }



}
