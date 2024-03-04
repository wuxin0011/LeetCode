package leetcode.contest.weekly.w_300.w_387.a;

import leetcode.utils.IoUtil;

/**
 * @author: wuxin0011
 * @title :  将元素分配到两个数组中 I
 * @Description: 简单模拟下就ok
 * @url: https://leetcode.cn/contest/weekly-contest-387/problems/distribute-elements-into-two-arrays-i/
 */
public class A {
    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "resultArray", "in.txt");
    }

    public int[] resultArray(int[] nums) {
        int n = nums.length;
        if (n <= 2) return nums;
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        int n1 = 0;
        int n2 = 0;
        arr1[n1++] = nums[0];
        arr2[n2++] = nums[1];
        for (int i = 2; i < n; i++) {
            if (arr1[n1 - 1] > arr2[n2 - 1]) {
                arr1[n1++] = nums[i];
            } else {
                arr2[n2++] = nums[i];
            }
        }
        for (int i = 0, i1 = 0, i2 = 0; i < n; i++) {
            if (i1 < n1) {
                nums[i] = arr1[i1];
                i1++;
            } else {
                nums[i] = arr2[i2];
                i2++;
            }
        }
        return nums;

    }
}
