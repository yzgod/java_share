package com.java_share.juc.l11_lock_support;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author yz
 * @date 2020-08-18 16:34
 * <p></p>
 **/
public class LockSupportTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                if (i==4) {
                    LockSupport.park();
                }
                try {
                    System.out.println(i+1);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
//        LockSupport.unpark(thread);
        TimeUnit.SECONDS.sleep(10);
        System.out.println("等待了10s");
        LockSupport.unpark(thread);
    }

}
