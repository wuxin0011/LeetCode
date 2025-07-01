package leetcode.contest.weekly.w_400.w_456;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-456/problems/longest-common-prefix-between-adjacent-strings-after-removals">相邻字符串之间的最长公共前缀</a>
 * @title: 相邻字符串之间的最长公共前缀
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "longestCommonPrefix", "B.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public int[] longestCommonPrefix(String[] words) {
        int n = words.length;
        int[] pre = new int[n];
        int[] suf = new int[n];
        for (int i = 1; i < n; i++) {
            pre[i] = Math.max(pre[i-1],LCP(words[i], words[i - 1]));
        }
        for (int i = n - 2; i >= 0; i--) {
            suf[i] = Math.max(suf[i+1],LCP(words[i], words[i + 1]));
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int v = 0;
            if (i - 1 >= 0) {
                v = Math.max(v, pre[i - 1]);
            }
            if (i + 1 < n) {
                v = Math.max(v, suf[i + 1]);
            }
            if (i + 1 < n && i > 0) {
                v = Math.max(v, LCP(words[i - 1], words[i + 1]));
            }
            ans[i] = v;
        }
        return ans;
    }

    public static int LCP(String s1, String s2) {
        int n = Math.min(s1.length(), s2.length());
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) return i;
        }
        return n;
    }


}