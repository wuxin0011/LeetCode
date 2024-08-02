package leetcode._0x3f_.dp.knapsack.c_much_knaspack.Solution_0000;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 2585. 获得分数的方法数
 * <p>
 * 考试中有 n 种类型的题目。
 * 给你一个整数 target 和一个下标从 0 开始的二维整数数组 types ，其中 types[i] = [counti, marksi] 表示第 i 种类型的题目有 counti 道，每道题目对应 marksi 分。
 * 返回你在考试中恰好得到 target 分的方法数。由于答案可能很大，结果需要对 10^9 +7 取余。
 * 注意，同类型题目无法区分。
 * 比如说，如果有 3 道同类型题目，那么解答第 1 和第 2 道题目与解答第 1 和第 3 道题目或者第 2 和第 3 道题目是相同的。
 * <p>
 * 示例 1：
 * 输入：target = 6, types = [[6,1],[3,2],[2,3]]
 * 输出：7
 * 解释：要获得 6 分，你可以选择以下七种方法之一：
 * - 解决 6 道第 0 种类型的题目：1 + 1 + 1 + 1 + 1 + 1 = 6
 * - 解决 4 道第 0 种类型的题目和 1 道第 1 种类型的题目：1 + 1 + 1 + 1 + 2 = 6
 * - 解决 2 道第 0 种类型的题目和 2 道第 1 种类型的题目：1 + 1 + 2 + 2 = 6
 * - 解决 3 道第 0 种类型的题目和 1 道第 2 种类型的题目：1 + 1 + 1 + 3 = 6
 * - 解决 1 道第 0 种类型的题目、1 道第 1 种类型的题目和 1 道第 2 种类型的题目：1 + 2 + 3 = 6
 * - 解决 3 道第 1 种类型的题目：2 + 2 + 2 = 6
 * - 解决 2 道第 2 种类型的题目：3 + 3 = 6
 * <p>
 * 示例 2：
 * 输入：target = 5, types = [[50,1],[50,2],[50,5]]
 * 输出：4
 * 解释：要获得 5 分，你可以选择以下四种方法之一：
 * - 解决 5 道第 0 种类型的题目：1 + 1 + 1 + 1 + 1 = 5
 * - 解决 3 道第 0 种类型的题目和 1 道第 1 种类型的题目：1 + 1 + 1 + 2 = 5
 * - 解决 1 道第 0 种类型的题目和 2 道第 1 种类型的题目：1 + 2 + 2 = 5
 * - 解决 1 道第 2 种类型的题目：5
 * <p>
 * 示例 3：
 * 输入：target = 18, types = [[6,1],[3,2],[2,3]]
 * 输出：1
 * 解释：只有回答所有题目才能获得 18 分。
 * <p>
 * 提示：
 * 1 <= target <= 1000
 * n == types.length
 * 1 <= n <= 50
 * types[i].length == 2
 * 1 <= counti, marksi <= 50
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/number-of-ways-to-earn-points
 * @title: 获得分数的方法数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "waysToReachTarget", "in.txt");
    }

    private static final int MOD = (int) 1e9 + 7;


    public int waysToReachTarget(int target, int[][] types) {
        int n = types.length;
        memo = new long[n][target + 1];
        this.types = types;
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return (int) dfs(n - 1, target);
    }

    int[][] types;
    long[][] memo;

    static int inf = 0x3fffff;

    public long dfs(int i, int c) {
        if (c < 0) {
            return 0;
        }
        if (i < 0) {
            return c == 0 ? 1 : 0;
        }
        if (memo[i][c] != -1) {
            return memo[i][c];
        }

        long res = 0;
        if (c < types[i][1]) {
            res = dfs(i - 1, c);
        } else {
            int N = types[i][0];
            int score = types[i][1];
            for (int t = 0; t <= N; t++) {
                if (c < score * t) {
                    break;
                }
                res = (res + dfs(i - 1, c - score * t) + MOD) % MOD;
            }
        }
        memo[i][c] = res;
        return res;
    }


}