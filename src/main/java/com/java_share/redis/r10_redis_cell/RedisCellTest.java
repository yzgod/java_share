package com.java_share.redis.r10_redis_cell;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

/**
 * @author yz
 * @date 2020-08-02 02:54
 * <p></p>
 **/
public class RedisCellTest {

    public static void main(String[] args){
        String lua = getCellLua();
        JedisPool pool = createPool();
        Jedis jedis = pool.getResource();
        jedis.del("user123");
        for (int i = 0; i < 105; i++) {
            List<Long> eval = (List<Long>) jedis.eval(lua, 1,
                    "user123", "100", "30", "60");
            if (eval.get(0) == 0) System.out.println("请求成功");
            if (eval.get(0) == 1) System.out.println("请求失败");
        }
    }

    private static String getCellLua(){
        File file = new File(RedisCellTest.class.getResource("/cl.lua").getFile());
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String len;
            StringBuilder sb = new StringBuilder();
            while ((len=br.readLine())!=null){
                sb.append(len).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JedisPool createPool() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(10);
        return new JedisPool(config, "10.211.55.20", 6379);
    }
}
