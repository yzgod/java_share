package com.java_share.juc.cache_line;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.CountDownLatch;

/**
 * @author yz
 * @date 2020-07-20 00:47
 * <p>
 *      缓存行测试
 * </p>
 **/
public class CacheLine {
    static int num  = 1_0000_0000;
    static int thread_num  = 2;

    public static void main(String[] args) throws Exception{
        System.out.println(ClassLayout.parseClass(T1.class).toPrintable());
        System.out.println(ClassLayout.parseClass(T2.class).toPrintable());
        cacheLineAlign();
        cacheLineNotAlign();
    }
    private static void cacheLineAlign() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2 * thread_num);
        T1 t = new T1();
        long start = System.currentTimeMillis();
        for (int i = 0; i < thread_num; i++) {
            new Thread(()->{
                for (int j = 0; j < num; j++) t.a = j;
                countDownLatch.countDown();
            }).start();
            new Thread(()->{
                for (int j = 0; j < num; j++) t.b = j;
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("缓存行对齐耗时:"+(end - start));
    }

    private static void cacheLineNotAlign() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(thread_num);
        T2 t = new T2();
        long start = System.currentTimeMillis();
        for (int i = 0; i < thread_num; i++) {
            new Thread(()->{
                for (int j = 0; j < num; j++) {
                    t.a = j;
                }
                countDownLatch.countDown();
            }).start();
            new Thread(()->{
                for (int j = 0; j < num; j++) {
                    t.b = j;
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("缓存行未对齐耗时:"+(end - start));
    }

    static class T1{
        volatile long a;
        // 占用 56 byte
        volatile long p1,p2,p3,p4,p5,p6,p7;
        volatile long b;
    }
    static class T2 {
        volatile long a;
        volatile long b;
    }
}
