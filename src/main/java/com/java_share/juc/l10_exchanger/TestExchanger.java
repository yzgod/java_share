package com.java_share.juc.l10_exchanger;

import java.util.concurrent.Exchanger;

/**
 * @author yz
 * @date 2020-07-21 02:31
 * <p>
 *     线程之间交换数据
 *     使用场景: 线程之间通信
 * </p>
 **/
public class TestExchanger {

    public static void main(String[] args){
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(()->{
            try {
                String a = "线程A的变量";
                a = exchanger.exchange(a);
                System.out.println(Thread.currentThread().getName()+":"+a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(()->{
            try {
                String b = "线程B的变量";
                b = exchanger.exchange(b);
                System.out.println(Thread.currentThread().getName()+":"+b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

    }

}
