package com.java_share.base.cache_line;

import org.openjdk.jol.info.ClassLayout;
import sun.misc.Contended;

import java.util.concurrent.CountDownLatch;

/**
 * @author yz
 * @date 2020-07-20 00:47
 * <p>
 *     注意关闭限制Contented参数
 *     -XX:-RestrictContended
 * </p>
 **/
public class CacheLine_Contented {
    static int num  = 1_0000_0000;
    static int thread_num  = 2;


    public static void main(String[] args) throws Exception{
        System.out.println(ClassLayout.parseClass(Contented.class).toPrintable());
        System.out.println(ClassLayout.parseClass(NoContented.class).toPrintable());
        testContented();
        testNoContented();
    }
    private static void testContented() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2 * thread_num);
        Contented contented = new Contented();
        long start = System.currentTimeMillis();
        for (int i = 0; i < thread_num; i++) {
            new Thread(()->{
                for (int j = 0; j < num; j++) contented.a = j;
                countDownLatch.countDown();
            }).start();
            new Thread(()->{
                for (int j = 0; j < num; j++) contented.b = j;
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("contented 耗时:"+(end - start));
    }
    private static void testNoContented() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2 * thread_num);
        NoContented noContented = new NoContented();
        long start = System.currentTimeMillis();
        for (int i = 0; i < thread_num; i++) {
            new Thread(()->{
                for (int j = 0; j < num; j++) noContented.a = j;
                countDownLatch.countDown();
            }).start();
            new Thread(()->{
                for (int j = 0; j < num; j++) noContented.b = j;
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("No_contented 耗时:"+(end - start));
    }

    //@Contented
    static class Contented{
        @Contended
        volatile long a;
        @Contended
        volatile long b;
    }
    static class NoContented{
        volatile long a;
        volatile long b;
    }

}
