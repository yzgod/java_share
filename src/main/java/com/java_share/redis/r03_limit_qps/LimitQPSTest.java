package com.java_share.redis.r03_limit_qps;

import com.netflix.hystrix.util.HystrixRollingNumber;
import com.netflix.hystrix.util.HystrixRollingNumberEvent;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author yz
 * @date 2020-07-27 13:42
 * <p>
 *     以下代码为:限制某个接口流量
 *     一般还会有限制某个用户一段时间内请求数的场景,防止恶意请求
 * </p>
 **/
public class LimitQPSTest {

    static String lua = "local num = redis.call('incr', KEYS[1])\n" +
                        "if tonumber(num) == 1 then\n" +
                            "\tredis.call('pexpire', KEYS[1], ARGV[1])\n" +
                        "end\n" +
                        "return tonumber(num)";
    static int threadNum = 40;
    static int max_qps = 1000;
    public static void main(String[] args){
        LongAdder success = new LongAdder();
        LongAdder fails = new LongAdder();
        // rollingNumber的数据只是参考,有可能跨秒统计和redis过期不一致,大部分一致
        HystrixRollingNumber rollingNumber = new HystrixRollingNumber(1000, 100);
//        errorExec(success, fails, rollingNumber);// 错误的方式
        luaExec(success, fails, rollingNumber);// lua脚本的方式,解决上面代码的问题
        check(success, fails, rollingNumber);
    }

    private static void luaExec(LongAdder success, LongAdder fails, HystrixRollingNumber rollingNumber) {
        JedisPool pool = createPool();
        String key = "limit_qps";
        for (int i = 0; i < threadNum; i++) {
            new Thread(()->{
                while (true) {
                    Jedis jedis = pool.getResource();
                    try {
                        // 使用lua脚本, 让incr和pexpire成为原子操作
                        Long num = (Long) jedis.eval(lua, Collections.singletonList(key),
                                Collections.singletonList("1000"));
                        if (num > max_qps) {
                            fails.add(1);
                            rollingNumber.add(HystrixRollingNumberEvent.FAILURE, 1);
                        }
                        else {
                            success.add(1);
                            rollingNumber.add(HystrixRollingNumberEvent.SUCCESS, 1);
                        }
                    } finally {
                        jedis.close();
                    }
                }
            }).start();
        }
    }

    private static void errorExec(LongAdder success, LongAdder fails, HystrixRollingNumber rollingNumber) {
        JedisPool pool = createPool();
        String key = "limit_qps";
        for (int i = 0; i < threadNum; i++) {
            new Thread(()->{
                while (true) {
                    Jedis jedis = pool.getResource();
                    try {
                        // 这种方式incr和expire不是一个原子操作,会有问题
                        Long num = jedis.incr(key);
                        if (num == 1) jedis.pexpire(key, 1000);
                        if (num > max_qps) {
                            fails.add(1);
                            rollingNumber.add(HystrixRollingNumberEvent.FAILURE, 1);
                        } else {
                            success.add(1);
                            rollingNumber.add(HystrixRollingNumberEvent.SUCCESS, 1);
                        }
                    } finally {
                        jedis.close();
                    }
                }
            }).start();
        }
    }

    private static void check(LongAdder success, LongAdder fails, HystrixRollingNumber num) {
        new Thread(()->{
            while (true){
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("成功总请求次数:"+success.sum());
                System.out.println("失败总请求次数:"+fails.sum());
                System.out.println("成功参考QPS:" + num.getRollingSum(HystrixRollingNumberEvent.SUCCESS));
                System.out.println("失败参考QPS:" + num.getRollingSum(HystrixRollingNumberEvent.FAILURE));
                System.out.println("============================");
            }
        }).start();
    }

    private static JedisPool createPool() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(threadNum);
        return new JedisPool(config, "10.211.55.20", 6379);
    }

}
