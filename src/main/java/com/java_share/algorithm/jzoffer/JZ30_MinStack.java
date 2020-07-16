package com.java_share.algorithm.jzoffer;

import java.util.Stack;

/**
 * @author yz
 * @date 2020-07-15 13:39
 * <br>
 *      剑指 Offer 30. 包含min函数的栈
 *      定义栈的数据结构，请在该类型中实现一个能够得到栈的
 *      最小元素的 min 函数在该栈中，
 *      调用 min、push 及 pop 的时间复杂度都是 O(1)。
 * </br>
 * <br>
 * </br>
 **/
public class JZ30_MinStack {

    public static void main(String[] args){

    }

    static class MinStack {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();
        public void push(int x) {
            stack.push(x);
            if (minStack.isEmpty()) minStack.push(x);
            else minStack.push(Math.min(minStack.peek(), x));
        }
        public void pop() {
            if(stack.isEmpty()) return;
            minStack.pop();
            stack.pop();
        }
        public int top() { return stack.peek(); }
        public int min() { return minStack.peek();}
    }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */
}
