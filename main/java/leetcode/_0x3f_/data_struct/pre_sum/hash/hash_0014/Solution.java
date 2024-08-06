package leetcode._0x3f_.data_struct.pre_sum.hash.hash_0014;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.List;

/**
 * 2845. 统计趣味子数组的数目
 * <p>
 * 给你一个下标从 0 开始的整数数组 nums ，以及整数 modulo 和整数 k 。
 * 请你找出并统计数组中 趣味子数组 的数目。
 * 如果 子数组 nums[l..r] 满足下述条件，则称其为 趣味子数组 ：
 * 在范围 [l, r] 内，设 cnt 为满足 nums[i] % modulo == k 的索引 i 的数量。并且 cnt % modulo == k 。
 * 以整数形式表示并返回趣味子数组的数目。
 * 注意：子数组是数组中的一个连续非空的元素序列。
 * <p>
 * 示例 1：
 * 输入：nums = [3,2,4], modulo = 2, k = 1
 * 输出：3
 * 解释：在这个
 * 示例中，趣味子数组分别是：
 * 子数组 nums[0..0] ，也就是 [3] 。
 * - 在范围 [0, 0] 内，只存在 1 个下标 i = 0 满足 nums[i] % modulo == k 。
 * - 因此 cnt = 1 ，且 cnt % modulo == k 。
 * 子数组 nums[0..1] ，也就是 [3,2] 。
 * - 在范围 [0, 1] 内，只存在 1 个下标 i = 0 满足 nums[i] % modulo == k 。
 * - 因此 cnt = 1 ，且 cnt % modulo == k 。
 * 子数组 nums[0..2] ，也就是 [3,2,4] 。
 * - 在范围 [0, 2] 内，只存在 1 个下标 i = 0 满足 nums[i] % modulo == k 。
 * - 因此 cnt = 1 ，且 cnt % modulo == k 。
 * 可以证明不存在其他趣味子数组。因此，答案为 3 。
 * <p>
 * 示例 2：
 * 输入：nums = [3,1,9,6], modulo = 3, k = 0
 * 输出：2
 * 解释：在这个
 * 示例中，趣味子数组分别是：
 * 子数组 nums[0..3] ，也就是 [3,1,9,6] 。
 * - 在范围 [0, 3] 内，只存在 3 个下标 i = 0, 2, 3 满足 nums[i] % modulo == k 。
 * - 因此 cnt = 3 ，且 cnt % modulo == k 。
 * 子数组 nums[1..1] ，也就是 [1] 。
 * - 在范围 [1, 1] 内，不存在下标满足 nums[i] % modulo == k 。
 * - 因此 cnt = 0 ，且 cnt % modulo == k 。
 * 可以证明不存在其他趣味子数组，因此答案为 2 。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= modulo <= 10^9
 * 0 <= k < modulo
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/count-of-interesting-subarrays
 * @title: 统计趣味子数组的数目
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "countInterestingSubarrays", "in.txt");
    }

    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        // System.out.println(Arrays.toString(sums));
        long ans = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int sums = 0;
        map.put(0,1);
        for (int i = 0; i < n; i++) {
            sums += nums.get(i) % modulo == k ? 1 : 0;
            // System.out.println(sums[i]);
            int key = calcMOD(modulo, sums, -k);
            ans += map.getOrDefault(key, 0);
            key = calcMOD(modulo, sums);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        return ans;
    }

    // (s[r] - s[l]) % mod = k
    // (s[r] - k) % mod = s[l] % mod
    // System.out.println("ans = " + ans);
    public long countInterestingSubarrays1(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        int[] sums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + (nums.get(i) % modulo == k ? 1 : 0);
        }
        // System.out.println(Arrays.toString(sums));
        long ans = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i <= n; i++) {
            // System.out.println(sums[i]);
            int key = calcMOD(modulo, sums[i], -k);
            ans += map.getOrDefault(key, 0);
            key = calcMOD(modulo, sums[i]);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        return ans;
    }

    public static int calcMOD(int mod, int... args) {
        int s = 0;
        for (int v : args) {
            s += v % mod;
        }
        return (s + mod) % mod;
    }


}