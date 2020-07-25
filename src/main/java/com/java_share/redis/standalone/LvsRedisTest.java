package com.java_share.redis.standalone;

import com.netflix.hystrix.util.HystrixRollingNumber;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import static com.netflix.hystrix.util.HystrixRollingNumberEvent.SUCCESS;

/**
 * @author yz
 * @date 2020-07-25 16:49
 * <p>
 *     redis哨兵模式下,简单的基于Lvs的读写分离玩法
 * </p>
 **/
public class LvsRedisTest {

    static int threadNum = 20;
    public static void main(String[] args){
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(threadNum);
        JedisPool writePool = new JedisPool(config, "10.211.55.20", 6379);//主库
        JedisPool readPool  = new JedisPool(config, "10.211.55.100", 6379);//从库
        HystrixRollingNumber readNumber = new HystrixRollingNumber(
                1000, 100);
        readOp(readPool, readNumber);//从库读

        HystrixRollingNumber writeNumber = new HystrixRollingNumber(
                1000, 100);
        writeOp(writePool,writeNumber);//主库写

        check(readNumber, writeNumber);//检查QPS
    }

    private static void readOp(JedisPool readPool, HystrixRollingNumber rollingNumber) {
        for (int i = 0; i < threadNum; i++) {
            new Thread(()->{
                while (true){
                    Jedis jedis = readPool.getResource();
                    try {
                        jedis.get("y");
                        rollingNumber.add(SUCCESS, 1);
                    }finally {
                        jedis.close();
                    }
                }
            }).start();
        }
    }

    private static void writeOp(JedisPool writePool, HystrixRollingNumber rollingNumber) {
        for (int i = 0; i < threadNum; i++) {
            new Thread(()->{
                while (true){
                    Jedis jedis = writePool.getResource();
                    try {
                        jedis.incr("y");
                        rollingNumber.add(SUCCESS, 1);
                    }finally {
                        jedis.close();
                    }
                }
            }).start();
        }
    }

    private static void check(HystrixRollingNumber readNumber, HystrixRollingNumber writeNumber) {
        new Thread(()->{
            while (true){
                try {
                    Thread.sleep(1000L);
                    System.out.println("读取QPS:"+readNumber.getRollingSum(SUCCESS));
                    System.out.println("写入QPS:"+writeNumber.getRollingSum(SUCCESS));
                    System.out.println("===================================");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
