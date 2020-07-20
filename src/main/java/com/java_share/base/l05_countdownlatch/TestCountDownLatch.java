package com.java_share.base.l05_countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author yz
 * @date 2020-07-21 01:55
 * <p>
 *
 * </p>
 **/
public class TestCountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            final int n = i;
            new Thread(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(n * 100L);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }).start();
        }
        latch.await();
        System.out.println(Thread.currentThread().getName());
    }
}
