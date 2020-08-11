package com.java_share.algorithm.msb.base.class02;

import com.java_share.algorithm.msb.Node;

/**
 * @author yz
 * @date 2020-08-11 17:49
 * <p>
 *     删除指定值的所有节点
 *
 *     -XX:+PrintGC
 * </p>
 **/
public class C02_DeleteNode {

    public static void main(String[] args) throws InterruptedException {
        Node remove = remove(Node.generateList(new int[]{1,1,1,1,1,1,1,1,5,6,7,1,1,8,1}), 1);
        System.out.println(remove);
        System.gc(); // 验证,记得node加大内存占用量
        // 每个node 1M时,结果如下, gc后剩余4M左右,刚好是4个节点内存
        // [GC (System.gc())  20604K->4872K(251392K), 0.0022746 secs]
        // [Full GC (System.gc())  4872K->4756K(251392K), 0.0043278 secs]
    }

    private static Node remove(Node node, int tar) {
        // java语言虽然没有手动删除头节点,但是没有引用指向头节点了,会被gc清除掉
        while (node != null && node.value == tar) {// 去相同的头
            node = node.next;
        }
        Node pre = node, cur = node; // 双指针
        while (cur != null) {
            if (cur.value == tar) pre.next = cur.next;// 跳过cur
            else pre = cur;// 不跳过
            cur = cur.next;// 继续循环
        }
        return node;
    }

}
