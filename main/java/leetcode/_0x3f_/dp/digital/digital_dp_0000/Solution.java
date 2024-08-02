package leetcode._0x3f_.dp.digital.digital_dp_0000;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 233. 数字 1 的个数
 * <p>
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 * <p>
 * 示例 1：
 * 输入：n = 13
 * 输出：6
 * <p>
 * 示例 2：
 * 输入：n = 0
 * 输出：0
 * <p>
 * 提示：
 * 0 <= n <= 10^9
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/number-of-digit-one
 * @title: number-of-digit-one
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "countDigitOne", "in.txt");
    }


    char[] path;
    int n;
    // 11 12 13 10 1

    public int countDigitOne(int n) {
        this.path = String.valueOf(n).toCharArray();
        this.n = path.length;
        this.memo = new int[this.n][this.n];
        for (int i = 0; i < this.n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(0, 0, true);
    }

    int[][] memo;

    public int dfs(int i, int s, boolean isLimit) {
        if (i == n) {
            return s;
        }
        if (!isLimit && memo[i][s] != -1) {
            return memo[i][s];
        }
        int cnt = 0;
        int up = isLimit ? path[i] - '0' : 9;
        for (int d = 0; d <= up; d++) {
            cnt += dfs(i + 1, s + (d == 1 ? 1 : 0), isLimit && d == up);
        }
        if(!isLimit) {
            memo[i][s] = cnt;
        }
        return cnt;
    }


}