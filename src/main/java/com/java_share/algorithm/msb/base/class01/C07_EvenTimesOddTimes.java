package com.java_share.algorithm.msb.base.class01;

import java.util.Arrays;

/**
 * @author yz
 * @date 2020-08-10 23:04
 * <p>
 *      抑或 ^
 * 		b ^ b = 0
 * 		a ^ 0 = a
 * 		a ^ b ^ b = a
 *
 * 		非运算 ~
 * 		a & (~a + 1) 等于a的最右bit为1的value值
 * </p>
 **/
public class C07_EvenTimesOddTimes {

	public static void main(String[] args){
		int[] nums =  {1, 1, 2, 3, 2, 5, 3};
		System.out.println(oddTimes1(nums));

		int[] nums2 = {1, 1, 2, 3, 2, 5, 3, 8, 7,7,9,9,111,111};
		System.out.println(Arrays.toString(oddTimes2(nums2)));

		System.out.println(40 & (~40 + 1));
	}

	// 数组中只有一个数出现1次
	private static int oddTimes1(int[] nums) {
		int res = 0;
		for (int num : nums)
			res ^= num;
		return res;
	}

	// 数组中有两个数出现1次
	// 两个数抑或^
	// 1 1 1 0 0 0
	// 1 0 1 1 0 0
	// 1 0 1 0 0 0
	//     ^ 第一个相同的1位
	private static int[] oddTimes2(int[] nums) {
		int tmp = 0;
		for (int num : nums)
			tmp ^= num;
		int bit = tmp & (~tmp + 1); // 找到数的最右bit为1的value
		int r1 = 0, r2 = 0;
		for (int num : nums) {
			if ((num & bit) == 0) { //数据分组,r1,r2分开
				r1 ^= num;
			}else {
				r2 ^= num;
			}
		}
		return new int[]{r1, r2};
	}


}
