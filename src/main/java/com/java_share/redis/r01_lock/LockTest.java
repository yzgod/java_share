package com.java_share.redis.r01_lock;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author yz
 * @date 2020-07-27 11:16
 * <p>
 *     redis分布式锁场景
 *     应用测试
 * </p>
 **/
public class LockTest {

    static int threadNum = 10;
    public static void main(String[] args){
        JedisPool pool = createPool();
        List<Thread> threads = new ArrayList<>();
        String key = UUID.randomUUID().toString();
        for (int i = 0; i < threadNum; i++) {
            Thread t = new Thread(() -> {
                Jedis jedis = pool.getResource();
                try {
                    Long res = jedis.setnx(key, "1");
                    if (res == 1) System.out.println(Thread.currentThread().getName() + ": 获取到了锁!");
                    else System.out.println(Thread.currentThread().getName() + ": 获取锁失败!");
                }finally {
                    jedis.close();
                }
            });
            threads.add(t);
        }
        for (Thread th : threads) {
            th.start();
        }
    }

    private static JedisPool createPool() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(threadNum);
        return new JedisPool(config, "10.211.55.20", 6379);
    }

}
