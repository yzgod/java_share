package com.java_share.redis.r08_hll;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author yz
 * @date 2020-08-01 22:38
 * <p></p>
 **/
public class HyperLogLogTest {

    public static void main(String[] args){
        JedisPool pool = createPool();
        cal(pool, 1, 10000, "pf001");
        cal(pool, 5, 10000, "pf005");
        cal(pool, 10, 10000, "pf010");
        cal(pool, 20, 10000, "pf020");
        cal(pool, 50, 10000, "pf050");
        cal(pool, 1, 1000000, "pf1");
        cal(pool, 5, 1000000, "pf5");
        cal(pool, 10, 1000000, "pf10");
        cal(pool, 20, 1000000, "pf20");
        cal(pool, 50, 1000000, "pf50");
        cal(pool, 100, 1000000, "pf100");
    }

    private static void cal(JedisPool pool, int nums, int digit, String key) {
        Jedis jedis = pool.getResource();
        jedis.del(key);
        for (int i = 0; i < nums; i++) {
            String[] strs = new String[digit];
            for (int j = 0; j < digit; j++) strs[j] = Integer.toString(i * digit + j);
            jedis.pfadd(key, strs);
        }
        long pf = jedis.pfcount(key);
        long count = nums * digit;
        System.out.println("实际基数为:"+ count + ", pf计算基数为:"+ pf
                + ", 误差为:" + new BigDecimal((pf-count)*100)
                .divide(new BigDecimal(count), 4,RoundingMode.HALF_UP) + "%" );
        jedis.close();
    }


    private static JedisPool createPool() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(10);
        return new JedisPool(config, "10.211.55.20", 6379);
    }
}
