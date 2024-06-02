package leetcode.contest.weekly.w_400.w_400.d;

import code_generation.utils.IoUtil;

/**
 * 100315. 找到按位与最接近 K 的子数组
 * <p>
 * <p>
 * 给你一个数组nums和一个整数k。
 * 你需要找到nums的一个子数组，满足子数组中所有元素按位与运算AND的值与 k的 绝对差尽可能 小。换言之，你需要选择一个子数组nums[l..r]满足|k - (nums[l] AND nums[l + 1] ... AND nums[r])|最小。
 * 请你返回 最小的绝对差值。
 * 子数组是数组中连续的非空元素序列。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,4,5], k = 3
 * 输出：1
 * 解释：
 * 子数组nums[2..3] 的按位AND运算值为 4 ，得到最小差值|3 - 4| = 1。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,1,2], k = 2
 * 输出：0
 * 解释：
 * 子数组nums[1..1] 的按位AND运算值为 2 ，得到最小差值|2 - 2| = 0。
 * <p>
 * 示例 3：
 * 输入：nums = [1], k = 10
 * 输出：9
 * 解释：
 * 只有一个子数组，按位AND运算值为 1 ，得到最小差值|10 - 1| = 9。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= 10^9
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-400/problems/find-subarray-with-bitwise-and-closest-to-k
 * @title: 找到按位与最接近 K 的子数组
 */
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "minimumDifference", "D.txt");
    }


    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            ans = Math.min(ans, Math.abs(val - k));
            for (int j = i - 1; j >= 0; j--) {
                if ((val & nums[j]) == nums[j]) {
                    break;
                }
                nums[j] &= val;
                ans = Math.min(ans, Math.abs(nums[j] - k));
            }
        }
        return ans;
    }


}