package com.java_share.algorithm.jzoffer;

import java.util.*;

/**
 * @author yz
 * @date 2020-07-16 14:20
 * <br>
 *     剑指 Offer 34. 二叉树中和为某一值的路径
 *
 *      输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
 *      从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 *
 * </br>
 * <p>
 * </p>
 **/
public class JZ35_CopyRandomList {

    public static void main(String[] args){
        Node h1 = new Node(1);
        Node h2 = new Node(2);
        h1.next = h2;
        h1.random = h2;
        Node node = new Solution().copyRandomList(h1);
        System.out.println(node);
    }

    static class Solution {

        public Node copyRandomList(Node cur) {
            if(cur == null) return null;
            Node head = cur;
            Map<Node, Node> map = new HashMap<>();
            while (cur != null){// 建立索引
                map.put(cur, new Node(cur.val));
                cur = cur.next;
            }
            Node res = map.get(head), tmp; //res锁定head节点, 定义tmp用来建立关系
            while (head != null){
                tmp = map.get(head);
                tmp.next = map.get(head.next);
                tmp.random = map.get(head.random);
                head = head.next;
            }
            return res;
        }

    }

    static class Node{

        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
        }

    }
}
