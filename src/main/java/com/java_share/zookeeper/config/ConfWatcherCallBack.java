package com.java_share.zookeeper.config;

import lombok.SneakyThrows;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * @author yz
 * @date 2020-08-10 00:27
 * <p></p>
 **/
public class ConfWatcherCallBack
        implements Watcher,
        AsyncCallback.StatCallback,
        AsyncCallback.DataCallback {

    private ZooKeeper zk;

    private MyConf conf = new MyConf();

    CountDownLatch latch = new CountDownLatch(1);

    public ConfWatcherCallBack(ZooKeeper zk) {
        this.zk = zk;
    }

    public MyConf getConf() {
        return conf;
    }

    public void await(){
        zk.exists("/app_conf",this, this, "ctx");
        try {
            latch.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        String str = new String(data);
        conf.setConf(str);
        latch.countDown();
    }

    @SneakyThrows
    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        if (stat!=null) {
            zk.getData(path, this, this, "ctx");
        }
    }

    @Override
    public void process(WatchedEvent event) {
        switch (event.getType()) {
            case None:
                break;
            case NodeCreated:
                zk.getData(event.getPath(), this, this, "ctx");
                break;
            case NodeDeleted:
                latch = new CountDownLatch(1);
                conf.setConf(null);
                break;
            case NodeDataChanged:
                await();
                break;
            case NodeChildrenChanged:
                break;
        }
    }
}
