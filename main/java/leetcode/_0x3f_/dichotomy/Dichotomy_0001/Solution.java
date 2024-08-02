package leetcode._0x3f_.dichotomy.Dichotomy_0001;

import code_generation.utils.IoUtil;

/**
 *
 *
 * 410. 分割数组的最大值
 *
 * 给定一个非负整数数组 nums 和一个整数k ，你需要将这个数组分成k个非空的连续子数组。设计一个算法使得这k个子数组各自和的最大值最小。
 *
 * 示例 1：输入：nums = [7,2,5,10,8], k = 2输出：18解释：一共有四种方法将 nums 分割为 2 个子数组。 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 *
 * 示例 2：输入：nums = [1,2,3,4,5], k = 2输出：9
 *
 * 示例 3：输入：nums = [1,4,4], k = 3输出：4
 *
 * 提示：
 *
 *
 * 	1 <= nums.length <= 1000
 * 	0 <= nums[i] <= 106
 * 	1 <= k <= min(50, nums.length)
 *
 *
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/split-array-largest-sum
 * @title: split-array-largest-sum
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "splitArray", "in.txt");
    }


    public int splitArray(int[] nums, int k) {
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        long r = sum, l = 0;
        long ans = 0;
        while (l <= r) {
            long mid = l + ((r - l) >> 1);
            if (check(nums, mid, k)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int) ans;
    }


    public static boolean check(int[] nums, long limit, int k) {
        int cnt = 1;
        long sum = 0;
        for (int num : nums) {
            if (num > limit) { // 不可能
                return false;
            }
            if (sum + num > limit) {
                sum = num;
                cnt++;
            } else {
                sum += num;
            }
        }

        return cnt <= k;
    }

}