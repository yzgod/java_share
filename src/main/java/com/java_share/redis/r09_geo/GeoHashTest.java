package com.java_share.redis.r09_geo;

import java.nio.charset.Charset;

/**
 * @author yz
 * @date 2020-08-02 00:43
 * <p>
 *     手撸geo算法,
 *     以下算法效率肯定不高,只是阐述实现步骤,方便理解
 * </p>
 **/
public class GeoHashTest {

    static char[] char_32 = new char[]{
            '0','1','2','3','4','5','6','7','8','9',
            'b','c','d','e','f','g','h','j','k','m',
            'n','p','q','r','s','t','u','v','w','x',
            'y','z'
    };// geo hash码表

    public static void main(String[] args){
        // 第一步计算经纬度bit位
        String xBitStr = getBitStr(104.088888, -180, 180);
        String yBitStr = getBitStr( 30.518888, -90, 90);
        System.out.println("经度二进制:"+xBitStr);
        System.out.println("纬度二进制:"+yBitStr);
        // 第二步合并经纬度
        String merge = merge(xBitStr, yBitStr);
        System.out.println("经纬度合并二进制:"+merge);
        // 第三步计算GeoHash值
        String geoHash = toGeoHash(merge);
        System.out.println("计算GeoHash值:"+geoHash);
    }

    private static String toGeoHash(String merge) {
        char[] chars = merge.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0, k = 0; i < chars.length; i++) {
            int x = i % 5;
            if (chars[i] == '1') k += 1<<(4 - x);
            if (x == 4) {
                sb.append(char_32[k]);
                k = 0;
            }
        }
        return sb.append(0).toString();// redis算出来貌似后面加了0
    }

    private static String merge(String xBitStr, String yBitStr) {
        if (xBitStr.length() != yBitStr.length()) throw new RuntimeException("error");
        char[] xs = xBitStr.toCharArray();
        char[] ys = yBitStr.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < xs.length; i++) {
            sb.append(xs[i]).append(ys[i]);
        }
        return sb.toString();
    }


    private static String getBitStr(double num, double min, double max) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            double mid = (max + min)/2;
            if (num > mid) {
                min = mid;
                sb.append(1);
            }else {
                max = mid;
                sb.append(0);
            }
            if (sb.length() == 25) break;//redis也是算的25次
        }
        return sb.toString();
    }

}
