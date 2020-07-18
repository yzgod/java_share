package com.java_share.algorithm.jzoffer;

/**
 * @author yz
 * @date 2020-07-18 08:36
 * <p>
 *      剑指 Offer 43. 1～n整数中1出现的次数
 * </p>
 **/
public class JZ43_CountDigitOne {

    public static void main(String[] args){
        System.out.println(new Solution().countDigitOne(100));
    }

    static class Solution {

        public int countDigitOne(int n) {
            // di-高位倍数,hi高位值,cur当前位,lo低位,sum总和
            int di = 1, hi = n / 10,
                    cur = n % 10, lo = 0, sum = 0;
            while(hi != 0 || cur != 0) {
                if(cur == 0) sum += hi * di;//cur=0, 1个数=hi*di
                else if(cur == 1) sum += hi * di + lo + 1;//cur=1, 1个数=hi*di+lo+1
                else sum += (hi + 1) * di;//cur>1, 1个数=(hi+1)*di
                lo += cur * di; // lo
                cur = hi % 10;// cur
                hi /= 10;// hi
                di *= 10;// di
            }
            return sum;
        }
    }

}
