package leetcode._0x3f_.bitwise_operations.and_or.and_or_0003;

import code_generation.utils.IoUtil;

/**
 * 2871. 将数组分割成最多数目的子数组
 * <p>
 * 给你一个只包含 非负整数的数组nums。
 * 我们定义满足 l <= r的子数组nums[l..r]的分数为nums[l] AND nums[l + 1] AND ... AND nums[r]，其中AND是按位与运算。
 * 请你将数组分割成一个或者更多子数组，满足：
 * 每个 元素都只属于一个子数组。
 * 子数组分数之和尽可能小。
 * 请你在满足以上要求的条件下，返回最多可以得到多少个子数组。
 * 一个 子数组是一个数组中一段连续的元素。
 * <p>
 * 示例 1：
 * 输入：nums = [1,0,2,0,1,2]
 * 输出：3
 * 解释：我们可以将数组分割成以下子数组：
 * - [1,0] 。子数组分数为 1 AND 0 = 0 。
 * - [2,0] 。子数组分数为 2 AND 0 = 0 。
 * - [1,2] 。子数组分数为 1 AND 2 = 0 。
 * 分数之和为 0 + 0 + 0 = 0 ，是我们可以得到的最小分数之和。
 * 在分数之和为 0 的前提下，最多可以将数组分割成 3 个子数组。所以返回 3 。
 * <p>
 * 示例 2：
 * 输入：nums = [5,7,1,3]
 * 输出：1
 * 解释：我们可以将数组分割成一个子数组：[5,7,1,3] ，分数为 1 ，这是可以得到的最小总分数。
 * 在总分数为 1 的前提下，最多可以将数组分割成 1 个子数组。所以返回 1 。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^6
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/split-array-into-maximum-number-of-subarrays
 * @title: 将数组分割成最多数目的子数组
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maxSubarrays", "in.txt");
    }

    static int inf = 0xfffffff;

    // 由于 and 的性质 and 越多 结果 越小 最小为0
    // 如果没有出现0 整个数组才是子数组
    // 如果出现0 可以按照 与的性质 划分子数组
    // 遇到 and = 0 可以划分
    public int maxSubarrays(int[] nums) {
        int n = nums.length;
        int mi = nums[0];
        for (int i = 1; i < n; i++) {
            mi &= nums[i];
        }
        if (mi > 0) {
            return 1; // 只能划分一个子数组
        }
        mi = -1;
        int ans = 0;
        for (int num : nums) {
            mi &= num;
            if (mi == 0) {
                ans++;
                mi = -1; //
            }
        }
        return ans;
    }


}