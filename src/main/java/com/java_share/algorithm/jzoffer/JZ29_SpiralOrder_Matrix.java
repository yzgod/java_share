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
            boolean[][] passed = new boolean[yLen][xLen];//定义记忆集
            int[] arr = new int[yLen * xLen];//记录走过的路径即为结果
            int y = 0, x = 0;//假设左上角坐标为(0,0)
            int[][] ds = {{0,1},{1,0},{0,-1},{-1,0}};//{y,x}方向增量
            int k = 0;//方向增量数组的索引
            for (int i = 0; i < arr.length; i++) {
                arr[i] = matrix[y][x];
                passed[y][x] = true;
                // 探测下一个节点是否走过,如走过需要改变方向
                int yNext = y + ds[k][0], xNext=x + ds[k][1];
                if(yNext < 0 || yNext>=yLen || xNext < 0 || xNext>=xLen
                        || passed[yNext][xNext]){
                    k = (++k) % 4;//改变方向的规律, 右->下->左->上 4个方向%4
                }
                y += ds[k][0];
                x += ds[k][1];
            }
            return arr;
        }

    }
}
