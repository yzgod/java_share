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
            PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> {
                long l0 = Long.parseLong(o1.toString() + o2.toString());
                long l1 = Long.parseLong(o2.toString() + o1.toString());
                return Long.compare(l0, l1);
            });
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
