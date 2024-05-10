package leetcode.contest.biweekly.bi_100.bi_120.a;

import code_generation.utils.IoUtil;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-120/problems/count-the-number-of-incremovable-subarrays-i
 * @title: 统计移除递增子数组的数目I
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "incremovableSubarrayCount", "A.txt");
    }


    public int incremovableSubarrayCount(int[] nums) {
        int n = nums.length;
        int cnt = 0;
        int k = 0;
        for (; k < n; k++) {
            if (k > 0 && nums[k] <= nums[k - 1]) break;
        }
        if (k == n ) {
            return (n * (n + 1)) / 2;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                cnt += calc(nums, i, j);
            }
        }
        return cnt;
    }


    /**
     * 计算区间内容是否严格递增 统计数字个数
     * 删除区间 r - l
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static int calc(int[] arr, int l, int r) {
        int n = arr.length;
        int pre = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (i >= l && i <= r) continue;
            if (arr[i] <= pre) {
                return 0;
            }
            pre = arr[i];
        }
        return n - (r - l + 1);

    }


}