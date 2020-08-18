package com.java_share.juc.l09_Semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author yz
 * @date 2020-07-21 02:25
 * <p>
 *     使用场景: 限流
 *      guava RateLimiter
 * </p>
 **/
public class TestSemaphore {

    public static void main(String[] args){
        Semaphore semaphore = new Semaphore(2, true);
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    Thread.sleep(500L);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
