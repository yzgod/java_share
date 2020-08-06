package com.java_share.redis.r12_proxy;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author yz
 * @date 2020-08-07 00:06
 * <p>
 *     twemproxy 推特开源的redis代理
 * </p>
 **/
public class TwemProxyTest {

    public static void main(String[] args) throws InterruptedException {
        JedisPool pool = createPool();
        Jedis jedis = pool.getResource();
        for (int i = 0;; i++) {
            jedis.set("k" + i,""+i);
            Thread.sleep(500);
        }
    }



    private static JedisPool createPool() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(10);
        return new JedisPool(config, "10.211.55.20", 22121);
    }
}
