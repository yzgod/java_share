package com.java_share.algorithm.leetcode.m08;

/**
 * @author yz
 * @date 2020-08-05 15:54
 * <p>
 *  1535. 找出数组游戏的赢家
 *
 *  https://leetcode-cn.com/problems/find-the-winner-of-an-array-game/
 * </p>
 **/
public class L1535_GetWinner {

    public static void main(String[] args){
        int[] arr = {2,1,3,5,4,6,7};
        System.out.println(new Solution().getWinner(arr, 2));
    }

    static class Solution {
        public int getWinner(int[] arr, int k) {
            int win = arr[0], cnt = 0;
            for (int i = 1; i < arr.length && cnt < k; i++) {
                if (win > arr[i]) cnt++;
                else {
                    win = arr[i];
                    cnt = 1;
                }
            }
            return win;
        }
    }
}
