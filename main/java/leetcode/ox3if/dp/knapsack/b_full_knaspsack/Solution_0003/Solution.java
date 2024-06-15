package leetcode.ox3if.dp.knapsack.b_full_knaspsack.Solution_0003;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 1449. 数位成本和为目标值的最大数字
 * <p>
 * 给你一个整数数组cost和一个整数target。
 * 请你返回满足如下规则可以得到的最大整数：
 * 给当前结果添加一个数位（i + 1）的成本为cost[i]（cost数组下标从 0 开始）。
 * 总成本必须恰好等于target。
 * 添加的数位中没有数字 0 。
 * 由于答案可能会很大，请你以字符串形式返回。
 * 如果按照上述要求无法得到任何整数，请你返回 "0" 。
 * <p>
 * 示例 1：
 * 输入：cost = [4,3,2,5,6,7,2,5,5], target = 9
 * 输出："7772"
 * 解释：添加数位 '7' 的成本为 2 ，添加数位 '2' 的成本为 3 。所以 "7772" 的代价为 2*3+ 3*1 = 9 。 "977" 也是满足要求的数字，但 "7772" 是较大的数字。
 * 数字     成本
 * 1  ->   4
 * 2  ->   3
 * 3  ->   2
 * 4  ->   5
 * 5  ->   6
 * 6  ->   7
 * 7  ->   2
 * 8  ->   5
 * 9  ->   5
 * <p>
 * 示例 2：
 * 输入：cost = [7,6,5,5,5,6,8,7,8], target = 12
 * 输出："85"
 * 解释：添加数位 '8' 的成本是 7 ，添加数位 '5' 的成本是 5 。"85" 的成本为 7 + 5 = 12 。
 * <p>
 * 示例 3：
 * 输入：cost = [2,4,6,2,4,6,4,4,4], target = 5
 * 输出："0"
 * 解释：总成本是 target 的条件下，无法生成任何整数。
 * <p>
 * 示例 4：
 * 输入：cost = [6,10,15,40,40,40,40,40,40], target = 47
 * 输出："32211"
 * <p>
 * 提示：
 * cost.length == 9
 * 1 <= cost[i] <= 5000
 * 1 <= target <= 5000
 *
 * @author: wuxin0011
 * @Description: 完全背包 + 记忆搜索 -> 递推 -> 空间压缩
 * @url: https://leetcode.cn/problems/form-largest-integer-with-digits-that-add-up-to-target
 * @title: 数位成本和为目标值的最大数字
 */
public class Solution {

    // 本题隐晦条件是 数字从[1,9]
    // 因此先可以当初完全背包做 求出最大长度 花费价值为cost[i-1]
    // 然后根据最大长度从大->小 枚举

    /**
     * 空间压缩
     */
    static class S0 {
        public static void main(String[] args) {
            IoUtil.testUtil(S0.class, "largestNumber", "in.txt");
        }


        public static int inf = 0x3fffff;

        public String largestNumber(int[] cost, int target) {
            int n = 10;
            int[] memo = new int[target + 1];
            Arrays.fill(memo, -inf);
            memo[0] = 0;
            for (int i = 1; i <= n - 1; i++) {
                int u = cost[i - 1];
                for (int c = u; c <= target; c++) {
                    memo[c] = Math.max(memo[c], memo[c - u] + 1);
                }
            }
            int v = memo[target];
            // 无法构成答案
            if (v <= 0) {
                return "0";
            }
            StringBuilder ans = new StringBuilder();
            for (int i = n - 1, k = target; i >= 1; i--) {
                int c = cost[i - 1];
                // 当剩余容量为K k>= 当前数字的价值 cost[i-1]
                //  并且 memo[i][k] == memo[i][k - c] + 1 => k - c
                for (; k >= c && memo[k] == memo[k - c] + 1; k -= c) {
                    ans.append(i);
                }
            }
            return ans.toString();
        }


    }

    /**
     * 递推
     */

    static class S1 {
        public static void main(String[] args) {
            IoUtil.testUtil(S1.class, "largestNumber", "in.txt");
        }


        public static int inf = 0x3fffff;

        public String largestNumber(int[] cost, int target) {

            int n = 10;
            int[][] memo = new int[n][target + 1];
            for (int i = 0; i < n; i++) {
                Arrays.fill(memo[i], -inf);
            }
            memo[0][0] = 0;
            for (int i = 1; i <= n - 1; i++) {
                int u = cost[i - 1];
                for (int c = 0; c <= target; c++) {
                    if (c < u) {
                        memo[i][c] = memo[i - 1][c];
                    } else {
                        memo[i][c] = Math.max(memo[i - 1][c], memo[i][c - u] + 1);
                    }
                }
            }
            int v = memo[n - 1][target];
            // 无法构成答案
            if (v <= 0) {
                return "0";
            }
            StringBuilder ans = new StringBuilder();
            for (int i = n - 1, k = target; i >= 1; i--) {
                int c = cost[i - 1];
                // 当剩余容量为K k>= 当前数字的价值 cost[i-1]
                //  并且 memo[i][k] == memo[i][k - c] + 1 => k - c
                for (; k >= c && memo[i][k] == memo[i][k - c] + 1; k -= c) {
                    ans.append(i);
                }
            }
            return ans.toString();
        }


    }

    /**
     * 记忆化搜索
     */

    static class S2 {
        public static void main(String[] args) {
            IoUtil.testUtil(S2.class, "largestNumber", "in.txt");
        }


        public String largestNumber(int[] cost, int target) {

            this.cost = cost;
            int n = 10;
            this.memo = new int[n][target + 1];
            for (int[] ints : memo) {
                Arrays.fill(ints, -1);
            }
            int v = dfs(n - 1, target);
            // 无法构成答案
            if (v <= 0) {
                return "0";
            }
            StringBuilder ans = new StringBuilder();
            for (int i = n - 1, k = target; i >= 1; i--) {
                int c = cost[i - 1];
                // 当剩余容量为K k>= 当前数字的价值 cost[i-1]
                //  并且 memo[i][k] == memo[i][k - c] + 1 => k - c
                for (; k >= c && memo[i][k] == memo[i][k - c] + 1; k -= c) {
                    ans.append(i);
                }
            }
            return ans.toString();
        }

        public static int inf = 0x3fffff;
        int[] cost;

        // 记忆化搜索 + 完全背包
        int[][] memo;

        public int dfs(int i, int c) {
            if (i == 0) {
                return c == 0 ? 0 : -inf;
            }
            if (memo[i][c] != -1) {
                return memo[i][c];
            }
            int res = 0;
            if (c < cost[i - 1]) {
                res = dfs(i - 1, c);
            } else {
                res = Math.max(dfs(i - 1, c), dfs(i, c - cost[i - 1]) + 1);
            }
            memo[i][c] = res;
            return res;
        }

    }


}