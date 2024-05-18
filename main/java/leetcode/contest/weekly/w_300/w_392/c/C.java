package leetcode.contest.weekly.w_300.w_392.c;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 310^7. 使数组中位数等于 K 的最少操作数给你一个整数数组nums和一个 非负整数k。
 * 一次操作中，你可以选择任一元素加1或者减1。
 * 请你返回将 nums中位数变为 k所需要的 最少操作次数。
 * 一个数组的中位数指的是数组按非递减顺序排序后最中间的元素。如果数组长度为偶数，我们选择中间两个数的较大值为中位数。
 * <p>
 * 示例 1：
 * 输入：nums = [2,5,6,8,5], k = 4
 * 输出：2
 * 解释：我们将nums[1] 和nums[4]减 1得到[2, 4, 6, 8, 4]。现在数组的中位数等于k。
 * <p>
 * 示例 2：
 * 输入：nums = [2,5,6,8,5], k = 7
 * 输出：3
 * 解释：我们将nums[1]增加 1 两次，并且将nums[2]增加 1 一次，得到[2, 7, 7, 8, 5]。
 * <p>
 * 示例 3：
 * 输入：nums = [1,2,3,4,5,6], k = 4
 * 输出：0
 * 解释：数组中位数已经等于 k了。
 * <p>
 * 提示：
 * 1 <= nums.length <= 2 * 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= 10^9
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-392/problems/minimum-operations-to-make-median-of-array-equal-to-k
 * @title: 使数组中位数等于K的最少操作数
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class,"minOperationsToMakeMedianK","C.txt");
    }


    public long minOperationsToMakeMedianK(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int mid = n >> 1;
        long cnt = 0;
        long sum = 0;
        if (nums[mid] == k) {
            return 0;
        }

        if (nums[mid] > k) {
            while (mid >= 0 && nums[mid] > k) {
                sum += nums[mid];
                cnt++;
                mid--; // 向左边移动
            }
            return sum - cnt * k;
        }

        if (nums[mid] < k) {
            while (mid < n && nums[mid] < k) {
                sum += nums[mid];
                cnt++;
                mid++;// 向右移动
            }
            return cnt * k - sum;
        }
        return -1;

    }

  

}