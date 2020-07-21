package com.java_share.base.l03_cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;

/**
 * @author yz
 * @date 2020-07-21 11:19
 * <p></p>
 **/
public class LongAccumulatorTest {

    static int threadNum = 1000;
    static int addNums = 1000000;

    public static void main(String[] args){
        oneThread();
        multiThread();
    }

    private static void multiThread() {
        CountDownLatch latch = new CountDownLatch(threadNum);
        LongAccumulator accumulator2 = getAccumulator();
        long start = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            new Thread(()->{
                for (int j = 0; j < addNums; j++) {
                    accumulator2.accumulate(1);
                }
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("多线程计算结果:"+accumulator2.get()+" 耗时:"+(end - start));
    }

    private static void oneThread() {
        LongAccumulator accumulator = getAccumulator();
        long start = System.currentTimeMillis();
        for (int i = 0; i < threadNum * addNums; i++) {
            accumulator.accumulate(1);
        }
        long end = System.currentTimeMillis();
        System.out.println("单线程计算结果:"+accumulator.get()+" 耗时:"+(end - start));
    }

    private static LongAccumulator getAccumulator() {
        return new LongAccumulator((x, y) -> {
            long sum = x + y;
            if (sum % 2 == 0) return sum + 1;
            return sum;
        }, 0);
    }
}
