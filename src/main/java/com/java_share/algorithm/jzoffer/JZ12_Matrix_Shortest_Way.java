package com.java_share.algorithm.jzoffer;

/**
 * @author yz
 * @date 2020-07-15 13:39
 * <br>
 *      剑指 Offer 12. 矩阵中的路径
 *      请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 *      路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。
 *      如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
 *      例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
 *
 * [["a","b","c","e"],
 * ["s","f","c","s"],
 * ["a","d","e","e"]]
 *
 * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行
 * 第二个格子之后，路径不能再次进入这个格子。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof
 * </br>
 * <br>
 *
 * </br>
 **/
public class JZ12_Matrix_Shortest_Way {

    public static void main(String[] args){
        char[][] chars = {
                {'a','b','c','e'},
                {'s','f','e','s'},
                {'a','d','e','e'},
        };
        System.out.println(new Solution().exist(chars, "abceseeefs"));
    }

    static class Solution {

        public boolean exist(char[][] board, String word) {
            char[] words = word.toCharArray();
            for (int y = 0; y < board.length; y++) {
                for (int x = 0; x < board[0].length; x++) {
                    if(board[y][x] == words[0]
                            && dfs(board, words, null, x, y, 0))
                        return true;
                }
            }
            return false;
        }

        // x-横坐标, y-纵坐标, k为words数组指针
        private boolean dfs(char[][] board, char[] words,boolean[][] passed,
                            int x, int y, int k) {
            if(passed == null) passed = new boolean[board.length][board[0].length];
            // 存在的终结条件
            if(k > words.length-1) return true;
            // 不存在的终结条件
            if(y<0 || x<0 || y > board.length-1 || x>board[0].length -1
                    || board[y][x] != words[k++]
                    || passed[y][x])
                return false;
            passed[y][x] = true;// 记录走过的路径
            boolean flag =  dfs(board, words, passed, x+1, y, k)
                    || dfs(board, words, passed, x-1, y, k)
                    || dfs(board, words, passed, x, y-1, k)
                    || dfs(board, words, passed, x, y+1, k);
            if(!flag) passed[y][x] = false;//清理回溯状态
            return flag;
        }
    }
}
