package com.java_share.algorithm.msb.base.class02;

import com.java_share.algorithm.msb.DNode;
import com.java_share.algorithm.msb.Node;

/**
 * @author yz
 * @date 2020-08-11 13:29
 * <p>
 *     单链表翻转, 双链表翻转
 * </p>
 **/
public class C01_ReverseList {

    public static void main(String[] args) {
        testReverse();
        testReverseDouble();
    }

    private static void testReverseDouble() {
        DNode node = DNode.generateRandomDoubleList(10, 100);
        System.out.println(node);
        DNode reverseDouble = reverseDouble(node);
        System.out.println(reverseDouble);
    }

    private static void testReverse() {
        Node node = Node.generateRandomList(10, 100);
        System.out.println(node);
        Node reverse = reverse(node);
        System.out.println(reverse);
    }

    public static Node reverse(Node node) {
        Node p = null;
        while (node != null) {
            Node tmp = node.next;
            node.next = p;
            p = node;
            node = tmp;
        }
        return p;
    }

    public static DNode reverseDouble(DNode node) {
        DNode p = null;
        while (node != null) {
            DNode next = node.next;
            node.next = p;
            node.pre = next;
            p = node;
            node = next;
        }
        return p;
    }

}
