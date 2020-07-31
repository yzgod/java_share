package com.java_share.redis.r04_bit_count;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author yz
 * @date 2020-07-27 20:37
 * <p>
 *     应用场景: 最近活跃用户数统计
 * </p>
 **/
public class CountActivePeopleTest {

    static int user_nums = 100_0000;// 100万用户
    static int days = 3;// 最近3天
    static String key_prefix = "active_";
    public static void main(String[] args){
        JedisPool pool = createPool();
        prepareData(pool);// 构造最近三天的日活数据
        sumData(pool);// 统计最近3天的活跃用户
    }

    private static void bitOp(JedisPool pool,BitOP op,String key,String[] keys) {
        Jedis jedis = pool.getResource();
        try {
            jedis.bitop(op, key, keys);
        }finally {
            jedis.close();
        }
    }

    private static void prepareData(JedisPool pool) {
        CountDownLatch latch = new CountDownLatch(days);
        for (int i = 0; i < days; i++) {
            String date = format(DateUtils.addDays(new Date(), -i));
            String key = key_prefix + date;
            new Thread(()->{
                delKey(pool, key);//清除数据
                Jedis jedis = null;
                try {
                    jedis = pool.getResource();
                    for (int j = 0; j < 10_0000; j++) setBit(jedis, key);//准备数据
                }finally {
                    jedis.close();
                }
                Long count = bitCount(pool, key);
                System.out.println(key+" 日活:"+ count);
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sumData(JedisPool pool) {
        String[] keys = new String[days];
        for (int i = 0; i < days; i++)
            keys[i] = key_prefix + format(DateUtils.addDays(new Date(), -i));
        String key = key_prefix +"latest";
        delKey(pool, key);
        bitOp(pool, BitOP.OR, key, keys);
        Long count = bitCount(pool, key);
        System.out.println(key+" 最近三天活跃用户数:"+ count);

        String awaysKey = key_prefix +"aways";
        delKey(pool, awaysKey);
        bitOp(pool, BitOP.AND, awaysKey, keys);
        Long alwaysCount = bitCount(pool, awaysKey);
        System.out.println(awaysKey+" 连续三天活跃用户数:"+ alwaysCount);
    }

    private static Long bitCount(JedisPool pool, String key) {
        Jedis jedis = pool.getResource();
        try {
            return jedis.bitcount(key);
        }finally {
            jedis.close();
        }
    }

    private static void delKey(JedisPool pool, String key) {
        Jedis jedis = pool.getResource();
        try {
            jedis.del(key);
        }finally {
            jedis.close();
        }
    }

    private static void setBit(Jedis jedis, String key) {
        int randId = new Random().nextInt(user_nums);
        jedis.setbit(key, randId, true);
    }

    private static String format(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(date);
    }

    private static JedisPool createPool() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(10);
        return new JedisPool(config, "10.211.55.20", 6379);
    }


}
