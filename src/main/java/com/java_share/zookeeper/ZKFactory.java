package com.java_share.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @author yz
 * @date 2020-08-10 00:11
 * <p></p>
 **/
public class ZKFactory {

    private static String url = "10.211.55.20:2181,10.211.55.21:2181,10.211.55.10:2181,10.211.55.30:2181";


    public static ZkWrapper createSession(){
        return new ZkWrapper(url, 2000, new DefaultWatcher());
    }

    public static class ZkWrapper {

        private ZooKeeper zk;

        private DefaultWatcher watcher;

        public ZkWrapper(String url, int sessionTimeout,DefaultWatcher watcher) {
            this.watcher = watcher;
            try {
                zk = new ZooKeeper(url, sessionTimeout, watcher);
                watcher.countDownLatch.await();// 等待连接准备好
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public void close(){
            try {
                Thread.sleep(5000);
                zk.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        public ZooKeeper getZk() {
            return zk;
        }

        public DefaultWatcher getWatcher() {
            return watcher;
        }
    }


    static class DefaultWatcher implements Watcher {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        @Override
        public void process(WatchedEvent event) {
            switch (event.getState()) {
                case Unknown:
                    break;
                case Disconnected:
                    break;
                case NoSyncConnected:
                    break;
                case SyncConnected:
                    System.out.println("连接成功!");
                    countDownLatch.countDown();
                    break;
                case AuthFailed:
                    break;
                case ConnectedReadOnly:
                    break;
                case SaslAuthenticated:
                    break;
                case Expired:
                    break;
            }
        }
    }

}
