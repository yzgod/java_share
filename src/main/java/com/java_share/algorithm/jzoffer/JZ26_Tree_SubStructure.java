package com.java_share.algorithm.jzoffer;

/**
 * @author yz
 * @date 2020-07-16 17:33
 * <br>
 *     剑指 Offer 26. 树的子结构
 *
 *     输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *     B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 * </br>
 **/
public class JZ26_Tree_SubStructure {

    public static void main(String[] args){
        TreeNode tree1 = TreeNode.deserialize("[3, 4, 5, 1, 2,null,null,null,null,null,null]");
        TreeNode tree2 = TreeNode.deserialize("[4, 1, null,null,null]");
        System.out.println(new Solution().isSubStructure(tree1, tree2));
        TreeNode tree3 = TreeNode.deserialize("[4, null, 2,null,null]");
        System.out.println(new Solution().isSubStructure(tree1, tree3));
        TreeNode tree4 = TreeNode.deserialize("[3, 4, 5,null,null,null,null]");
        System.out.println(new Solution().isSubStructure(tree1, tree4));
    }

    static class Solution {

        public boolean isSubStructure(TreeNode tree, TreeNode subTree) {
            // null不是任何树的子树
            if(tree == null || subTree==null) return false;
            // 头节点不是,那么用左右节点分别作为头节点递归
            return containsFromHead(tree, subTree)
                    || isSubStructure(tree.left, subTree)
                    || isSubStructure(tree.right, subTree);
        }

        // 从头节点 遍历是否包含子树
        private boolean containsFromHead(TreeNode tree, TreeNode subTree) {
            if (subTree == null) return true;
            if (tree == null || tree.val!=subTree.val) return false;
            return containsFromHead(tree.left, subTree.left)
                    && containsFromHead(tree.right, subTree.right);
        }
    }
}
