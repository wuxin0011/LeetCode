package leetcode.ox3if.dp.base.Solution_0012;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 *
 *
 * 1191. K 次串联后最大子数组之和
 *
 * 给定一个整数数组arr和一个整数k，通过重复k次来修改数组。
 * 例如，如果arr = [1, 2]，k = 3，那么修改后的数组将是 [1, 2, 1, 2, 1, 2] 。
 * 返回修改后的数组中的最大的子数组之和。注意，子数组长度可以是 0，在这种情况下它的总和也是 0。
 * 由于结果可能会很大，需要返回的10^9+ 7的模。
 *
 * 示例 1：
 * 输入：arr = [1,2], k = 3
 * 输出：9
 *
 * 示例 2：
 * 输入：arr = [1,-2,1], k = 5
 * 输出：2
 *
 * 示例 3：
 * 输入：arr = [-1,-2], k = 7
 * 输出：0
 *
 * 提示：
 * 	1 <= arr.length <= 10^5
 * 	1 <= k <= 10^5
 * 	-10^4<= arr[i] <= 10^4
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/k-concatenation-maximum-sum
 * @title: 次串联后最大子数组之和
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "kConcatenationMaxSum", "in.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public int kConcatenationMaxSum(int[] arr, int k) {
        long ans = 0;
        int n = arr.length;
        if (n == 0) return 0;
        long tot = 0;
        int mx = 0;
        for (int j : arr) {
            tot += j;
            if (mx < j) {
                mx = j;
            }
        }
        if (mx < tot) {
            return (int) (tot * k % MOD);
        }
        if (mx == 0) {
            return 0;
        }
        // 单个子数组最大值
        int single_max = findMax(arr, k);
        System.out.println(Arrays.toString(arr) + ",k = " + k);
        return single_max;
    }


    public static int findMax(int[] nums, int k) {
        int ans = 0;
        int n = nums.length;
        int tot = 0;
        for (int i = 0; i < k * n; i++) {
            int id = i % n;
            System.out.print(" " + nums[id] + ",");
            tot = (tot + nums[id]) % MOD;
            ans = Math.max(ans, tot);
            if (tot < 0) {
                tot = 0;
            }
        }
        System.out.println("");
        return ans;
    }


}