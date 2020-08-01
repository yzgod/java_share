package com.java_share.redis.r05_set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * @author yz
 * @date 2020-07-30 02:34
 * <p>
 *     redis set抽奖测试
 * </p>
 **/
public class DrawTest {

    static int nums = 100;//公司年会100人参加, 二三等奖互斥, 一等奖都有机会抽取
    static int first = 2;//一等奖2人
    static int sec = 10;//二等奖10人
    static int thi = 30;//三等奖30人
    static String key = "s2";
    public static void main(String[] args){
        JedisPool pool = createPool();
        String[] values = new String[nums];
        for (int i = 0; i < nums; i++) values[i] = Integer.toString(i+1);
        Jedis jedis = pool.getResource();
        jedis.sadd(key, values);
        List<String> p3 = jedis.srandmember(key, thi);//先抽三等奖
        System.out.println("三等奖名单:" + p3);
        jedis.srem(key, p3.toArray(new String[p3.size()]));// 移除三等奖用户
        List<String> p2 = jedis.srandmember(key, sec);// 抽二等奖
        System.out.println("二等奖名单:" + p2);
        for (String s : p2) if (p3.contains(s)) System.err.println("二三等奖重复了:" + s);
        jedis.sadd(key, values);// 恢复名单
        List<String> p1 = jedis.srandmember(key, first);// 抽一等奖
        System.out.println("一等奖名单:" + p1);
    }

    private static JedisPool createPool() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(10);
        return new JedisPool(config, "10.211.55.20", 6379);
    }
}
