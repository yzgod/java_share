package com.java_share.project.bloom;

import java.util.BitSet;

/**
 * @author yz
 * @date 2020-07-13 23:38
 * <br>
 *     手撸简易布隆过滤器
 * </br>
 **/
public class MyBloomFilter {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    // 100 以内大于10质数
    static final int[] seed_arr
            = new int[] {11,13,17,19,23,29,31,37,41,43,47,53,
            59,61,67,71,73,79,83,89,97 };

    private BitSet bitSet;

    private int capacity;

    public MyBloomFilter(int cap) {
        this.capacity = tableSizeFor(cap);
        bitSet = new BitSet(this.capacity);
    }

    public void put(Object o){
        for (int seed : seed_arr) {
            int tabAt = tabAt(seed, o);
            bitSet.set(tabAt);
        }
    }

    public boolean mightContain(Object o){
        for (int seed : seed_arr) {
            int tabAt = tabAt(seed, o);
            if(!bitSet.get(tabAt)){
                return false;
            }
        }
        return true;
    }

    private int tabAt(int seed, Object o){
        if(o == null){
            return 0;
        }
        int res = o.hashCode() * seed;
        return (capacity - 1) & res;
    }

    public int getCapacity() {
        return capacity;
    }

    // 计算BitSet的长度
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
