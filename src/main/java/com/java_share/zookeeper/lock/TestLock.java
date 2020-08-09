package com.java_share.zookeeper.lock;

import com.java_share.zookeeper.ZKFactory;

/**
 */
public class TestLock {


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    WatchCallBack watchCallBack = new WatchCallBack();
                    ZKFactory.ZkWrapper wrapper = ZKFactory.createSession();
                    watchCallBack.setZk(wrapper.getZk());
                    String threadName = Thread.currentThread().getName();
                    watchCallBack.setThreadName(threadName);
                    //每一个线程：
                    //抢锁
                    watchCallBack.tryLock();
                    //干活
                    System.out.println(threadName+" working...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //释放锁
                    watchCallBack.unLock();
                    wrapper.close();
                }
            }.start();
        }
    }




}
