package leetcode.ox3if.dp.interval.b_other.interval_dp_0011;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 *
 * 312. 戳气球
 *
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组nums中。
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。这里的 i - 1 和 i + 1 代表和i相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 * 求所能获得硬币的最大数量。
 *
 * 示例 1：
 * 输入：nums = [3,1,5,8]
 * 输出：167
 * 解释：
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 *
 * 示例 2：
 * 输入：nums = [1,5]
 * 输出：10
 *
 * 提示：
 * 	n == nums.length
 * 	1 <= n <= 300
 * 	0 <= nums[i] <= 100
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/burst-balloons
 * @title: 戳气球
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maxCoins", "in.txt");
    }


    public int maxCoins(int[] nums) {
        this.values = nums;
        int n = values.length;
        this.memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(0, n - 1);
    }

    int[][] memo;
    int[] values;

    public int dfs(int l, int r) {
        if (l > r) {
            return 0;
        }
        if (memo[l][r] != -1) {
            return memo[l][r];
        }
        int res = 0;
        // 枚举那个位置为末尾 然后递归
        // [l,r] => [l,k - 1] & [k + 1,r] + cur coins
        for (int k = l; k <= r; k++) {
            // 递归
            res = Math.max(res, dfs(l, k - 1) + dfs(k + 1, r) + coins(l, k, r));
        }
        memo[l][r] = res;
        return res;
    }

    public int coins(int l, int m, int r) {
        int a = 1;
        int b = values[m];
        int c = 1;
        if (l - 1 >= 0) {
            a = values[l - 1];
        }
        if (r + 1 < values.length) {
            c = values[r + 1];
        }
        return a * b * c;
    }


}