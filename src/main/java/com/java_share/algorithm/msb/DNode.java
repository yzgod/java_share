package com.java_share.algorithm.msb;

/**
 * @author yz
 * @date 2020-08-11 13:28
 * <p></p>
 **/
public class DNode {

    public int value;
    public DNode pre;
    public DNode next;

    public DNode(int data) {
        value = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DNode cur = this;
        while (cur != null) {
            sb.append(cur.value).append(",");
            cur = cur.next;
        }
        sb.delete(sb.length()-1, sb.length());
        return sb.toString();
    }

    public static DNode generateRandomDoubleList(int len, int value) {
        int size = (int) (Math.random() * (len + 1));
        if (size == 0) {
            return null;
        }
        size--;
        DNode head = new DNode((int) (Math.random() * (value + 1)));
        DNode pre = head;
        while (size != 0) {
            DNode cur = new DNode((int) (Math.random() * (value + 1)));
            pre.next = cur;
            cur.pre = pre;
            pre = cur;
            size--;
        }
        return head;
    }
}
