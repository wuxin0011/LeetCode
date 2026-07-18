package leetcode.contest.biweekly.bi_100.bi_187;

import code_generation.utils.IoUtil;

import java.util.Arrays;
import java.util.List;

/**
 * @author: qitongwei
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/biweekly-contest-187/problems/minimum-cost-to-convert-string-iii">转换字符串的最小成本 III</a>
 * @title: 转换字符串的最小成本 III
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "minCost", "D.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    static int inf = 0x3fffffff;
    public int minCost(String S, String T, List<List<String>> rules, int[] costs) {
        char[] s = S.toCharArray();
        int n = s.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, inf);
        dp[0] = 0;
        int m = rules.size();
        Info[] infos = new Info[m];
        for (int i = 0; i < m; i++) {
            infos[i] = new Info(rules.get(i).get(0).length(),countStars(rules.get(i).get(0)),rules.get(i).get(0),rules.get(i).get(1),costs[i]);
        }
        for (int i = 0; i < n; i++) {
            if (dp[i] == inf) continue;
            for (int r = 0; r < m; r++) {
                int len = infos[r].len;
                if (i + len > n) continue;
                boolean ok = true;
                for (int j = 0; j < len; j++) {
                    char c0 = infos[r].p.charAt(j);
                    if (c0 != '*' && c0 != s[i + j]) {
                        ok = false;
                        break;
                    }
                }
                if (!ok) continue;
                if (!infos[r].rs.equals(T.substring(i, i + len))) {
                    continue;
                }
                int c0 = dp[i] + infos[r].cost + infos[r].star;
                if (c0 < dp[i + len]) {
                    dp[i + len] = c0;
                }
            }
        }

        return dp[n] == inf ? -1 :dp[n];
    }

    private int countStars(String s) {
        int count = 0;
        for(int i = 0;i < s.length();i++){
            char c = s.charAt(i);
            if (c == '*') count++;
        }
        return count;
    }

    public static class Info {
        int len;
        int star;
        int cost;
        String p;
        String rs;
        Info(int len,int star,String p,String rs,int cost) {
            this.len = len;
            this.star = star;
            this.p = p;
            this.rs = rs;
            this.cost = cost;
        }
    }

}