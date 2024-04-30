package leetcode.contest.weekly.w_300.w_395.b;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-395/problems/find-the-integer-added-to-array-ii
 * @title: 找出与数组相加的整数II
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "minimumAddedInteger", "B.txt");
    }


    public int minimumAddedInteger(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = i+1; j < nums1.length; j++) {
                ans = Math.min(check(nums1, nums2, i, j), ans);
            }
        }
        return ans;
    }


    public static int check(int[] nums1, int[] nums2, int a, int b) {
        int diff = Integer.MAX_VALUE;
        for (int i = 0, j = 0; i < nums1.length; i++) {
            if (i == a || i == b) {
                continue;
            }
            if (diff == Integer.MAX_VALUE) {
                diff = nums2[j] - nums1[i];
            }
            if (nums1[i] + diff != nums2[j]) {
                return Integer.MAX_VALUE;
            }
            j++;
        }
        return diff;

    }


}