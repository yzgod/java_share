package com.java_share.algorithm.jzoffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yz
 * @date 2020-07-16 14:20
 * <br>
 *     剑指 Offer 34. 二叉树中和为某一值的路径
 *
 *      输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
 *      从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 * </br>
 * <p>
 * </p>
 **/
public class JZ34_Tree_PathSum {

    public static void main(String[] args){
        TreeNode node = TreeNode.deserialize("[5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1," +
                " null,null,null,null,null,null,null,null]");
        List<List<Integer>> lists = new Solution().pathSum(node, 22);
        System.out.println(lists);
    }

    static class Solution {

        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            List<List<Integer>> res = new ArrayList<>();
            LinkedList<Integer> data = new LinkedList<>();
            dfs(res, data ,root, sum);
            return res;
        }

        private void dfs(List<List<Integer>> res,LinkedList<Integer> data, TreeNode root, int sum) {
            if(root == null) return; // 终结条件
            data.add(root.val);// 前序遍历
            sum -= root.val;
            if(sum == 0 && root.left == null && root.right == null) // sum=0 必须为叶子节点
                res.add(new ArrayList<>(data));
            dfs(res, data, root.left, sum); //左子树
            dfs(res, data, root.right, sum); //右子树
            data.removeLast(); // 清理回溯状态
        }

    }
}
