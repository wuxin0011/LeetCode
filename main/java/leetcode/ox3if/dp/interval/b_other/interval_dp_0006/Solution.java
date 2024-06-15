package leetcode.ox3if.dp.interval.b_other.interval_dp_0006;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 1547. 切棍子的最小成本
 * <p>
 * 有一根长度为 n 个单位的木棍，棍上从 0 到 n 标记了若干位置。
 * 例如，长度为 6 的棍子可以标记如下：
 * 给你一个整数数组 cuts ，其中 cuts[i] 表示你需要将棍子切开的位置。
 * 你可以按顺序完成切割，也可以根据需要更改切割的顺序。
 * 每次切割的成本都是当前要切割的棍子的长度，切棍子的总成本是历次切割成本的总和。对棍子进行切割将会把一根木棍分成两根较小的木棍（这两根木棍的长度和就是切割前木棍的长度）。请参阅第一个
 * 示例以获得更直观的解释。
 * 返回切棍子的 最小总成本 。
 * <p>
 * 示例 1：
 * 输入：n = 7, cuts = [1,3,4,5]
 * 输出：16
 * 解释：按 [1, 3, 4, 5] 的顺序切割的情况如下所示：
 * 第一次切割长度为 7 的棍子，成本为 7 。第二次切割长度为 6 的棍子（即第一次切割得到的第二根棍子），第三次切割为长度 4 的棍子，最后切割长度为 3 的棍子。总成本为 7 + 6 + 4 + 3 = 20 。
 * 而将切割顺序重新排列为 [3, 5, 1, 4] 后，总成本 = 16（如
 * 示例图中 7 + 4 + 3 + 2 = 16）。
 * <p>
 * 示例 2：
 * 输入：n = 9, cuts = [5,6,1,4,2]
 * 输出：22
 * 解释：如果按给定的顺序切割，则总成本为 25 。总成本 <= 25 的切割顺序很多，例如，[4, 6, 5, 2, 1] 的总成本 = 22，是所有可能方案中成本最小的。
 * <p>
 * 提示：
 * 2 <= n <= 10^6
 * 1 <= cuts.length <= min(n - 1, 100)
 * 1 <= cuts[i] <= n - 1
 * cuts 数组中的所有整数都 互不相同
 * <p>
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/minimum-cost-to-cut-a-stick
 * @title: 切棍子的最小成本
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "minCost", "in.txt");
    }


    public int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);
        int N = cuts.length + 2;
        cost = new int[N];
        cost[0] = 0;

        System.arraycopy(cuts, 0, cost, 1, N - 2);
        cost[N-1] = n;
        // System.out.println("cuts = " + Arrays.toString(cuts) + ",cost = " + Arrays.toString(cost) + ",n = " + n);
        memo = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(memo[i], -1);
        }
        // [1,N-1]
        return dfs(1, N - 2);
    }

    int[] cost;

    int[][] memo;

    public int dfs(int l, int r) {
        // 无效区间
        if (l > r) {
            return 0;
        }
        if (memo[l][r] != -1) {
            return memo[l][r];
        }
        int res = Integer.MAX_VALUE;
        if (l == r) {
            res = 0;
        } else {
            for (int m = l; m <= r; m++) {
                res = Math.min(res, dfs(l, m-1) + dfs(m + 1, r));
            }
        }
        res += cost[r + 1] - cost[l - 1];

        memo[l][r] = res;
        return res;
    }


}