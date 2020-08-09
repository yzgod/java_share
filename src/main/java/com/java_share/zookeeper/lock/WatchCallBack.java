package com.java_share.zookeeper.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 *
 */
public class WatchCallBack
        implements Watcher,
        AsyncCallback.StringCallback ,
        AsyncCallback.Children2Callback ,
        AsyncCallback.StatCallback {

    ZooKeeper zk ;
    String threadName;
    CountDownLatch cc = new CountDownLatch(1);
    String pathName;

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public ZooKeeper getZk() {
        return zk;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    public void tryLock(){
        try {
            zk.create("/lock/a",threadName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL,this,"abc");
            cc.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void unLock(){
        try {
            zk.delete(pathName,-1);
            System.out.println(threadName + " work finish....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void process(WatchedEvent event) {
        //如果第一个哥们，那个锁释放了，其实只有第二个收到了回调事件！！
        //如果，不是第一个哥们，某一个，挂了，也能造成他后边的收到这个通知，从而让他后边那个跟去watch挂掉这个哥们前边的。。。
        switch (event.getType()) {
            case None:
                break;
            case NodeCreated:
                break;
            case NodeDeleted:
                zk.getChildren("/lock",false,this ,"ctx");
                break;
            case NodeDataChanged:
                break;
            case NodeChildrenChanged:
                break;
        }

    }

    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        if(name != null ){
            System.out.println(threadName  +"  create node : " +  name );
            pathName =  name ;
            zk.getChildren("/lock",false,this ,"sdf");
        }

    }

    //getChildren  call back
    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
        // 每次都重新拿children排序,保证每次都是节点最小的获得锁countdown
        Collections.sort(children);
        System.out.println("剩余子树节点:"+children.size());
        String sb = pathName.substring(6);
        if (sb.equals(children.get(0))){
            System.out.println(threadName +" i am first....");
//                zk.setData("/lock",threadName.getBytes(),-1);// 设置标识id,实现可重入锁
            cc.countDown();
        }else{
            int i = Collections.binarySearch(children, sb);
            zk.exists("/lock/"+children.get(i-1),this,this,"sdf");
        }

    }

    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        //偷懒
    }
}
