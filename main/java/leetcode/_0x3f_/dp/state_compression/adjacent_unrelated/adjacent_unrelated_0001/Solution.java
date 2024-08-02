package leetcode._0x3f_.dp.state_compression.adjacent_unrelated.adjacent_unrelated_0001;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 *1879. 两个数组最小的异或值之和
 *
 * 给你两个整数数组nums1 和nums2，它们长度都为n。
 * 两个数组的 异或值之和为(nums1[0] XOR nums2[0]) + (nums1[1] XOR nums2[1]) + ... + (nums1[n - 1] XOR nums2[n - 1])（下标从 0 开始）。
 * 	比方说，[1,2,3] 和[3,2,1]的 异或值之和等于(1 XOR 3) + (2 XOR 2) + (3 XOR 1) = 2 + 0 + 2 = 4。
 * 请你将nums2中的元素重新排列，使得 异或值之和最小。
 * 请你返回重新排列之后的 异或值之和。
 *
 * 示例 1：
 * 输入：nums1 = [1,2], nums2 = [2,3]
 * 输出：2
 * 解释：将 nums2 重新排列得到 [3,2] 。
 * 异或值之和为 (1 XOR 3) + (2 XOR 2) = 2 + 0 = 2 。
 *
 * 示例 2：
 * 输入：nums1 = [1,0,3], nums2 = [5,3,4]
 * 输出：8
 * 解释：将 nums2 重新排列得到 [5,4,3] 。
 * 异或值之和为 (1 XOR 5) + (0 XOR 4) + (3 XOR 3) = 4 + 4 + 0 = 8 。
 *
 * 提示：
 * 	n == nums1.length
 * 	n == nums2.length
 * 	1 <= n <= 14
 * 	0 <= nums1[i], nums2[i] <= 10^7
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/minimum-xor-sum-of-two-arrays
 * @title: 两个数组最小的异或值之和
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "minimumXORSum", "in.txt");
    }


    int[][] dp;
    int n;

    int[] nums1, nums2;

    public static int inf = Integer.MAX_VALUE;

    public int minimumXORSum(int[] nums1, int[] nums2) {
        this.n = nums1.length;
        this.nums2 = nums2;
        this.nums1 = nums1;
        this.dp = new int[n][(1 << n)];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], inf);
        }

        return dfs(0, 0);
    }

    public int dfs(int id, int mask) {
        if (id == n) {
            return 0;
        }
        if (dp[id][mask] != inf) {
            return dp[id][mask];
        }
        int res = inf;
        for (int i = 0; i < n; i++) {
            if ((mask >> i & 1) == 1) {
                continue;
            }
            res = Math.min(res, dfs(id + 1, mask | (1 << i)) + (nums1[id] ^ nums2[i]));
        }
        dp[id][mask] = res;
        return res;
    }


}