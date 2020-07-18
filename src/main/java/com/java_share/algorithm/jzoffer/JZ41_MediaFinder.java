package com.java_share.algorithm.jzoffer;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author yz
 * @date 2020-07-18 08:36
 * <p>
 *      剑指 Offer 41. 数据流中的中位数
 * </p>
 **/
public class JZ41_MediaFinder {

    public static void main(String[] args){
        MedianFinder finder = new MedianFinder();
        finder.addNum(1);
        System.out.println(finder.findMedian());
        finder.addNum(2);
        System.out.println(finder.findMedian());
        finder.addNum(3);
        System.out.println(finder.findMedian());
        finder.addNum(4);
        System.out.println(finder.findMedian());
        finder.addNum(5);
        System.out.println(finder.findMedian());
    }

    static class MedianFinder {
        //小顶堆, 保存较大的一半数据
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        //大顶堆, 保存较小的一半数据
        PriorityQueue<Integer> maxHeap
                = new PriorityQueue<>(Comparator.reverseOrder());

        public void addNum(int num) {
            // 永远是小顶堆size>=大顶堆size(不超过1)
            if (minHeap.size() == maxHeap.size()) {
                maxHeap.add(num);//先加入大顶堆
                minHeap.add(maxHeap.poll());//小顶堆再添加大顶堆较大的数
            }else {
                minHeap.add(num);//先加入小顶堆
                maxHeap.add(minHeap.poll());//大顶堆再添加小顶堆中较小的数
            }
        }
        // 中位数: size不等,取小顶堆,size相等,取大两堆堆顶数平均
        public double findMedian() {
            return minHeap.size()==maxHeap.size()
                    ? (double) (maxHeap.peek()+minHeap.peek())/2
                    : minHeap.peek();
        }
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

}
