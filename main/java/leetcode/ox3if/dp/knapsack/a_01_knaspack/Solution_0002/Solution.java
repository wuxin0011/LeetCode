package leetcode.ox3if.dp.knapsack.a_01_knaspack.Solution_0002;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/target-sum
 * @title: 目标和
 */
public class Solution {


    /**
     * 一个数组滚动更新
     */

    static class S0 {
        public static void main(String[] args) {
            IoUtil.testUtil(S0.class, "findTargetSumWays", "in.txt");
        }

        public int findTargetSumWays(int[] nums, int target) {
            for (int num : nums) {
                target += num;
            }
            // target + sum < 0
            // 说明 无法满足条件
            //
            if (target < 0 || target % 2 == 1) {
                return 0;
            }
            target /= 2;
            int[] f = new int[target + 1];
            f[0] = 1;
            for (int num : nums) {
                for (int c = target; c >= num; c--) {
                    f[c] += f[c - num];
                }
            }
            return f[target];
        }


    }


    /**
     * 二维数组递推
     */
    static class S1 {
        public static void main(String[] args) {
            IoUtil.testUtil(S1.class, "findTargetSumWays", "in.txt");
        }


        public int findTargetSumWays(int[] nums, int target) {
            for (int num : nums) {
                target += num;
            }
            // target + sum < 0
            // 说明 无法满足条件
            //
            if (target < 0 || target % 2 == 1) {
                return 0;
            }
            target /= 2;
            int n = nums.length;
            int[][] f = new int[n + 1][target + 1];
            f[0][0] = 1;
            for (int i = 0; i < n; i++) {
                for (int c = 0; c <= target; c++) {
                    if (c < nums[i]) {
                        f[i + 1][c] = f[i][c];
                    } else {
                        f[i + 1][c] = f[i][c] + f[i][c - nums[i]];
                    }
                }
            }
            return f[n][target];
        }


    }

    /**
     * 递归
     */
    static class S2 {
        public static void main(String[] args) {
            IoUtil.testUtil(S2.class, "findTargetSumWays", "in.txt");
        }

        public int findTargetSumWays(int[] nums, int target) {
            this.nums = nums;
            return dfs(0, target);
        }

        int[] nums;
        int[][] memo;

        public int dfs(int i, int s) {
            if (i == nums.length) {
                return s == 0 ? 1 : 0;
            }
            return dfs(i + 1, s - nums[i]) + dfs(i + 1, s + nums[i]);
        }

    }


}