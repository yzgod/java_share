package com.java_share.algorithm.jzoffer;

/**
 * @author yz
 * @date 2020-07-20 17:52
 * <p>
 *      剑指 Offer 54. 二叉搜索树的第k大节点
 *  给定一棵二叉搜索树，请找出其中第k大的节点。
 * </p>
 **/
public class JZ54_KthLargestNode {

    public static void main(String[] args){
        TreeNode node = TreeNode.deserialize("[3,1,4,null,2,null,null,null,null]");
        System.out.println(new Solution().kthLargest(node, 2));
    }

    static class Solution{

        int res = 0, cur = 0;// 定义局部变量

        // 中序遍历自增，中序倒序自减
        // 中序遍历,右->中->左 倒序的方式最快
        public int kthLargest(TreeNode node, int k) {
            cur = k;
            dfs(node);
            return res;
        }

        private void dfs(TreeNode node) {
            if (node == null || cur == 0) return;
            dfs(node.right);
            if (--cur == 0) res = node.val;
            dfs(node.left);
        }

        // 时间复杂度O(n) 空间复杂度O(k,n))
        public int kthLargest0(TreeNode node, int k) {
            int[] nums = new int[k];
            int i = dfs0(node, nums, 0, k);
            return nums[i];
        }

        private int dfs0(TreeNode node, int[] nums, int i, int k) {
            if (node == null) return i;
            i = dfs0(node.left, nums, i, k);
            nums[i % k] = node.val;
            if (++i == k) i = 0;
            return dfs0(node.right, nums, i, k);
        }

    }



}
