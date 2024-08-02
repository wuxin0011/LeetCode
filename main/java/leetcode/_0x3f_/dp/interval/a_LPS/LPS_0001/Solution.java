package leetcode._0x3f_.dp.interval.a_LPS.LPS_0001;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 730. 统计不同回文子序列
 * <p>
 * 给你一个字符串 s ，返回 s中不同的非空回文子序列个数 。
 * 由于答案可能很大，请返回对 10^9 + 7 取余 的结果。
 * 字符串的子序列可以经由字符串删除 0 个或多个字符获得。
 * 如果一个序列与它反转后的序列一致，那么它是回文序列。
 * 如果存在某个 i , 满足ai!= bi，则两个序列a1, a2, ...和b1, b2, ...不同。
 * <p>
 * 示例 1：
 * 输入：s = 'bccb'
 * 输出：6
 * 解释：6 个不同的非空回文子字符序列分别为：'b', 'c', 'bb', 'cc', 'bcb', 'bccb'。
 * 注意：'bcb' 虽然出现两次但仅计数一次。
 * <p>
 * 示例 2：
 * 输入：s = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
 * 输出：10^4860361
 * 解释：共有 3104860382 个不同的非空回文子序列，10^4860361 是对 10^9 + 7 取余后的值。
 * <p>
 * 提示：
 * 1 <= s.length <= 1000
 * s[i]仅包含'a','b','c'或'd'
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/count-different-palindromic-subsequences
 * @title: 统计不同回文子序列
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "countPalindromicSubsequences", "in.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public int countPalindromicSubsequences(String s) {
        this.s = s.toCharArray();
        int n = s.length();
        memo = new long[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        long v = dfs(0, n - 1);
        return (int)v;
    }


    long[][] memo;
    char[] s;

    public long dfs(int l, int r) {
        if (l > r) {
            return 0;
        }
        long res = 0;
        // TODO ...
        return res;

    }


}