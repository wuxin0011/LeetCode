package leetcode._0x3f_.data_struct.pre_sum.hash.hash_0006;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;
/**
 *
 * 2588. 统计美丽子数组数目
 *
 * 给你一个下标从 0开始的整数数组nums。
 * 每次操作中，你可以：
 * 	选择两个满足0 <= i, j < nums.length的不同下标i和j。
 * 	选择一个非负整数k，满足 nums[i]和 nums[j]在二进制下的第 k位（下标编号从 0开始）是 1。
 * 	将 nums[i]和 nums[j]都减去2k。
 * 如果一个子数组内执行上述操作若干次后，该子数组可以变成一个全为 0的数组，那么我们称它是一个 美丽的子数组。
 * 请你返回数组 nums中 美丽子数组的数目。
 * 子数组是一个数组中一段连续 非空的元素序列。
 *
 * 示例 1：
 * 输入：nums = [4,3,1,2,4]
 * 输出：2
 * 解释：nums 中有 2 个美丽子数组：[4,3,1,2,4] 和 [4,3,1,2,4] 。
 * - 按照下述步骤，我们可以将子数组 [3,1,2] 中所有元素变成 0 ：
 *   - 选择 [3, 1, 2] 和 k = 1 。将 2 个数字都减去 21 ，子数组变成 [1, 1, 0] 。
 *   - 选择 [1, 1, 0] 和 k = 0 。将 2 个数字都减去 20 ，子数组变成 [0, 0, 0] 。
 * - 按照下述步骤，我们可以将子数组 [4,3,1,2,4] 中所有元素变成 0 ：
 *   - 选择 [4, 3, 1, 2, 4] 和 k = 2 。将 2 个数字都减去 22 ，子数组变成 [0, 3, 1, 2, 0] 。
 *   - 选择 [0, 3, 1, 2, 0] 和 k = 0 。将 2 个数字都减去 20 ，子数组变成 [0, 2, 0, 2, 0] 。
 *   - 选择 [0, 2, 0, 2, 0] 和 k = 1 。将 2 个数字都减去 21 ，子数组变成 [0, 0, 0, 0, 0] 。
 *
 * 示例 2：
 * 输入：nums = [1,10,4]
 * 输出：0
 * 解释：nums 中没有任何美丽子数组。
 *
 * 提示：
 * 	1 <= nums.length <= 10^5
 * 	0 <= nums[i] <= 10^6
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/count-the-number-of-beautiful-subarrays
 * @title: 统计美丽子数组数目
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "beautifulSubarrays", "in.txt");
    }


    // 每个二进制位单独处理

    public long beautifulSubarrays(int[] a) {
        int n = a.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] ^ a[i];
        }
        Map<Integer, Integer> map = new HashMap<>();
        // System.out.println(Arrays.toString(s));
        int ans = 0;
        for (int j : s) {
            int x = map.getOrDefault(j, 0);
            ans += x;
            map.put(j, x + 1);
        }
        return ans;
    }


}