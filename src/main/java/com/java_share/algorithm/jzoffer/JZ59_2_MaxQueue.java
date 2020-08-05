package com.java_share.algorithm.jzoffer;

import java.util.LinkedList;

/**
 * @author yz
 * @date 2020-07-20 17:52
 * <p>
 *      剑指 Offer 59 - II. 队列的最大值
 *
 *      请定义一个队列并实现函数 max_value 得到队列里的最大值，
 *      要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 *      若队列为空，pop_front 和 max_value 需要返回 -1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof
 * </p>
 **/
public class JZ59_2_MaxQueue {

    public static void main(String[] args){
        MaxQueue queue = new MaxQueue();
        queue.push_back(4);
        System.out.println(queue.max_value());
        queue.push_back(2);
        System.out.println(queue.max_value());
        queue.push_back(0);
        System.out.println(queue.max_value());
        queue.push_back(3);
        System.out.println(queue.max_value());

        queue.pop_front();
        System.out.println(queue.max_value());
        queue.pop_front();
        System.out.println(queue.max_value());
        queue.pop_front();
        System.out.println(queue.max_value());
        queue.pop_front();
        System.out.println(queue.max_value());

    }

    // 难点在于从队尾加元素, 队头pop, 那么max_queue添加和弹出要考虑前面的元素情况
    static class MaxQueue {

        LinkedList<Integer> queue = new LinkedList<>();
        LinkedList<Integer> queueMax = new LinkedList<>();

        public MaxQueue() {
        }

        public int max_value() {
            if (queueMax.isEmpty()) return -1;
            return queueMax.peek();
        }

        public void push_back(int value) {
            queue.addLast(value);
            while (!queueMax.isEmpty()){
                if (queueMax.getLast() < value) { // 比前一个max大,那么前一个删除
                    queueMax.pollLast();//队尾弹出
                }else {
                    break;
                }
            }
            queueMax.addLast(value);// 添加当前max
        }

        public int pop_front() {
            if (queue.isEmpty()) return -1;
            Integer first = queue.pollFirst();
            if (first.equals(queueMax.peek())){// head出队的元素如果与max队列头部相同,max弹出
                queueMax.pop();// 队头弹出
            }
            return first;
        }
    }



}
