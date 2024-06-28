package leetcode.ox3if.dp.interval.a_LPS.LPS_0003;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 *
 * 1771. 由子序列构造的最长回文串的长度
 *
 * 给你两个字符串 word1 和 word2 ，请你按下述方法构造一个字符串：
 * 	从 word1 中选出某个 非空 子序列 subsequence1 。
 * 	从 word2 中选出某个 非空 子序列 subsequence2 。
 * 	连接两个子序列 subsequence1 + subsequence2 ，得到字符串。
 * 返回可按上述方法构造的最长 回文串 的 长度 。如果无法构造回文串，返回 0 。
 * 字符串 s 的一个 子序列 是通过从 s 中删除一些（也可能不删除）字符而不更改其余字符的顺序生成的字符串。
 * 回文串 是正着读和反着读结果一致的字符串。
 *
 * 示例 1：
 * 输入：word1 = "cacb", word2 = "cbba"
 * 输出：5
 * 解释：从 word1 中选出 "ab" ，从 word2 中选出 "cba" ，得到回文串 "abcba" 。
 *
 * 示例 2：
 * 输入：word1 = "ab", word2 = "ab"
 * 输出：3
 * 解释：从 word1 中选出 "ab" ，从 word2 中选出 "a" ，得到回文串 "aba" 。
 *
 * 示例 3：
 * 输入：word1 = "aa", word2 = "bb"
 * 输出：0
 * 解释：无法按题面所述方法构造回文串，所以返回 0 。
 *
 * 提示：
 * 	1 <= word1.length, word2.length <= 1000
 * 	word1 和 word2 由小写英文字母组成
 *
 *
 * @author: wuxin0011
 * @Description: 本题可最长回文子序列不同是 需要选择两个非空子序列！！！ 见这题： https://leetcode.cn/problems/longest-palindromic-subsequence/
 * @url: https://leetcode.cn/problems/maximize-palindrome-length-from-subsequences
 * @title: 由子序列构造的最长回文串的长度
 */
public class Solution {


    /**
     * 递推版本
     */
    static class S0 {
        public static void main(String[] args) {
            IoUtil.testUtil(S0.class, "longestPalindrome", "in.txt");
        }


        public int longestPalindrome(String word1, String word2) {
            char[] s = (word1 + word2).toCharArray();
            int a = word1.length();
            int ans = 0;
            int n = s.length;
            int[][] f = new int[n][n];

            // 从记忆化搜索看 i 依赖于 i+1 j 依赖于 j - 1
            // 因此 f[i][j] = f[i+1][j-1]
            // i 从 n -> 0 枚举
            // j 从 i + 1 枚举 （ i == j 情况下 为 1)
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i; j < n; j++) {
                    if (i == j) {
                        f[i][j] = 1;
                    } else {
                        if (s[i] == s[j]) {
                            f[i][j] = f[i + 1][j - 1] + 2;
                            if (i < a && j >= a) {
                                ans = Math.max(f[i][j], ans);
                            }
                        } else {
                            f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
                        }
                    }

                }
            }
            return ans;
        }


    }


    /**
     * 记忆化搜索
     */
    static class MemoSolution1 {
        public static void main(String[] args) {
            IoUtil.testUtil(MemoSolution1.class, "longestPalindrome", "in.txt");
        }

        int ans = 0;


        char[] s;
        int a;
        int[][] memo;

        public int longestPalindrome(String word1, String word2) {
            s = (word1 + word2).toCharArray();
            a = word1.length();
            int n = s.length;
            memo = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(memo[i], -1);
            }
            dfs(0, n - 1);
            return ans;
        }


        public int dfs(int l, int r) {
            if (l > r) {
                return 0;
            }
            if (memo[l][r] != -1) {
                return memo[l][r];
            }
            int res = 0;
            if (l == r) {
                res = 1;
            } else {
                if (s[l] == s[r]) {
                    res = dfs(l + 1, r - 1) + 2;
                    if (l < a && r >= a) {
                        // 全局更新
                        ans = Math.max(ans, res);
                    }
                } else {
                    res = Math.max(dfs(l + 1, r), dfs(l, r - 1));
                }
            }
            memo[l][r] = res;
            return res;
        }
    }


    /**
     * 记忆化搜索
     */
    static class MemoSolution {
        public static void main(String[] args) {
            IoUtil.testUtil(MemoSolution.class, "longestPalindrome", "in.txt");
        }


        char[] s;
        int a;
        int[][][] memo;

        public int longestPalindrome(String word1, String word2) {
            s = (word1 + word2).toCharArray();
            a = word1.length();
            int n = s.length;
            memo = new int[n][n][2];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    Arrays.fill(memo[i][j], -1);
                }
            }
            return dfs(0, n - 1, 0);
        }


        public int dfs(int l, int r, int ok) {
            if (l > r) {
                return 0;
            }
            if (memo[l][r][ok] != -1) {
                return memo[l][r][ok];
            }
            int res = 0;
            // 不能选了
            if (ok == 0 && (l >= a || r < a)) {
                // ignore
            } else if (l == r) {
                res = 1;
            } else {
                if (s[l] == s[r]) {
                    res = dfs(l + 1, r - 1, 1) + 2;
                } else {
                    res = Math.max(dfs(l + 1, r, ok), dfs(l, r - 1, ok));
                }
            }
            memo[l][r][ok] = res;
            return res;
        }
    }


}