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
            ListNode head = cur; //定义head节点
            ListNode pre = null;  //定义前置节点
            while (cur != null){
                if(cur.val == val){
                    // 若为头节点:返回头节点的下一个节点,并删除原头节点
                    if(head.val == val) {
                        ListNode next = head.next;
                        head.next = null;//help GC
                        return next;
                    }
                    pre.next = cur.next;//前节点指向当前的下一个节点
                    return head;
                }
                pre = cur; //前结点改为当前节点
                cur = cur.next; //当前节点置为next节点
            }
            return head;
        }
    }
}
