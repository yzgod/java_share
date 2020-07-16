package com.java_share.algorithm.jzoffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yz
 * @date 2020-07-16 14:20
 * <br>
 *     剑指 Offer 32 - III. 从上到下打印二叉树 III
 *
 *     请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，
 *     第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，
 *     其他行以此类推。
 * </br>
 **/
public class JZ32_3_TreeLevelOrder {

    public static void main(String[] args){
        TreeNode node = TreeNode.levelDeserialize(1, 2, 3, 4, null, null, 5);
//        TreeNode node = TreeNode.levelDeserialize(3,9,20,null,null,15,7);
        List<List<Integer>> list = new Solution().levelOrder(node);
        System.out.println(list);
    }

    static class Solution {

        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) return Collections.emptyList();
            List<List<Integer>> res = new ArrayList<>();
            // 定义cur/next 集合
            LinkedList<TreeNode> cur = new LinkedList<>(), next;
            cur.add(root);
            boolean order = false;// 定义第二层开始的弹出顺序, false为从右往左
            while (!cur.isEmpty()){
                //定义链表比较巧妙,不同的add顺序,数据顺序相反
                LinkedList<Integer> data = new LinkedList<>();
                next = new LinkedList<>();
                TreeNode node;
                while ((node = cur.pollFirst()) != null){
                    if (order) data.addFirst(node.val);//左往右,从顶部添加
                    else data.addLast(node.val);//右往左,从底部添加
                    if (node.left  != null) next.addLast(node.left);
                    if (node.right != null) next.addLast(node.right);
                }
                res.add(data);
                cur = next;
                order = !order;
            }
            return res;
        }

    }
}
