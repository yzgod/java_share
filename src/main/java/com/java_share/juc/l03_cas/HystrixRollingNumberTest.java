package com.java_share.juc.l03_cas;

import com.netflix.hystrix.util.HystrixRollingNumber;
import com.netflix.hystrix.util.HystrixRollingNumberEvent;

import java.util.concurrent.CountDownLatch;

/**
 * @author yz
 * @date 2020-07-21 17:43
 * <p>
 *     HystrixRollingNumber应用场景:qps统计
 * </p>
 **/
public class HystrixRollingNumberTest {

    static int threadNum = 100;
    static int addNums = 1000000;
    public static void main(String[] args) throws InterruptedException {
        HystrixRollingNumber number = new HystrixRollingNumber(1000, 100);
        CountDownLatch latch = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            new Thread(()->{
                for (int j = 0; j < addNums; j++) {
                    if (j % 1000 == 0) number.add(HystrixRollingNumberEvent.FAILURE, 1);
                    else number.add(HystrixRollingNumberEvent.SUCCESS, 1);
                }
                latch.countDown();
            }).start();
        }
        latch.await();
        long success = number.getRollingSum(HystrixRollingNumberEvent.SUCCESS);
        long failure = number.getRollingSum(HystrixRollingNumberEvent.FAILURE);
        System.out.println("成功请求QPS: "+success);
        System.out.println("失败请求QPS: "+failure);
        long maxSuccess = number.getRollingMaxValue(HystrixRollingNumberEvent.SUCCESS);
        long maxFailure = number.getRollingMaxValue(HystrixRollingNumberEvent.FAILURE);
        System.out.println("成功请求单bucket最大QPS: "+maxSuccess);
        System.out.println("失败请求单bucket最大QPS: "+maxFailure);
    }

}
