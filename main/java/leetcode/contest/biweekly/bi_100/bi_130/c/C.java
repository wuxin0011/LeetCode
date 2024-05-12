package leetcode.contest.biweekly.bi_100.bi_130.c;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 100289. 分割字符频率相等的最少子字符串
 * <p>
 * <p>
 * 给你一个字符串s，你需要将它分割成一个或者更多的平衡子字符串。
 * 比方说，s == "ababcc"那么("abab", "c", "c")，("ab", "abc", "c")和("ababcc")都是合法分割，但是("a", "bab", "cc")，("aba", "bc", "c")和("ab", "abcc")不是，不平衡的子字符串用粗体表示。
 * 请你返回 s最少 能分割成多少个平衡子字符串。
 * 注意：一个 平衡字符串指的是字符串中所有字符出现的次数都相同。
 * <p>
 * 示例 1：
 * 输入：s = "fabccddg"
 * 输出：3
 * 解释：
 * 我们可以将 s分割成 3 个子字符串：("fab, "ccdd", "g")或者("fabc", "cd", "dg")。
 * <p>
 * 示例 2：
 * 输入：s = "abababaccddb"
 * 输出：2
 * 解释：
 * 我们可以将s分割成 2 个子字符串：("abab", "abaccddb")。
 * <p>
 * 提示：
 * 1 <= s.length <= 1000
 * s只包含小写英文字母。
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-130/problems/minimum-substring-partition-of-equal-character-frequency
 * @title: 分割字符频率相等的最少子字符串
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "minimumSubstringsInPartition", "C.txt");
    }


    public int minimumSubstringsInPartition(String s) {
        this.cs = s.toCharArray();
        this.dp = new int[cs.length];
        this.n = cs.length;
        Arrays.fill(dp, Integer.MAX_VALUE);
        return dfs(0);
    }

    int[] dp;
    char[] cs;
    int n;

    public int dfs(int id) {
        if (id == n) {
            return 0;
        }
        if (id == n - 1) { // 与一般 DP 不同 最后一个字符只能单独划分
            dp[id] = 1;
            return 1;
        }
        if (dp[id] != Integer.MAX_VALUE) {
            return dp[id];
        }
        int res = Integer.MAX_VALUE;
        int type = 0;
        int[] cnt = new int[26];
        int l = 0;
        int mxSize = 0; // 出现最大频率
        for (int i = id; i < n; i++) {
            int v = cs[i] - 'a';
            if (cnt[v] == 0) {
                type++; // 种类
            }
            l++;
            cnt[v]++;
            mxSize = Math.max(cnt[v], mxSize);
            if (type * mxSize == l) { // 是一个平衡字符串 => 长度 = 最大频率 * 种类
                res = Math.min(res, dfs(i + 1) + 1); // 找到一种方案
            }
        }
        dp[id] = res;
        return res;
    }

}