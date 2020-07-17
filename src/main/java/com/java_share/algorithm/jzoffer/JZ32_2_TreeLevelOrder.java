package com.java_share.algorithm.jzoffer;

import java.util.*;

/**
 * @author yz
 * @date 2020-07-16 14:20
 * <br>
 *     剑指 Offer 32 - II. 从上到下打印二叉树 II
 *
 *     从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，
 *     每一层打印到一行。
 * </br>
 **/
public class JZ32_2_TreeLevelOrder {

    public static void main(String[] args){
        TreeNode node = TreeNode.deserialize("[3, 9, 20, null, null, 15, 7,null,null,null,null]");
        List<List<Integer>> list = new Solution().levelOrder(node);
        System.out.println(list);
    }

    static class Solution {

        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) return Collections.emptyList();
            List<List<Integer>> res = new ArrayList<>();
            // 定义cur/next 集合
            LinkedList<TreeNode> cur = new LinkedList<>(), next;
            cur.add(root);
            while (!cur.isEmpty()){
                List<Integer> data = new ArrayList<>(cur.size());
                next = new LinkedList<>();
                TreeNode node;
                while ((node = cur.pollFirst())!=null){
                    data.add(node.val);
                    if (node.left  != null) next.add(node.left);
                    if (node.right != null) next.add(node.right);
                }
                res.add(data);
                cur = next;
            }
            return res;
        }

    }
}
