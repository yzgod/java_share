package com.java_share.base.l03_cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author yz
 * @date 2020-07-21 00:50
 * <p></p>
 **/
public class LongAdderTest {

    static int threadNum = 1000;
    static int addNums = 100000;

    public static void main(String[] args) throws InterruptedException {
        new Sync().test();
        new Atomic().test();
        new Adder().test();
    }


    static class Sync{
        long count = 0L;
        Object lock = new Object();
        void test() throws InterruptedException {
            CountDownLatch countDownLatch = new CountDownLatch(threadNum);
            long start = System.currentTimeMillis();
            for (int i = 0; i < threadNum; i++) {
                new Thread(()->{
                    for (int j = 0; j < addNums; j++) {
                        synchronized (lock) {
                            count++;
                        }
                    }
                    countDownLatch.countDown();
                }).start();
            }
            countDownLatch.await();
            long end = System.currentTimeMillis();
            System.out.println("synchronized("+ count+ ") 耗时:"+(end-start));
        }
    }

    static class Atomic{
        AtomicLong count = new AtomicLong(0L);
        void test() throws InterruptedException {
            CountDownLatch countDownLatch = new CountDownLatch(threadNum);
            long start = System.currentTimeMillis();
            for (int i = 0; i < threadNum; i++) {
                new Thread(()->{
                    for (int j = 0; j < addNums; j++) count.incrementAndGet();
                    countDownLatch.countDown();
                }).start();
            }
            countDownLatch.await();
            long end = System.currentTimeMillis();
            System.out.println("AtomicLong("+ count.get()+ ")   耗时:"+(end-start));
        }
    }

    static class Adder{
        LongAdder count = new LongAdder();
        void test() throws InterruptedException {
            CountDownLatch countDownLatch = new CountDownLatch(threadNum);
            long start = System.currentTimeMillis();
            for (int i = 0; i < threadNum; i++) {
                new Thread(()->{
                    for (int j = 0; j < addNums; j++) count.add(1L);
                    countDownLatch.countDown();
                }).start();
            }
            countDownLatch.await();
            long end = System.currentTimeMillis();
            System.out.println("LongAdder("+ count.longValue()+ ")    耗时:"+(end-start));
        }
    }
}
