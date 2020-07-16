package com.java_share.algorithm.jzoffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yz
 * @date 2020-07-14 15:30
 * <br></br>
 **/
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    // 层序的反序列换,安照LeetCode实现
    public static TreeNode levelDeserialize(Integer... nums){
        if(nums==null) return null;
        TreeNode root = new TreeNode(nums[0]);
        if(nums.length == 1) return root;
        List<TreeNode> preList = new ArrayList<>();
        preList.add(root);
        int level = 2;
        while (true){
            int endIndex = (int) Math.pow(2, level) - 2;
            int startIndex = endIndex==0 ? 0:endIndex - ((int) Math.pow(2, level -1 ) - 1);
            if(endIndex > nums.length- 1){
                break;
            }
            List<TreeNode> levelList = new ArrayList<>(endIndex - startIndex + 1);
            for (int i = startIndex; i <= endIndex; i++) {
                if(nums[i] == null) levelList.add(null);
                else levelList.add(new TreeNode(nums[i]));
            }
            for (int i = 0; i < preList.size(); i++) {
                if(preList.get(i) == null) continue;
                preList.get(i).left = levelList.get(2*i);
                preList.get(i).right = levelList.get(2*i+1);
            }
            preList = levelList;
            level ++;
        }
        return root;
    }

}
