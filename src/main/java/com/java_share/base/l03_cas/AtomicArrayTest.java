package com.java_share.base.l03_cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author yz
 * @date 2020-07-21 00:29
 * <p>
 *  Atomic数组类型, 支持对数组元素进行原子操作.
 * </p>
 **/
public class AtomicArrayTest {

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerArray array = new AtomicIntegerArray(2);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 10000000; j++) {
                    array.incrementAndGet(0);
                    array.incrementAndGet(1);
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(array.get(0));
        System.out.println(array.get(1));
    }

}
