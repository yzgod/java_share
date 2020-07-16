package com.java_share.algorithm.jzoffer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yz
 * @date 2020-07-16 14:20
 * <br>
 *     剑指 Offer 36. 二叉搜索树与双向链表
 *
 * </br>
 * <p>
 * </p>
 **/
public class JZ36_TreeToDoublyList {

    public static void main(String[] args){
        Node h1 = new Node(2);
        Node h2 = new Node(1);
        Node h3 = new Node(3);
        h1.left = h2;
        h1.right = h3;
        Node node = new Solution().treeToDoublyList(h1);
        System.out.println(node);
    }

    static class Solution {

        Node pre, head;

        // 中序递增
        public Node treeToDoublyList(Node node) {
            if (node == null) return null;
            dfs(node);
            head.left = pre;
            pre.right = head;
            return head;
        }

        // 中序遍历
        private void dfs(Node node) {
            if (node == null) return;
            dfs(node.left);
            if (pre == null)
                head = node;
            else
                pre.right = node;
            node.left = pre;
            pre = node;
            dfs(node.right);
        }


    }

    static class Node{

        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }
}
