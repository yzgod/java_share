package com.java_share.algorithm.jzoffer;

/**
 * @author yz
 * @date 2020-07-15 13:39
 * <br>
 *      剑指 Offer 22. 链表中倒数第k个节点
 *
 *      输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，
 *      本题从1开始计数，即链表的尾节点是倒数第1个节点。
 *      例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。
 *      这个链表的倒数第3个节点是值为4的节点。
 *
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 * 返回链表 4->5.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof
 *
 * </br>
 * <br>
 *  快慢指针解决
 * </br>
 **/
public class JZ22_GetKthFromEnd {

    public static void main(String[] args){
        ListNode node = ListNode.deserialize(new int[]{1,2,3,4});
        ListNode fromEnd = new Solution().getKthFromEnd(node, 7);
        System.out.println(fromEnd);
    }

    static class Solution {

        public ListNode getKthFromEnd(ListNode cur, int k) {
            ListNode head = cur;
            ListNode slow = null;
            int i = 0;
            while (cur != null) {
                if(++i == k) slow = head;
                if(i > k) slow = slow.next;
                if(cur == null && slow != null) {
                    ListNode tmp = slow.next;
                    slow.next = null;
                    return tmp;
                }
                cur = cur.next;
            }
            return slow;
        }

    }
}
