package com.java_share.redis.r02_flash_sale;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author yz
 * @date 2020-07-27 13:24
 * <p>
 *     redis秒杀场景
 *     应用测试
 * </p>
 **/
public class FlashSaleTest {

    static int threadNum = 40;// 秒杀线程数
    static Integer stock = 5;// 库存数
    static String key = UUID.randomUUID().toString();// 商品key
    public static void main(String[] args){
        JedisPool pool = createPool();
        initStock(pool);//初始化库存
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadNum; i++) {
            Thread t = new Thread(() -> {
                Jedis jedis = pool.getResource();
                try {
                    Long num = jedis.decr(key);
                    if (num >= 0) System.out.println(Thread.currentThread().getName()+": 秒杀成功! 剩余库存 "+ num);
                    else System.out.println(Thread.currentThread().getName()+": 秒杀失败,商品售罄!");
                }finally {
                    jedis.close();
                }
            });
            threads.add(t);
        }
        for (Thread t : threads) t.start();
    }

    private static void initStock(JedisPool pool) {
        Jedis jedis = pool.getResource();
        try {
            jedis.set(key, stock.toString());
        }finally {
            jedis.close();
        }
    }

    private static JedisPool createPool() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(threadNum);
        return new JedisPool(config, "10.211.55.20", 6379);
    }
}
