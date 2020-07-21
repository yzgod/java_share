package com.java_share.base.l07_phaser;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author yz
 * @date 2020-07-21 09:59
 * <p>
 *      移相器
 *      应用场景:不同的线程,都需要等待注册的线程到达后执行下一步逻辑.
 *      在不同的阶段有自己的退出逻辑,
 * </p>
 **/
public class TestPhaser {


    public static void main(String[] args){
        ArrayList<Guest> list = getGuests();
        for (int i = 0; i < 6; i++) {
            Guest guest = list.get(i);
            new Thread(()->{
                guest.meet();
                System.out.println(guest.name + " 撤了回家!");
            }).start();
        }
    }

    private static ArrayList<Guest> getGuests() {
        Phaser phaser = new MeetingPhaser(6);
        Guest g1 = new Guest("小明", phaser);
        Guest g2 = new Guest("小张", phaser);
        Guest g3 = new Guest("小红", phaser);
        Guest g4 = new Guest("小陈", phaser);
        Guest g5 = new Guest("总经理", phaser);
        Guest g6 = new Guest("秘书", phaser);
        return Lists.newArrayList(g1, g2, g3, g4, g5, g6);
    }

    static class MeetingPhaser extends Phaser{
        public MeetingPhaser(int parties) {
            super(parties);
        }
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("共有"+registeredParties+"人,到了,准备开会");
                    return false;
                case 1:
                    System.out.println("共有"+registeredParties+"人,发表了意见,开会结束,准备吃饭");
                    return false;
                case 2:
                    System.out.println("共有"+registeredParties+"人,吃完饭了,大家走了,剩下的人结账");
                    return false;
                case 3:
                    System.out.println("共有"+registeredParties+"人,结完账了,准备去酒店休息");
                    return false;
                case 4:
                    System.out.println("共有"+registeredParties+"人,酒店休息");
                    return true;
                default:
                    return true;
            }
        }
    }

    static class Guest {
        String name;
        Phaser phaser;
        public Guest(String name, Phaser phaser) {
            this.name = name;
            this.phaser = phaser;
        }

        boolean arrive(){
            randomSleep();
            phaser.arriveAndAwaitAdvance();
            return true;
        }

        boolean talk(){
            randomSleep();
            phaser.arriveAndAwaitAdvance();
            return true;
        }

        boolean eat(){
            if (name.equals("小红")){
                phaser.arriveAndDeregister();
                System.out.println("小红身体不舒服");
                return false;
            }
            randomSleep();
            phaser.arriveAndAwaitAdvance();
            return true;
        }

        boolean pay(){
            if (!name.equals("总经理") && !name.equals("秘书")){
                phaser.arriveAndDeregister();
                return false;
            }
            randomSleep();
            phaser.arriveAndAwaitAdvance();
            return true;
        }

        boolean hotel(){
            if (name.equals("秘书")){
                phaser.arriveAndDeregister();
                System.out.println("秘书要回家带孩子");
                return false;
            }
            randomSleep();
            phaser.arriveAndAwaitAdvance();
            return true;
        }

        void meet(){
            if (!arrive()) return;
            if (!talk()) return;
            if (!eat()) return;
            if (!pay()) return;
            if (!hotel()) return;
        }

        private void randomSleep() {
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
