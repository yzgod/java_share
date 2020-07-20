package com.java_share.algorithm.jzoffer;

/**
 * @author yz
 * @date 2020-07-20 17:52
 * <p>
 *
 * </p>
 **/
public class JZ51_ReversePairs {
    public static void main(String[] args){
        int[] nums = {1,3,5,2,1,7,4};
        System.out.println(new Solution().reversePairs(nums));
//        mergeSort(nums, 0, nums.length -1);
//        System.out.println(nums);
    }

    static class Solution{
        public int reversePairs(int[] nums) {
            return merge(nums, 0, nums.length - 1);
        }

        int merge(int[] arr, int start, int end) {
            if (start == end) return 0;
            int mid = (start + end) / 2;
            int count = merge(arr, start, mid) + merge(arr, mid + 1, end);

            int[] temp = new int[end - start + 1];
            int i = start, j = mid + 1, k = 0;
            while (i <= mid && j <= end) {
                count += arr[i] <= arr[j] ? j - (mid + 1) : 0;
                temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
            }
            while (i <= mid) {
                count += j - (mid + 1);
                temp[k++] = arr[i++];
            }
            while (j <= end)
                temp[k++] = arr[j++];
            System.arraycopy(temp, 0, arr, start, end - start + 1);
            return count;
        }
    }

    static void mergeSort(int[] arr, int l, int r){
        if (l == r) return;// 终结
        int mid = (l + r) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, mid + 1, r);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] lArr = new int[mid - l];
        int[] rArr = new int[r - mid + 1];
        for (int i = l; i < mid; i++) lArr[i - l] = arr[i];
        for (int i = mid; i <= r; i++) rArr[i - mid] = arr[i];
        int i = 0, j = 0, k = l;
        while (i <= lArr.length && j <= rArr.length) {
            if (i == lArr.length && j == rArr.length) break;
            else if (i == lArr.length) arr[k++] = rArr[j++];
            else if (j == rArr.length) arr[k++] = lArr[i++];
            else if (lArr[i] <= rArr[j]) arr[k++] = lArr[i++];
            else arr[k++] = rArr[j++];
        }
    }
}
