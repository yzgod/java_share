package com.java_share.algorithm.jzoffer;

/**
 * @author yz
 * @date 2020-07-14 15:30
 * <br></br>
 **/
public class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }


    public static ListNode deserialize(int[] arr){
        ListNode head = new ListNode(arr[0]);
        ListNode cur = null;
        for (int i = 1; i < arr.length; i++) {
            ListNode pre = cur;
            cur = new ListNode(arr[i]);
            if(i == 1){
                pre = head;
            }
            pre.next = cur;
        }
        return head;
    }
}
