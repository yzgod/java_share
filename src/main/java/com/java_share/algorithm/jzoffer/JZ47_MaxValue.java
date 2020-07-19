package com.java_share.algorithm.jzoffer;

/**
 * @author yz
 * @date 2020-07-18 08:36
 * <p>
 *      剑指 Offer 47. 礼物的最大价值
 *
 *      在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
 *      你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、
 *      直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，
 *      请计算你最多能拿到多少价值的礼物？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof
 * </p>
 **/
public class JZ47_MaxValue {

    public static void main(String[] args){
        int[][] grid = {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        System.out.println(new Solution().maxValue(grid));
    }

    static class Solution {

        //动态规划,利用原来的grid做记忆
        public int maxValue(int[][] grid) {
            int yLen = grid.length, xLen = grid[0].length;
            for (int y = 0; y < yLen; y++) {
                for (int x = 0; x < xLen; x++) {// 先走右边
                    if (x == 0 && y == 0) continue;//起点
                    if (x == 0) grid[y][x] += grid[y-1][x];//往下走
                    else if (y == 0) grid[y][x] += grid[y][x-1];//往右走
                    //对比左边和上边相邻的值取max
                    else grid[y][x] += Math.max(grid[y][x-1], grid[y-1][x]);
                }
            }
            return grid[yLen-1][xLen-1];//终点
        }

    }

}
