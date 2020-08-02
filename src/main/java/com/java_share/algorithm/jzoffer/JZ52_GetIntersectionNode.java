package com.java_share.algorithm.jzoffer;

/**
 * @author yz
 * @date 2020-07-20 17:52
 * <p>
 *      剑指 Offer 52. 两个链表的第一个公共节点
 * </p>
 **/
public class JZ52_GetIntersectionNode {

    public static void main(String[] args){
        ListNode a = ListNode.deserialize(new int[]{4, 1, 8, 4, 5});
        ListNode b = ListNode.deserialize(new int[]{5, 0, 1, 8, 4, 5});
        a.next.next = b.next.next.next;

        ListNode node = new Solution().getIntersectionNode(a, b);
        System.out.println(node);
    }

    static class Solution{

        public ListNode getIntersectionNode(ListNode a, ListNode b) {
            ListNode a1 = a, b1 = b;
            while (a1 != b1){
                if (a1 == null) a1 = b;//a切换b
                else a1 = a1.next;
                if (b1 == null) b1 = a;//b切换a
                else b1 = b1.next;
            }
            return a1;//当循环ab完结时,a=b=null,返回null
        }

    }



}
