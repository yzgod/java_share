package com.java_share.algorithm.jzoffer;

import java.util.Stack;

/**
 * @author yz
 * @date 2020-07-15 13:39
 * <br>
 *      剑指 Offer 25. 合并两个排序的链表
 * </br>
 * <br>
 *   栈
 * </br>
 **/
public class JZ25_MergeTwoLists {

    public static void main(String[] args){
        ListNode l1 = ListNode.deserialize(new int[]{1,2,3,4});
        ListNode l2 = ListNode.deserialize(new int[]{1,3,5,7});
        ListNode merge = new Solution().mergeTwoLists(l1, l2);
        System.out.println(merge);
    }

    static class Solution {

        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            // 终结条件
            if(l1 == null) return l2;
            if(l2 == null) return l1;
            if(l1.val <= l2.val){ // l1<=l2,那么l1.next与l2 merge,返回l1
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            }
            l2.next = mergeTwoLists(l1, l2.next);//相反l2.next与l1 merge返回l2
            return l2;
        }

    }
}
