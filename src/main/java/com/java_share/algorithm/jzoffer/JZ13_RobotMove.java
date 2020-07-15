package com.java_share.algorithm.jzoffer;

/**
 * @author yz
 * @date 2020-07-15 13:39
 * <br>
 *      剑指 Offer 13. 机器人的运动范围
 *
 *  地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
 *  一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
 *  也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，
 *  因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *
 *  1 <= n,m <= 100
 *  0 <= k <= 20
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
 * </br>
 * <br>
 *
 * </br>
 **/
public class JZ13_RobotMove {

    public static void main(String[] args){
        System.out.println(new Solution().movingCount(2, 3, 1));
        System.out.println(new Solution().movingCount(3, 1, 0));
        System.out.println(new Solution().movingCount(10, 10, 10));
    }

    static class Solution {

        public int movingCount(int y, int x, int k) {
            boolean[][] passed = new boolean[y][x];
            return dfs(y, x, passed, 0, 0, k);
        }

        private int dfs(int yLen, int xLen, boolean[][] passed,
                        int y, int x, int k) {
            // 终结条件,返回0
            if(y >= yLen || x >= xLen || sum(y, x) > k || passed[y][x])
                return 0;
            passed[y][x] = true;// 设置走过
            // 机器人从[0,0]开始,其实只能往x或y方向走一步, 累加
            return 1 + dfs(yLen, xLen, passed, y+1, x, k) + dfs(yLen, xLen, passed, y, x+1, k);
        }

        // xy与k的函数关系
        int sum(int y, int x){
            return y%10 + y/10 + x%10 + x/10;
        }
    }
}
