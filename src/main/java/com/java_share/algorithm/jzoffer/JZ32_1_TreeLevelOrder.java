package com.java_share.algorithm.jzoffer;

import java.util.*;

/**
 * @author yz
 * @date 2020-07-16 14:20
 * <br>
 *     剑指 Offer 32 - I. 从上到下打印二叉树
 *
 * </br>
 **/
public class JZ32_1_TreeLevelOrder {

    public static void main(String[] args){
        TreeNode node = TreeNode.levelDeserialize(3, 9, 20, null, null, 15, 7);
        int[] arr = new Solution().levelOrder(node);
        System.out.println(Arrays.toString(arr));
    }

    static class Solution {

        public int[] levelOrder(TreeNode root) {
            if(root == null) return new int[0];
            LinkedList<TreeNode> list = new LinkedList<>();
            list.add(root);
            List<Integer> data = new ArrayList<>();
            while(!list.isEmpty()) {
                TreeNode node = list.pop();
                data.add(node.val);
                if(node.left != null) list.add(node.left);
                if(node.right != null) list.add(node.right);
            }
            int[] res = new int[data.size()];
            for(int i = 0; i < data.size(); i++)
                res[i] = data.get(i);
            return res;
        }


        public int[] levelOrder1(TreeNode node) {
            if (node == null) return new int[0];
            List<Integer> list = new LinkedList<>();
            List<TreeNode> nodes = new ArrayList<>();
            nodes.add(node);
            bfs(nodes, list);
            int[] res = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                res[i] = list.get(i);
            }
            return res;
        }

        private void bfs(List<TreeNode> nodes, List<Integer> list) {
            if (nodes == null || nodes.isEmpty()) return;
            List<TreeNode> nextNodes = new ArrayList<>(nodes.size() * 2);
            for (TreeNode node : nodes) {
                list.add(node.val);
                if (node.left!=null) nextNodes.add(node.left);
                if (node.right!=null) nextNodes.add(node.right);
            }
            bfs(nextNodes, list);
        }
    }
}
