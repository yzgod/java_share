package com.java_share.redis.r07_max_memory;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

/**
 * @author yz
 * @date 2020-07-30 02:34
 * <p>
 *     redis 管道测试
 * </p>
 **/
public class MaxMemoryTest {

    public static void main(String[] args){
        Jedis jedis = createPool().getResource();
        String kbStr = get1KbStr();
        int i = 1;
        while (true){
            jedis.setex("k"+i, 3600,kbStr);
            System.out.println(i++);
        }
    }

    private static String get1KbStr(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1024; i++) sb.append('A');
        return sb.toString();
    }

    private static JedisPool createPool() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(10);
        return new JedisPool(config, "10.211.55.20", 6379);
    }
}
