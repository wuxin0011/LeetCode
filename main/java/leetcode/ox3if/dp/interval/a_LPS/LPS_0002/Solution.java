package leetcode.ox3if.dp.interval.a_LPS.LPS_0002;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 1312. 让字符串成为回文串的最少插入次数
 * <p>
 * 给你一个字符串s，每一次操作你都可以在字符串的任意位置插入任意字符。
 * 请你返回让s成为回文串的最少操作次数。
 * 「回文串」是正读和反读都相同的字符串。
 * <p>
 * 示例 1：
 * 输入：s = "zzazz"
 * 输出：0
 * 解释：字符串 "zzazz" 已经是回文串了，所以不需要做任何插入操作。
 * <p>
 * 示例 2：
 * 输入：s = "mbadm"
 * 输出：2
 * 解释：字符串可变为 "mbdadbm" 或者 "mdbabdm" 。
 * <p>
 * 示例 3：
 * 输入：s = "leetcode"
 * 输出：5
 * 解释：插入 5 个字符后字符串变为 "leetcodocteel" 。
 * <p>
 * 提示：
 * 1 <= s.length <= 500
 * s中所有字符都是小写字母。
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/minimum-insertion-steps-to-make-a-string-palindrome
 * @title: 让字符串成为回文串的最少插入次数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "minInsertions", "in.txt");
    }


    public int minInsertions(String s) {
        this.s = s.toCharArray();
        int n = s.length();
        memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(0, n - 1);
    }

    int[][] memo;

    char[] s;

    public int dfs(int l, int r) {
        if (l >= r) {
            return 0;
        }
        if (memo[l][r] != -1) {
            return memo[l][r];
        }
        int res = 0;
        if (s[l] == s[r]) {
            res = dfs(l + 1, r - 1);
        } else {
            // dfs(l + 1, r-1) 包含在 Math.max(dfs(l + 1, r), dfs(l, r - 1)) 中
            res = Math.min(dfs(l + 1, r), dfs(l, r - 1)) + 1;
        }
        memo[l][r] = res;
        return res;
    }


}