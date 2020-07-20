package com.java_share.base.l02_synchronized;

/**
 * @author yz
 * @date 2020-07-21 00:13
 * <p>
 *      synchronize选择锁的时候不要选择池化对象,
 *      如字符串,基本数据类型(有缓存可能);
 *      这也是一种规范,用上述类型会导致意想不到的结果,比如死锁
 * </p>
 **/
public class Synchronized {

//    static Object lock = new String("lock").intern();
//    static Object lock2 = new String("lock").intern();
    static Object lock = Integer.valueOf(100);
    static Object lock2 = 100;

    public static void main(String[] args){
        Thread t1 = new Thread(Synchronized::m1);
        Thread t2 = new Thread(Synchronized::m2);
        t1.start();
        t2.start();
    }

    static void m1(){
        synchronized (lock){
            try {
                System.out.println(Thread.currentThread().getName() +":获取到了锁lock");
                Thread.sleep(1000L);
                System.out.println(Thread.currentThread().getName() +":执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static void m2(){
        synchronized (lock2){
            try {
                System.out.println(Thread.currentThread().getName() +":获取到了锁lock2");
                Thread.sleep(1000L);
                System.out.println(Thread.currentThread().getName() +":执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
