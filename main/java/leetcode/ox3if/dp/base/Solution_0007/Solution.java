package leetcode.ox3if.dp.base.Solution_0007;

import code_generation.utils.IoUtil;

/**
 * 2320. 统计放置房子的方式数
 * <p>
 * 一条街道上共有 n * 2 个 地块 ，街道的两侧各有 n 个地块。
 * 每一边的地块都按从 1 到 n 编号。每个地块上都可以放置一所房子。
 * 现要求街道同一侧不能存在两所房子相邻的情况，请你计算并返回放置房屋的方式数目。由于答案可能很大，需要对 10^9 + 7 取余后再返回。
 * 注意，如果一所房子放置在这条街某一侧上的第 i 个地块，不影响在另一侧的第 i 个地块放置房子。
 * <p>
 * 示例 1：
 * 输入：n = 1
 * 输出：4
 * 解释：
 * 可能的放置方式：
 * 1. 所有地块都不放置房子。
 * 2. 一所房子放在街道的某一侧。
 * 3. 一所房子放在街道的另一侧。
 * 4. 放置两所房子，街道两侧各放置一所。
 * <p>
 * 示例 2：
 * 输入：n = 2
 * 输出：9
 * 解释：如上图所示，共有 9 种可能的放置方式。
 * <p>
 * 提示：
 * 1 <= n <= 10^4
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/count-number-of-ways-to-place-houses
 * @title: 统计放置房子的方式数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "countHousePlacements", "in.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    static int max = 10001;
    static long[] dp;

    static {
        dp = new long[max];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % MOD;
        }
    }

    public int countHousePlacements(int n) {
        return (int)((long) dp[n] * dp[n] % MOD);
    }


}