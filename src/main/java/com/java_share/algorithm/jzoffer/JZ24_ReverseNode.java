package com.java_share.algorithm.jzoffer;

import java.util.Stack;

/**
 * @author yz
 * @date 2020-07-15 13:39
 * <br>
 *      剑指 Offer 24. 反转链表
 * </br>
 * <br>
 *   栈
 * </br>
 **/
public class JZ24_ReverseNode {

    public static void main(String[] args){
        ListNode node = ListNode.deserialize(new int[]{1,2,3,4});
        ListNode reverse = new Solution().reverseList(node);
        System.out.println(reverse);
    }

    static class Solution {

        public ListNode reverseList(ListNode head) {
            //定义当前指针和前指针
            ListNode cur = null, pre = head;
            while (pre != null){
                ListNode next = pre.next;// 记录pre.next
                pre.next = cur;// pre.next重置为当前
                cur = pre;// 当前置为pre
                pre = next;// pre置为next
            }
            return cur;
        }

        public ListNode reverseList1(ListNode cur) {
            Stack<ListNode> stack = new Stack<>();
            while (cur != null){
                ListNode tmp = cur.next;
                cur.next = null;
                stack.push(cur);
                cur = tmp;
            }
            ListNode reverse = null;
            ListNode pre = null;
            while (!stack.isEmpty()){
                if(reverse == null){
                    reverse = stack.pop();
                    pre = reverse;
                }else {
                    pre.next = stack.pop();
                    pre = pre.next;
                }
            }
            return reverse;
        }

    }
}
