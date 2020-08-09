package com.java_share.zookeeper.code;

import org.apache.zookeeper.ZooKeeper;

import java.util.Date;

/**
 * @author yz
 * @date 2020-08-10 00:21
 * <p>
 *     分布式配置, 配置更新,立马程序拿到最新,配置删除,等待新的配置
 * </p>
 **/
public class TestConf {

    public static void main(String[] args) throws InterruptedException {
        ZKFactory.ZkWrapper wrapper = ZKFactory.createSession();
        ZooKeeper zk = wrapper.getZk();
        try {
            ConfWatcherCallBack wc = new ConfWatcherCallBack(zk);
            wc.await();
            MyConf conf = wc.getConf();
            while (true) {
                if (conf.getConf() == null) {
                    System.out.println("配置被删除了");
                    wc.await();// 等待
                }
                Thread.sleep(1000);
                System.out.println(new Date() + ":"+ conf.getConf());
            }
        } finally {
            wrapper.close();
        }
    }


}
