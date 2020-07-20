package com.java_share.base.l01_cpu_disorder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author yz
 * @date 2020-07-20 22:34
 * <p>
 *  cpu指令重排序,代码证明
 * </p>
 **/
public class CpuDisorder {

    static int x = 0, y = 0;
    static int a = 0, b = 0;

    static class Task implements Callable<Object>{
        @Override
        public Object call() throws Exception {
            a = 1;
            x = b;
            return null;
        }
    }
    static class Task2 extends Task {
        @Override
        public Object call() throws Exception {
            b = 1;
            y = a;
            return null;
        }
    }

    static List<Task> tasks = new ArrayList<Task>(){
        {
            add(new Task());
            add(new Task2());
        }
    };

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 线程池不用新建线程,可以更快速的模拟出效果
        ExecutorService service = Executors.newFixedThreadPool(2);
        long count = 0;
        while (true) {
            count ++;
            //重置为0
            x = 0;y = 0;a = 0;b = 0;
            service.invokeAll(tasks);//invokeAll会等待两个task跑完
            // 若没有指令重排序,永远也不可能出现x==0 && y==0
            if(x == 0 && y == 0){
                System.out.println("第"+count+"次,出现了乱序!");
                break;
            }
        }
        service.shutdown();
    }

}
