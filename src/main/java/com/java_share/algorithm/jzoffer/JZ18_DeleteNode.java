package com.java_share.algorithm.jzoffer;

import java.util.Arrays;

/**
 * @author yz
 * @date 2020-07-15 13:39
 * <br>
 *      剑指 Offer 18. 删除链表的节点
 *
 *      给定单向链表的头指针和一个要删除的节点的值，
 *      定义一个函数删除该节点。
 *      返回删除后的链表的头节点。
 *
 * </br>
 * <br>
 * </br>
 **/
public class JZ18_DeleteNode {

    public static void main(String[] args){
        ListNode node = ListNode.deserialize(new int[]{4, 5, 1, 9});
        ListNode cur = new Solution().deleteNode(node, 1);
        System.out.println(cur);
    }

    static class Solution {

        public ListNode deleteNode(ListNode cur, int val) {
            ListNode head = cur;
            ListNode pre = cur;
            while (cur != null){
                if(cur.val == val){
                    if(head.val == val){
                        return head.next;
                    }else {
                        pre.next = cur.next;
                        return head;
                    }
                }
                pre = cur;
                cur = cur.next;
            }
            return head;
        }
    }
}
