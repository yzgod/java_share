package com.java_share.algorithm.jzoffer;

import java.util.Arrays;

/**
 * @author yz
 * @date 2020-07-15 13:39
 * <br>
 *      剑指 Offer 29. 顺时针打印矩阵
 *      输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 * </br>
 * <br>
 * </br>
 **/
public class JZ29_SpiralOrder_Matrix {

    public static void main(String[] args){
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9},
        };
        int[] order = new Solution().spiralOrder(matrix);
        System.out.println(Arrays.toString(order));
    }

    static class Solution {

        public int[] spiralOrder(int[][] matrix) {
            int yLen = matrix.length, xLen = yLen==0?0:matrix[0].length;
            if(yLen==0 || xLen == 0) return new int[0];
            boolean[][] passed = new boolean[yLen][xLen];
            int[] arr = new int[yLen * xLen];
            int y = 0, x =0;
            int[][] ds = {{0,1},{1,0},{0,-1},{-1,0}};//{y,x}方向
            int k = 0;
            for (int i = 0; i < arr.length; i++) {
                arr[i] = matrix[y][x];
                passed[y][x] = true;
                int yNext = y + ds[k][0], xNext=x + ds[k][1];
                if(yNext < 0 || yNext>=yLen || xNext < 0 || xNext>=xLen || passed[yNext][xNext]){
                    k = (++k) % 4;
                }
                y += ds[k][0];
                x += ds[k][1];
            }
            return arr;
        }

    }
}
