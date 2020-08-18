package com.java_share.juc.l06_CyclicBarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author yz
 * @date 2020-07-21 02:00
 * <p>
 *     使用场景: 等待所有线程到达之后执行某个逻辑
 * </p>
 **/
public class TestCyclicBarrier {

    public static void main(String[] args){
        CyclicBarrier barrier = new CyclicBarrier(10, ()->{
            System.out.println("go go go");
        });
        for (int i = 0; i < 100; i++) {
            final int n = i;
            new Thread(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(n * 100L);
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
