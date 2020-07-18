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

        // 定义pre节点和head节点
        Node pre, head;
        public Node treeToDoublyList(Node node) {
            if (node == null) return null;
            head = node;
            dfs(node);
            head.left = pre; //最后连接head和pre
            pre.right = head;
            return head;
        }
        // 中序遍历
        private void dfs(Node cur) {
            if (cur == null) return;
            dfs(cur.left);//左
            // 处理中间逻辑
            if (pre == null) head = cur;// 此时找到head即为最小的左节点
            else pre.right = cur;//pre的right指向当前节点
            cur.left = pre;//当前node的left指向pre,形成环
            pre = cur;//pre切换指向当前node
            dfs(cur.right);//右
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
