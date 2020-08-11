package com.java_share.algorithm.msb;

/**
 * @author yz
 * @date 2020-08-11 13:27
 * <p></p>
 **/
public class Node {
    public int value;
    public Node next;

    // 用来测试删除时的gc
//    private byte[] bytes = new byte[1024*1024];

    public Node(int data) {
        value = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = this;
        while (cur != null) {
            sb.append(cur.value).append(",");
            cur = cur.next;
        }
        sb.delete(sb.length()-1, sb.length());
        return sb.toString();
    }

    public static Node generateRandomList(int len, int value) {
        int size = (int) (Math.random() * (len + 1));
        if (size == 0) return null;
        size--;
        Node head = new Node((int) (Math.random() * (value + 1)));
        Node pre = head;
        while (size != 0) {
            Node cur = new Node((int) (Math.random() * (value + 1)));
            pre.next = cur;
            pre = cur;
            size--;
        }
        return head;
    }

    public static Node generateList(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        Node head = new Node(nums[0]);
        Node pre = head;
        int k = 1;
        while (k < nums.length) {
            Node cur = new Node(nums[k++]);
            pre.next = cur;
            pre = cur;
        }
        return head;
    }


}
