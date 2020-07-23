package com.java_share.net.lvs;

import com.netflix.hystrix.util.HystrixRollingNumber;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.atomic.LongAdder;

import static com.netflix.hystrix.util.HystrixRollingNumberEvent.SUCCESS;

/**
 * @author yz
 * @date 2020-07-22 21:13
 * <p>
 *     模拟并发请求LVS服务
 * </p>
 **/
public class LvsTest {

    static int threadNum = 40;

    public static void main(String[] args){
        LongAdder count = new LongAdder();
        HystrixRollingNumber ip_20 = new HystrixRollingNumber(1000, 100);
        HystrixRollingNumber ip_21 = new HystrixRollingNumber(1000, 100);
        for (int i = 0; i < threadNum; i++) {
            new Thread(()->{
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://10.211.55.100/lvs").build();
                while (true){
                    try {
                        Response response = client.newCall(request).execute();
                        count.increment();
                        if (response.body().string().endsWith("20")) ip_20.add(SUCCESS, 1);
                        else ip_21.add(SUCCESS, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        check(count, ip_20, ip_21);
    }

    private static void check(LongAdder count, HystrixRollingNumber ip_20, HystrixRollingNumber ip_21) {
        new Thread(()->{
            while (true){
                try {
                    Thread.sleep(4000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("总请求量:" + count.sum());
                System.out.println("55.20单机QPS:"+ip_20.getRollingSum(SUCCESS));
                System.out.println("55.21单机QPS:"+ip_21.getRollingSum(SUCCESS));
                System.out.println("===================================");
            }
        }).start();
    }
}
