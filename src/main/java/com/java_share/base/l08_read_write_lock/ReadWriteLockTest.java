package com.java_share.base.l08_read_write_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author yz
 * @date 2020-07-21 10:48
 * <p>
 *     读写锁
 *     应用场景: 读多写少
 *     读读不互斥;
 *     读写, 写写互斥;
 * </p>
 **/
public class ReadWriteLockTest {

    static ReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args){
        for (int i = 0; i < 10; i++) {
            if (i == 3 || i == 4){
                new Thread(ReadWriteLockTest::write).start();
            }else {
                new Thread(ReadWriteLockTest::read).start();
            }
        }
    }

    static void read(){
        Lock readLock = lock.readLock();
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() +" 开始读取数据");
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName() +" 读取了数据");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    static void write(){
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() +" 开始写入数据------开始等待写入数据");
            TimeUnit.SECONDS.sleep(5);
            System.out.println(Thread.currentThread().getName() +" 写入了数据--------等待了5s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

}
