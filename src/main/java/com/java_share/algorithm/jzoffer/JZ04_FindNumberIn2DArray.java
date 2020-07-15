package com.java_share.algorithm.jzoffer;

import java.util.HashSet;

/**
 * @author yz
 * @date 2020-07-14 10:15
 * <br>
 *      剑指 Offer 04. 二维数组中的查找
 *          在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，
 *          每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的
 *          一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 *          示例:
 *              现有矩阵 matrix 如下：
 *                 [
 *                   [1,   4,  7, 11, 15],
 *                   [2,   5,  8, 12, 19],
 *                   [3,   6,  9, 16, 22],
 *                   [10, 13, 14, 17, 24],
 *                   [18, 21, 23, 26, 30]
 *                 ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
 * </br>
 * <br>
 *      二维矩阵横竖x,y方向单调递增
 *      有个很巧妙的方法,时间复杂度O(x+y),空间复杂度O(1)
 *      定义指针,从右上角开始,往左下角,走z字形路线
 * </br>
 **/
public class JZ04_FindNumberIn2DArray {

    public static void main(String[] args){
        int[][] matrix = {
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        System.out.println(new Solution().findNumberIn2DArray(matrix, 9));
        System.out.println(new Solution().findNumberIn2DArray(matrix, 20));
    }

    static class Solution {

        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            if(matrix == null)
                return false;
            int y = matrix.length;
            if(y == 0)
                return false;
            int x = matrix[0].length;
            if(x == 0)
                return false;

            int xt = x -1;//横向指针
            int yt = 0;//纵向指针
            while (true){
                int cur = matrix[yt][xt];
                // 查询成功终结条件
                if(cur == target)
                    return true;
                if(cur > target){
                    xt --;
                }else {
                    yt ++;
                }
                // 未查询到终结条件
                if(xt < 0 || yt == y)
                    return false;
            }
        }
    }

}
