package com.java_share.redis.r06_pipeline;

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
public class PipeLineTest {

    static int nums = 10000;// 执行命令数量
    public static void main(String[] args){
        JedisPool pool = createPool();
        long start = System.currentTimeMillis();
        pipeline(pool);
        long mid = System.currentTimeMillis();
        notPipeline(pool);
        long end = System.currentTimeMillis();
        System.out.println("pipeline运行耗时:"+(mid - start));
        System.out.println("非pipeline运行耗时:"+(end - mid));
    }

    private static void pipeline(JedisPool pool) {
        Jedis jedis = pool.getResource();
        Pipeline pipeline = jedis.pipelined();
        pipeline.set("p1", "0");
        for (int i = 0; i < nums; i++) {
            pipeline.incr("p1");
        }
        pipeline.sync();
        jedis.close();
    }
    private static void notPipeline(JedisPool pool) {
        for (int i = 0; i < nums; i++) {
            Jedis jedis = pool.getResource();
            try {
                if (i == 0) jedis.set("p2", "0");
                jedis.incr("p2");
            }finally {
                jedis.close();
            }
        }
    }

    private static JedisPool createPool() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(10);
        return new JedisPool(config, "10.211.55.20", 6379);
    }
}
