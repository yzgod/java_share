package com.java_share.algorithm.leetcode.m08;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yz
 * @date 2020-08-18 10:54
 * <p>
 *      146. LRU缓存机制
 *      运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。
 *      它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 *      获取数据 get(key) - 如果关键字 (key) 存在于缓存中，
 *          则获取关键字的值（总是正数），否则返回 -1。
 *
 *      写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；
 *          如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，
 *          它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * 进阶:
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache
 * </p>
 **/
public class L146_LRU {

    public static void main(String[] args){
        LRUCache cache = new LRUCache(5);
        for (int i = 0; i < 100000; i++) {
            int num = (int) (Math.random() * 100);
            cache.put(num, num);
            if (num % 3 == 0) {
                cache.get(num);
            }
        }
    }

    // O(1)操作,hashMap加双向链表
    static class LRUCache {
        Map<Integer, Node> map;
        int capacity;
        Node head;// 头节点.next -- 最近使用的数据
        Node tail;// 尾结点.pre  -- 即将淘汰的数据

        public LRUCache(int capacity) {
            if (capacity <1) capacity = 1;
            map = new HashMap<>(capacity);
            this.capacity = capacity;
            head = new Node(-1,-1);
            tail = new Node(-1,-1);
            tail.pre = head;
            head.next = tail;
        }

        public int get(int key) {
            Node node = map.get(key);
            if (node == null) return -1;
            move2Head(node);
            return node.val;
        }

        public void put(int key, int value) {
            Node node = map.get(key);
            if (node == null) {// 添加
                Node newNode = new Node(key, value);
                addHead(newNode);
                if (map.size() >= capacity) {// 淘汰
                    Node tmp = removeTail();
                    map.remove(tmp.key);
                }
                map.put(key, newNode);
            }else {// 修改
                move2Head(node);
                node.val = value;// 修改数据
            }
        }

        // 将节点移动到头节点
        private void move2Head(Node node) {
            node.next.pre = node.pre;
            node.pre.next = node.next;
            addHead(node);
        }

        // 添加到头节点
        private void addHead(Node node) {
            node.pre = head;
            node.next = head.next;
            head.next.pre = node;
            head.next = node;
        }

        // 删除尾结点
        private Node removeTail() {
            Node tmp = tail.pre;
            tail.pre = tmp.pre;
            tmp.pre.next = tail;
            tmp.next = null;// help gc
            tmp.pre = null;
            return tmp;
        }

        class Node {
            int key;
            int val;
            Node pre;
            Node next;

            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "key=" + key +
                        ", val=" + val +
                        '}';
            }
        }
    }

}
