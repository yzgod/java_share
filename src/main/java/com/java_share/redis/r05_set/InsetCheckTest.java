package com.java_share.redis.r05_set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author yz
 * @date 2020-07-30 02:09
 * <p></p>
 **/
public class InsetCheckTest {

    public static void main(String[] args){
        JedisPool pool = createPool();
        String[] values = new String[512];
        for (int i = 0; i < 512; i++) values[i] = Integer.toString(i+1);
        Jedis jedis = pool.getResource();
        try {
            jedis.sadd("s1", values);
        }finally {
            jedis.close();
        }
    }

    private static JedisPool createPool() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(10);
        return new JedisPool(config, "10.211.55.20", 6379);
    }
}
