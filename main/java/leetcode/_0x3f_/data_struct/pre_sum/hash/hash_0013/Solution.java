package leetcode._0x3f_.data_struct.pre_sum.hash.hash_0013;

import code_generation.utils.IoUtil;

import java.util.HashMap;

/**
 * 1590. 使数组和能被 P 整除
 * <p>
 * 给你一个正整数数组nums，请你移除 最短子数组（可以为 空），使得剩余元素的 和能被 p整除。
 * 不允许将整个数组都移除。
 * 请你返回你需要移除的最短子数组的长度，如果无法满足题目要求，返回 -1。
 * 子数组定义为原数组中连续的一组元素。
 * <p>
 * 示例 1：
 * 输入：nums = [3,1,4,2], p = 6
 * 输出：1
 * 解释：nums 中元素和为 10，不能被 p 整除。我们可以移除子数组 [4] ，剩余元素的和为 6 。
 * <p>
 * 示例 2：
 * 输入：nums = [6,3,5,2], p = 9
 * 输出：2
 * 解释：我们无法移除任何一个元素使得和被 9 整除，最优方案是移除子数组 [5,2] ，剩余元素为 [6,3]，和为 9 。
 * <p>
 * 示例3：
 * 输入：nums = [1,2,3], p = 3
 * 输出：0
 * 解释：和恰好为 6 ，已经能被 3 整除了。所以我们不需要移除任何元素。
 * <p>
 * 示例 4：
 * 输入：nums = [1,2,3], p = 7
 * 输出：-1
 * 解释：没有任何方案使得移除子数组后剩余元素的和被 7 整除。
 * <p>
 * 示例 5：
 * 输入：nums = [1000000000,1000000000,1000000000], p = 3
 * 输出：0
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= p <= 10^9
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/make-sum-divisible-by-p
 * @title: make-sum-divisible-by-p
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "minSubarray", "in.txt");
    }


    // t = x mod p
    // (s[r] - s[l]) % mod = x % mod
    // s[r] - s[l] = x % mod
    // s[r] - x%mod = s[l]
    // (s[r] % mod -x % mod + mod)%mod = s[l] % mod


    public int minSubarray(int[] a, int p) {
        int n = a.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = calcMod(s[i], a[i], p);
        }
        if (s[n] % p == 0) {
            return 0;
        }
        int ans = n;
        int rest = s[n] % p;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i <= n; i++) {
            map.put(s[i], i);
            int x = map.getOrDefault(calcMod(s[i], -rest, p), -n);
            ans = Math.min(ans, i - x);
        }

        return ans < n ? ans : -1;
    }


    public static int calcMod(long x, long y, int mod) {
        return (int) ((x % mod + y % mod + mod) % mod);
    }

}