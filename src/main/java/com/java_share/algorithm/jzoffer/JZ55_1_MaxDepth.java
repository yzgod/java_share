package com.java_share.algorithm.jzoffer;

import java.util.LinkedList;

/**
 * @author yz
 * @date 2020-07-20 17:52
 * <p>
 *      剑指 Offer 55 - I. 二叉树的深度
 *  输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过
 *  的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
 * </p>
 **/
public class JZ55_1_MaxDepth {

    public static void main(String[] args){
        TreeNode node = TreeNode.deserialize("[3,9,20,null,null,15,7,null,null,null,null]");
        System.out.println(new Solution().maxDepth(node));
        System.out.println(new Solution().maxDepth2(node));
    }

    static class Solution{

        int max = 0;//定义最大层级

        public int maxDepth(TreeNode node) {
            dfs(node, 0);
            return max;
        }

        // 深度优先
        private void dfs(TreeNode node, int cur) {
            if (node == null) return;
            max = Math.max(max, ++cur);// 前序遍历,max赋值
            dfs(node.left, cur);
            dfs(node.right, cur);
        }

        // 广度优先层序遍历
        public int maxDepth2(TreeNode node) {
            int cur = 0;
            LinkedList<TreeNode> list = new LinkedList<>(), tmp;
            list.addFirst(node);
            while (!list.isEmpty()){
                tmp = new LinkedList<>();
                for (TreeNode tn : list) {
                    if (tn.left !=null) tmp.add(tn.left);
                    if (tn.right!=null) tmp.add(tn.right);
                }
                list = tmp;
                cur++;
            }
            return cur;
        }

    }



}
