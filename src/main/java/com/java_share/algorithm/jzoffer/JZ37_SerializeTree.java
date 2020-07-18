package com.java_share.algorithm.jzoffer;

import java.util.LinkedList;

/**
 * @author yz
 * @date 2020-07-17 09:37
 * <br>
 *     剑指 Offer 37. 序列化二叉树
 * </br>
 * <p>
 *     层序遍历
 * </p>
 **/
public class JZ37_SerializeTree {

    public static void main(String[] args){
        Codec codec = new Codec();
        TreeNode node = codec.deserialize("[1,2,3,null,4,null,5,null,null,7,8,null,null,null,null]");
        System.out.println(codec.serialize(node));
    }


    private static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if(root == null) return "[]";
            StringBuilder sb = new StringBuilder("[");
            LinkedList<TreeNode> list = new LinkedList<>();//定义队列
            list.add(root);
            while(!list.isEmpty()) {
                TreeNode node = list.pollFirst();
                if (node == null) sb.append("null,");//null也要记录,不然反序列化不正确
                else {
                    sb.append(node.val + ",");
                    list.add(node.left);//记录子树
                    list.add(node.right);
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if(data.equals("[]")) return null;
            data = data.replaceAll(" ", "");
            String[] values = data.substring(1, data.length() - 1).split(",");
            TreeNode root = new TreeNode(Integer.parseInt(values[0]));
            LinkedList<TreeNode> list = new LinkedList<>();//定义队列
            list.add(root);
            int k = 1; //从数组索引1开始
            while(!list.isEmpty()) {
                TreeNode node = list.pollFirst();
                if(!values[k].equals("null")) {
                    node.left = new TreeNode(Integer.parseInt(values[k]));//建立关系
                    list.add(node.left);
                }
                k++;
                if(!values[k].equals("null")) {
                    node.right = new TreeNode(Integer.parseInt(values[k]));
                    list.add(node.right);
                }
                k++;
            }
            return root;
        }
    }
}
