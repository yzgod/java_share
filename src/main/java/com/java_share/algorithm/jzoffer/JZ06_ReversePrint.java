package com.java_share.algorithm.jzoffer;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author yz
 * @date 2020-07-14 14:26
 * <br>
 *     剑指 Offer 06. 从尾到头打印链表
 *     输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * </br>
 * <br>
 *     利用栈先进后出的特性
 * </br>
 **/
public class JZ06_ReversePrint {

    public static void main(String[] args){
        ListNode head = new ListNode(1);
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(3);
        head.next = n1;
        n1.next = n2;
        System.out.println(Arrays.toString(new Solution().reversePrint(head)));
    }

    static class Solution {

        public int[] reversePrint(ListNode head) {
            Stack<Integer> stack = new Stack<>();
            while (head != null){
                stack.push(head.val);
                head = head.next;
            }
            int[] arr = new int[stack.size()];
            int i = 0;
            while (!stack.isEmpty()){
                arr[i++] = stack.pop();
            }
            return arr;
        }
    }
}
