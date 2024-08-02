package leetcode._0x3f_.dp.digital.digital_dp_0010;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 2827. 范围中美丽整数的数目
 * <p>
 * 给你正整数low，high和k。
 * 如果一个数满足以下两个条件，那么它是 美丽的：
 * 偶数数位的数目与奇数数位的数目相同。
 * 这个整数可以被k整除。
 * 请你返回范围[low, high]中美丽整数的数目。
 * <p>
 * 示例 1：
 * 输入：low = 10, high = 20, k = 3
 * 输出：2
 * 解释：给定范围中有 2 个美丽数字：[12,18]
 * - 12 是美丽整数，因为它有 1 个奇数数位和 1 个偶数数位，而且可以被 k = 3 整除。
 * - 18 是美丽整数，因为它有 1 个奇数数位和 1 个偶数数位，而且可以被 k = 3 整除。
 * 以下是一些不是美丽整数的例子：
 * - 16 不是美丽整数，因为它不能被 k = 3 整除。
 * - 15 不是美丽整数，因为它的奇数数位和偶数数位的数目不相等。
 * 给定范围内总共有 2 个美丽整数。
 * <p>
 * 示例 2：
 * 输入：low = 1, high = 10, k = 1
 * 输出：1
 * 解释：给定范围中有 1 个美丽数字：[10]
 * - 10 是美丽整数，因为它有 1 个奇数数位和 1 个偶数数位，而且可以被 k = 1 整除。
 * 给定范围内总共有 1 个美丽整数。
 * <p>
 * 示例 3：
 * 输入：low = 5, high = 5, k = 2
 * 输出：0
 * 解释：给定范围中有 0 个美丽数字。
 * - 5 不是美丽整数，因为它的奇数数位和偶数数位的数目不相等。
 * <p>
 * 提示：
 * 0 < low <= high <= 10^9
 * 0 < k <= 20
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/number-of-beautiful-integers-in-the-range
 * @title: 范围中美丽整数的数目
 */
public class Solution {

    // @TestCaseGroup(start = 2, end = 2)
    static class Error {
        public static void main(String[] args) {
            IoUtil.testUtil(Error.class, "numberOfBeautifulIntegers", "in.txt");
        }


        public int numberOfBeautifulIntegers(int low, int high, int k) {
            System.out.println("test case");
            DFS dfs = new DFS(k);
            return dfs.f(high) - dfs.f(low - 1);
        }


        /**
         * 思路没错 但是解法有问题 越界了！！！
         * 换成 python 能过 ！！！
         */
        static class DFS {
            int k;
            char[] path;
            int[][] memo;

            public DFS(int k) {
                this.k = k;
            }


            public int f(int num) {
                if (num <= 9) {
                    return 0;
                }
                path = String.valueOf(num).toCharArray();
                this.memo = new int[path.length][1 << 10];
                for (int[] ints : this.memo) {
                    Arrays.fill(ints, -1);
                }
                return dfs(0, 0, 0, true, false);
            }

            public int dfs(int i, int s, int x, boolean isLimit, boolean isNum) {
                if (i == path.length) {
                    return s == 0 && x % k == 0 && isNum ? 1 : 0;
                }
                if (!isLimit && isNum && memo[i][x] != -1) {
                    return memo[i][x];
                }
                int res = 0;
                if (!isNum) {
                    res += dfs(i + 1, s, x, false, false);
                }
                int hi = isLimit ? path[i] - '0' : 9;
                for (int d = isNum ? 0 : 1; d <= hi; d++) {
                    res += dfs(i + 1, s + d % 2 * 2 - 1, (x * 10 + d), isLimit && d == hi, true);
                }
                if (!isLimit && isNum) {
                    memo[i][x] = res;
                }
                return res;
            }
        }
    }
}