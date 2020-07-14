package com.java_share.algorithm.jzoffer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yz
 * @date 2020-07-14 14:26
 * <br>
 *     剑指 Offer 07. 重建二叉树
 *     输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。
 *     假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * </br>
 * <br>
 *
 * </br>
 **/
public class JZ07_BuildTree {

    public static void main(String[] args){
        int[] preorder = {3,9,8,10,20,15,7};
        int[] inorder = {8,9,10,3,15,20,7};
        TreeNode tree = new Solution().buildTree(preorder, inorder);
        System.out.println(tree);
    }

    static class Solution {

        public TreeNode buildTree(int[] pre, int[] in) {
            Map<Integer, Integer> inMap = new HashMap<>(pre.length);
            for (int i = 0; i < in.length; i++) {
                inMap.put(in[i], i);
            }
            return dfs(pre, inMap, 0, pre.length-1, 0);
        }

        // ins 中序遍历数组开始的位置
        private TreeNode dfs(int[] pre, Map<Integer, Integer> inMap,
                             int ps, int pe, int ins) {
            // 终结条件 ps-前序左指针start,pe-前序右指针end
            if(ps > pe){
                return null;
            }
            int mid = inMap.get(pre[ps]);// 中序mid index
            TreeNode head = new TreeNode(pre[ps]);// head节点
            if(ps != pe){//ps==pe重合直接返回head
                int len = mid - ins;//左端长度
                //左子集,范围 pre[ps+1, pe+len] in[ins,)
                head.left = dfs(pre, inMap,ps+1, ps + len, ins);
                //右子集,范围 pre[ps+len+1, pe] in[mid+1,)
                head.right = dfs(pre, inMap,ps+len+1, pe,mid + 1);
            }
            return head;
        }
    }
}
