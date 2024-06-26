package leetcode.contest.weekly.w_400.w_403.c;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 3196. 最大化子数组的总成本
 * <p>
 * 给你一个长度为 n 的整数数组 nums。
 * 子数组 nums[l..r]（其中 0 <= l <= r < n）的 成本 定义为：
 * cost(l, r) = nums[l] - nums[l + 1] + ... + nums[r] * (−1)r − l
 * 你的任务是将 nums 分割成若干子数组，使得所有子数组的成本之和 最大化，并确保每个元素 正好 属于一个子数组。
 * 具体来说，如果 nums 被分割成 k 个子数组，且分割点为索引 i1, i2, ..., ik − 1（其中 0 <= i1 < i2 < ... < ik - 1 < n - 1），则总成本为：
 * cost(0, i1) + cost(i1 + 1, i2) + ... + cost(ik − 1 + 1, n − 1)
 * 返回在最优分割方式下的子数组成本之和的最大值。
 * 注意：如果 nums 没有被分割，即 k = 1，则总成本即为 cost(0, n - 1)。
 * <p>
 * 示例 1：
 * 输入： nums = [1,-2,3,4]
 * 输出： 10
 * 解释：
 * 一种总成本最大化的方法是将 [1, -2, 3, 4] 分割成子数组 [1, -2, 3] 和 [4]。总成本为 (1 + 2 + 3) + 4 = 10。
 * <p>
 * 示例 2：
 * 输入： nums = [1,-1,1,-1]
 * 输出： 4
 * 解释：
 * 一种总成本最大化的方法是将 [1, -1, 1, -1] 分割成子数组 [1, -1] 和 [1, -1]。总成本为 (1 + 1) + (1 + 1) = 4。
 * <p>
 * 示例 3：
 * 输入： nums = [0]
 * 输出： 0
 * 解释：
 * 无法进一步分割数组，因此答案为 0。
 * <p>
 * 示例 4：
 * 输入： nums = [1,-1]
 * 输出： 2
 * 解释：
 * 选择整个数组，总成本为 1 + 1 = 2，这是可能的最大成本。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-403/problems/maximize-total-cost-of-alternating-subarrays
 * @title: 最大化子数组的总成本
 */
public class C {


    static class S0 {
        public static void main(String[] args) {
            IoUtil.testUtil(S0.class, "maximumTotalCost1", "C.txt");
        }

        public long maximumTotalCost(int[] nums) {
            return 0;
        }

    }


    static class S1 {
        public static void main(String[] args) {
            IoUtil.testUtil(S1.class, "maximumTotalCost", "C.txt");
        }

        public long maximumTotalCost(int[] nums) {
            n = nums.length;
            memo = new long[n][2];
            for (int i = 0; i < n; i++) {
                Arrays.fill(memo[i], -1);
            }
            this.nums = nums;
            return dfs(0, 0);
        }

        int n;
        long[][] memo;
        int[] nums;

        public long dfs(int i, int j) {
            if (i == n) {
                return 0;
            }
            if (memo[i][j] != -1) return memo[i][j];
            long res = Math.max(dfs(i + 1, 1) + nums[i], dfs(i + 1, j ^ 1) + (j == 1 ? -nums[i] : nums[i]));
            memo[i][j] = res;
            return res;
        }

    }


    /**
     * 比赛我的做法 不过超时了 本地不是区间DP 没有注意时间复杂度
     */
    static class Timeout {
        public static void main(String[] args) {
            IoUtil.testUtil(Timeout.class, "maximumTotalCost1", "C.txt");
        }


        //    @TestCaseGroup(start = 4)
        public long maximumTotalCost(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int c = 0;
            long s = 0;
            for (int num : nums) {
                if (num < 0) {
                    c++;
                }
                s += num;
            }
            if (c == 0) {
                return s;
            }
            n = nums.length;
            memo = new long[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(memo[i], -1);
            }
            this.nums = nums;
            return dfs(0, 0);
        }

        long[][] memo;
        int[] nums;
        int n;

        public long dfs(int l, int r) {
            if (r == n) {
                return 0;
            }
            if (memo[l][r] != -1) {
                return memo[l][r];
            }
            long res = Math.max(dfs(l + 1, r + 1) + nums[r], dfs(l, r + 1) + ((r - l) % 2 == 1 ? -nums[r] : nums[r]));
            memo[l][r] = res;
            return res;
        }


        public long maximumTotalCost1(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int n = nums.length;
            int c = 0;
            int s = 0;
            for (int num : nums) {
                if (num < 0) {
                    c++;
                }
                s += Math.abs(num);
            }
            if (c == 0) {
                return s;
            }
            long[][] f = new long[n + 1][n + 1];

            for (int r = n - 1; r >= 0; r--) {
                if (f[0][0] >= s) {
                    return s;
                }
                for (int l = r; l >= 0; l--) {
                    f[l][r] = Math.max(f[l + 1][r + 1] + nums[r], f[l][r + 1] + ((r - l) % 2 == 1 ? -nums[r] : nums[r]));
                }
            }

            return f[0][0];
        }

        public long maximumTotalCost2(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int n = nums.length;
            int c = 0;
            long s = 0;
            long a = 0, b = 0;

            for (int i = 0; i < n; i++) {
                int num = nums[i];
                if (i % 2 == 0) {
                    a += num;
                } else {
                    b += num;
                }
                if (num < 0) {
                    c++;
                }
                s += num;
            }
            if (c == 0) {
                return s;
            }


            return s;
        }
    }


}