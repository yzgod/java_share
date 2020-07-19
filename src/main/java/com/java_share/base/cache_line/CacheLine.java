package com.java_share.base.cache_line;

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

    // 使用数组,让两个对象的缓存行连续
    static T[] arr = new T[2];

    static {
        arr[0] = new T1();
        System.out.println(ClassLayout.parseClass(T1.class).toPrintable(arr[0]));
        arr[1] = new T2();
        System.out.println(ClassLayout.parseClass(T2.class).toPrintable(arr[1]));
    }

    public static void main(String[] args) throws Exception{
        testContented();
        testNoContented();
    }

    private static void testContented() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(thread_num);
        long start = System.currentTimeMillis();
        for (int i = 0; i < thread_num; i++) {
            new Thread(()->{
                for (int j = 0; j < num; j++) {
                    arr[0].test(j);
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("T1 耗时:"+(end - start));
    }
    private static void testNoContented() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(thread_num);
        long start = System.currentTimeMillis();
        for (int i = 0; i < thread_num; i++) {
            new Thread(()->{
                for (int j = 0; j < num; j++) {
                    arr[1].test(j);
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("T2 耗时:"+(end - start));
    }

    static class T1 implements T{
        // 占用 56 byte
        volatile long p1,p2,p3,p4,p5,p6,p7;
        volatile long a;

        @Override
        public void test(long a) {
            this.a = a;
        }
    }
    static class T2 implements T{
        volatile long a;
        @Override
        public void test(long a) {
            this.a = a;
        }
    }

    interface T {
        void test(long a);
    }

}
