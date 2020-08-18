package com.java_share.juc.l03_cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yz
 * @date 2020-07-21 00:29
 * <p>
 *  Atomic基本类型,应用无锁做cas操作,提供原子操作的方法
 *  AtomicReference,让对象拥有cas操作的能力
 * </p>
 **/
public class AtomicTest {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger count = new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 10000000; j++) {
                    count.incrementAndGet();
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(count.get());
    }

}
