package com.java_share.zookeeper;

import lombok.SneakyThrows;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author yz
 * @date 2020-08-09 16:49
 * <p>
 *     zookeeper api学习
 *
 *     zk是session的概念,一个连接就一个session,没有连接池的概念
 * </p>
 **/
public class ZookeeperClientApiTest {

    private static String url = "10.211.55.20:2181,10.211.55.21:2181,10.211.55.10:2181,10.211.55.30:2181";
    static String p = "/tt";
    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        ZooKeeper zk = getZooKeeper(latch);
        latch.await();// wait

        // create没有watch
        String path = zk.create(p, "data".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("create data path: " + path);

        Stat stat = new Stat();
        byte[] data = zk.getData(p, new Watcher() {
                @SneakyThrows
                @Override
                public void process(WatchedEvent e) {
                    System.out.println("get data watch: "+e.getState());
                    zk.getData(p, this, stat);
                }
        }, stat);
        System.out.println("get data: " + new String(data));

        Stat s1 = zk.setData(p, "2".getBytes(), 0);
        zk.setData(p, "hello world".getBytes(), s1.getVersion());

        System.out.println("=========异步get start=======");
        zk.getData(p, false, new AsyncCallback.DataCallback() {

            @Override
            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
                System.out.println("=========异步get callback=======");
                System.out.println("ctx: "+ctx);
                System.out.println("path:" + path + ", data:"+ new String(data));
            }
        }, "ctx");
        System.out.println("=========异步get end=======");

        Thread.sleep(10000);

    }

    private static ZooKeeper getZooKeeper(CountDownLatch latch) throws IOException {
        return new ZooKeeper(url, 3000,
                e -> {
                    Watcher.Event.KeeperState state = e.getState();
                    System.out.println("default watch:"+ state);
                    switch (state) {
                        case Unknown:
                            break;
                        case Disconnected:
                            break;
                        case NoSyncConnected:
                            break;
                        case SyncConnected:
                            System.out.println("SyncConnected...");
                            latch.countDown();
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
                    latch.countDown();
                }
            );
    }

}
