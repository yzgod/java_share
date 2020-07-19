package com.java_share.algorithm.jzoffer;

import java.util.PriorityQueue;

/**
 * @author yz
 * @date 2020-07-18 08:36
 * <p>
 *      剑指 Offer 45. 把数组排成最小的数
 * </p>
 **/
public class JZ45_MinNumber {

    public static void main(String[] args){
        int[] nums = {1,10,3,0};
        System.out.println(new Solution().minNumber(nums));
    }

    static class Solution {

        //利用堆完成
        public String minNumber(int[] nums) {
            //最小的数字字符串会排在前面
            PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2)
                    -> (o1.toString() + o2.toString())
                    .compareTo(o2.toString() + o1.toString()));
            for (int num : nums) {
                heap.add(num);
            }
            StringBuilder sb = new StringBuilder();
            while (!heap.isEmpty()) {
                sb.append(heap.poll());
            }
            return sb.toString();
        }

    }

}
