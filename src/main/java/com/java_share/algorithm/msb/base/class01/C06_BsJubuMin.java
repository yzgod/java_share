package com.java_share.algorithm.msb.base.class01;

/**
 * @author yz
 * @date 2020-08-10 23:04
 * <p>
 *     4. 局部最小值问题, 找出一个局部最小值
 *     无序数组, 相邻不等.
 *     0, 1         arr[0]   < arr[1]           0局部最小
 *     n-2, n-1     arr[n-2] > arr[n-1]         n-1局部最小 (n为nums.length)
 *     x-1,x,x+1    arr[x-1] > arr[x] <arr[x+1] x局部最小
 *
 *     画图可以发现(最坏情况,左边下降趋势,右边上升趋势),所以必然存在局部最小的数
 * </p>
 **/
public class C06_BsJubuMin {

    public static void main(String[] args){
        int[] nums = new int[]{6,5,4,3,4,5,6,7,8};
        System.out.println(jubuMin(nums));

        int[] nums2 = new int[]{6,5};
        System.out.println(jubuMin(nums2));

        int[] nums3 = new int[]{5,6};
        System.out.println(jubuMin(nums3));

        int[] nums4 = new int[]{7,8,5};
        System.out.println(jubuMin(nums4));
    }


    public static int jubuMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return nums[0];
        int l = 0, r = nums.length - 1;
        if (nums[0] < nums[1]) return nums[0];
        if (nums[r - 1] > nums[r]) return nums[r];

        while (l <= r) {
            int mid = (l + r)/2;
            if (nums[mid] < nums[mid+1] && nums[mid-1] > nums[mid]) {
                return nums[mid];
            }else if (nums[mid] < nums[mid+1]) {
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }
        return -1;
    }

}
