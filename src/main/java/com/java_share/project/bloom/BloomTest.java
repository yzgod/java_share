package com.java_share.project.bloom;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @author yz
 * @date 2020-07-14 00:03
 * <br></br>
 **/
public class BloomTest {

    public static void main(String[] args){
        myBloomTest();
        guavaBloomTest();
    }

    private static void myBloomTest() {
        MyBloomFilter myBloomFilter = new MyBloomFilter(20000000);
        for (int i = 0; i < 1000000; i++) {
            myBloomFilter.put(i);
        }
        int count = 0;
        for (int i = 1000000; i < 2000000; i++) {
            if(myBloomFilter.mightContain(i)){
                count++;
            }
        }
        System.out.println("BitSet长度:"+ myBloomFilter.getCapacity());
        System.out.println("误判次数:"+count +";误判率:"+
                ((double) count/1000000D));
    }


    private static void guavaBloomTest() {
        BloomFilter<Integer> guavaBloomFilter = BloomFilter.create(Funnels.integerFunnel(),
                1000000, 0.0011);
        for (int i = 0; i < 1000000; i++) {
            guavaBloomFilter.put(i);
        }
        int count = 0;
        for (int i = 1000000; i < 2000000; i++) {
            if(guavaBloomFilter.mightContain(i)){
                count++;
            }
        }
        System.out.println("误判次数:"+count +";误判率:"+
                ((double) count/1000000D));
    }
}
