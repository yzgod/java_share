package com.java_share.algorithm.msb.base.class02;

/**
 * @author yz
 * @date 2020-08-17 17:03
 * <p>
 *     递归,求数组的最大值
 * </p>
 **/
public class C08_GetMax {

    public static int getMax(int[] arr){
        if (arr==null || arr.length == 0) return -1;
        return dfs(arr, 0, arr.length -1);
    }

    private static int dfs(int[] arr, int l, int r) {
        if (l == r) return arr[l];
        int mid = (l + r) / 2;
        return Math.max(dfs(arr, l, mid), dfs(arr, mid + 1, r));
    }

    public static void main(String[] args){
        int[] arr = {6,7,111,555,4,-9,-999,4,7,0,7777};
        System.out.println(getMax(arr));
    }
}
