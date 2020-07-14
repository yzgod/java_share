package com.java_share.algorithm.jzoffer;

import java.util.Stack;

/**
 * @author yz
 * @date 2020-07-14 14:26
 * <br>
 *    剑指 Offer 09. 用两个栈实现队列
 *     用两个栈实现一个队列。队列的声明如下，请实现它的两个函数
 *     appendTail 和 deleteHead ，分别完成在队列尾部插入整数
 *     和在队列头部删除整数的功能。
 *     (若队列中没有元素，deleteHead 操作返回 -1 )
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
 * </br>
 * <br>
 *
 * </br>
 **/
public class JZ09_CQueue {

    public static void main(String[] args){
        CQueue queue = new CQueue();
        queue.appendTail(1);
        queue.appendTail(2);
        System.out.println(queue.deleteHead());
        queue.appendTail(3);
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
    }

    static class CQueue {

        Stack<Integer> addStack = new Stack();
        Stack<Integer> delStack = new Stack();

        public void appendTail(int value) {
            while (!delStack.isEmpty()){
                addStack.push(delStack.pop());
            }
            addStack.push(value);
        }

        public int deleteHead() {
            while (!addStack.isEmpty()){
                delStack.push(addStack.pop());
            }
            if(!delStack.isEmpty()){
                return delStack.pop();
            }
            return  -1;
        }
    }

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
}
