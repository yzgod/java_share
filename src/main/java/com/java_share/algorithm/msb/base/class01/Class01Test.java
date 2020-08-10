package com.java_share.algorithm.msb.base.class01;

/**
 * @author yz
 * @date 2020-08-10 21:54
 * <p></p>
 **/
public class Class01Test {

    public static void main(String[] args){
        binaryTest();
    }

    private static void binaryTest() {
        System.out.println(-1 >>> 1);// 带符号右移
        System.out.println(-1 >> 1); // 不带符号右移
    }
}
