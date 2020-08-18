package com.java_share.juc.l12_interview;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yz
 * @date 2020-08-18 17:52
 * <p>
 *    面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 *      能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *      使用wait和notify/notifyAll来实现
 *      使用Lock和Condition来实现
 *      对比两种方式，Condition的方式可以更加精确的指定哪些线程被唤醒
 * </p>
 **/
public class ContainerTest {

    static class Container<T> {

        private int maxSize;
        private volatile int count;

        public Container(int maxSize) {
            this.maxSize = maxSize;
        }

        LinkedList<T> queue = new LinkedList<>();
        ReentrantLock lock = new ReentrantLock();
        Condition producer = lock.newCondition();
        Condition consumer = lock.newCondition();

        public T get(){
            lock.lock();
            try {
                while (queue.size() == 0) {
                    consumer.await();
                }
                T t = queue.removeLast();
                count--;
                producer.signalAll();
                return t;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            return null;
        }

        public void put(T t){
            lock.lock();
            try {
                if (queue.size() == maxSize) {
                    producer.await();
                }
                queue.addFirst(t);
                count ++;
                consumer.signalAll();
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

        public int getCount(){
            return count;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Container<Object> container = new Container<>(10);
        // 生产
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                for (int j = 0; j < 10; j++) {
                    container.put(Thread.currentThread().getName()+"--"+j);
                }
            }).start();
        }

        // 消费
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                while (true) {
                    Object data = container.get();
                    System.out.println(Thread.currentThread().getName()+ "消费了: "+data);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        Thread.sleep(10000L);
        System.out.println("继续----");

        new Thread(()->{
            for (int j = 0; j < 10; j++) {
                container.put(Thread.currentThread().getName()+"--"+j);
            }
        }).start();
    }
}
