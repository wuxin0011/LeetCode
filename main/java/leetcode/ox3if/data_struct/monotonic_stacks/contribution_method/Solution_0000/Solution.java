package leetcode.ox3if.data_struct.monotonic_stacks.contribution_method.Solution_0000;

import code_generation.utils.IoUtil;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 907. 子数组的最小值之和
 * <p>
 * 给定一个整数数组 arr，找到 min(b)的总和，其中 b 的范围为 arr 的每个（连续）子数组。
 * 由于答案可能很大，因此 返回答案模 10^9 + 7 。
 * <p>
 * 示例 1：
 * 输入：arr = [3,1,2,4]
 * 输出：17
 * 解释：
 * 子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
 * 最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
 * <p>
 * 示例 2：
 * 输入：arr = [11,81,94,43,3]
 * 输出：444
 * <p>
 * 提示：
 * 1 <= arr.length <= 3 * 10^4
 * 1 <= arr[i] <= 3 * 10^4
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/sum-of-subarray-minimums
 * @title: 子数组的最小值之和
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "sumSubarrayMins", "in.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public int sumSubarrayMins(int[] a) {

        Deque<Integer> sk = new ArrayDeque<>();
        int n = a.length;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            // 大 -> 小
            while (!sk.isEmpty() && a[i] <= a[sk.peek()]) {
                int cur = sk.pop();
                int left = sk.isEmpty() ? -1 : sk.peek();
                ans = (ans + (cur - left) * 1L * (i - cur) *  a[cur]) % MOD;
            }
            sk.push(i);
        }
        while (!sk.isEmpty()) {
            int cur = sk.pop();
            int left = sk.isEmpty() ? -1 : sk.peek();
            ans = (ans + (cur - left) * 1L * (n - cur) *  a[cur]) % MOD;
        }
        return (int) (ans % MOD);
    }


}