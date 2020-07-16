package com.java_share.algorithm.jzoffer;

/**
 * @author yz
 * @date 2020-07-15 13:39
 * <br>
 *      剑指 Offer 27. 二叉树的镜像
 * </br>
 * <br>
 * </br>
 **/
public class JZ27_MirrorTree {

    public static void main(String[] args){

    }

    static class Solution {

        public TreeNode mirrorTree(TreeNode root) {
            if(root == null) return null;//终结条件
            TreeNode node = new TreeNode(root.val);// 定义新node
            // 左右节点不为空,分解子问题,分别镜像左右子树
            if(root.left != null) node.right=mirrorTree(root.left);
            if(root.right!= null) node.left =mirrorTree(root.right);
            return node;//返回新节点
        }

    }
}
