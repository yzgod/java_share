package com.java_share.net;

import com.netflix.hystrix.util.HystrixRollingNumber;
import com.netflix.hystrix.util.HystrixRollingNumberEvent;
import okhttp3.*;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author yz
 * @date 2020-07-22 21:13
 * <p>
 *     模拟并发请求LVS服务
 * </p>
 **/
public class LvsTest {

    static int threadNum = 1;
    static AtomicLong count = new AtomicLong(0);

    public static void main(String[] args){
        for (int i = 0; i < threadNum; i++) {
            new Thread(()->{
                OkHttpClient client = new OkHttpClient.Builder()
                        .build();
                while (true){
                    reqLvs(client);
                }
            }).start();
        }
    }

    static void reqLvs(OkHttpClient client){
        Request request = new Request.Builder()
                .url("http://10.211.55.100/lvs")
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            String data = response.body().string();
            System.out.println(data +"-----"+ count.getAndIncrement());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
