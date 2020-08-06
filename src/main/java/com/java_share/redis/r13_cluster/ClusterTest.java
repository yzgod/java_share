package com.java_share.redis.r13_cluster;

import com.netflix.hystrix.util.HystrixRollingNumber;
import com.netflix.hystrix.util.HystrixRollingNumberEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author yz
 * @date 2020-08-05 18:52
 * <p>
 *
 *     集群测试
 * </p>
 **/
public class ClusterTest {

    private static StringRedisTemplate redisTemplate;


    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(RedisConfig.class);
        redisTemplate = context.getBean(StringRedisTemplate.class);
        test();
    }

    static HystrixRollingNumber rollingNumber = new HystrixRollingNumber(1000, 100);
    private static void test() {
        new Thread(()->{
            while (true){
                System.out.println("QPS:" + rollingNumber.getRollingSum(HystrixRollingNumberEvent.SUCCESS));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        for (int i = 0; i < 60; i++) {
            new Thread(()->{
                while (true){
                    redisTemplate.opsForValue().get("k1");
                    rollingNumber.add(HystrixRollingNumberEvent.SUCCESS, 1);
                    redisTemplate.opsForValue().get("k3");
                    rollingNumber.add(HystrixRollingNumberEvent.SUCCESS, 1);
                    redisTemplate.opsForValue().get("k4");
                    rollingNumber.add(HystrixRollingNumberEvent.SUCCESS, 1);
                }
            }).start();
        }
    }


    @Configuration
    static class RedisConfig{
        @Bean
        public RedisConnectionFactory redisConnectionFactory() {
            RedisClusterConfiguration conf = new RedisClusterConfiguration();
            conf.clusterNode("10.211.55.20", 20001);
            conf.clusterNode("10.211.55.20", 20002);
            conf.clusterNode("10.211.55.21", 20001);
            return new LettuceConnectionFactory(conf);
        }

        @Bean
        public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory){
            return new StringRedisTemplate(redisConnectionFactory);
        }

    }
}
