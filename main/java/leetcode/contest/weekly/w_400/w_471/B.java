package leetcode.contest.weekly.w_400.w_471;

import code_generation.utils.IoUtil;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-471/problems/longest-balanced-substring-i">最长的平衡子串 I</a>
 * @title: 最长的平衡子串 I
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class,"longestBalanced","B.txt");
    }
    private static final int MOD = (int)1e9 + 7; 

    public int longestBalanced(String s) {
        int[] a = s.chars().map(x -> x - 'a').toArray();
        int n = a.length;
        int N = 26;
        int[][] sums = new int[n + 1][N];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < N; k++) {
                sums[i + 1][k] = sums[i][k];
            }
            sums[i + 1][a[i]]++;
        }

        int ans = 1;
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j > i; j--) {
                if (j - i + 1 <= ans) continue;
                int flag = -1;
                boolean ok = true;
                for (int k = 0; k < N; k++) {
                    int v = sums[j + 1][k] - sums[i][k];
                    if (v == 0) continue;
                    if (flag == -1) flag = v;
                    if (flag != v) {
                        ok = false;
                        break;
                    }
                }
                if (ok) ans = Math.max(ans, j - i + 1);
            }
        }
        return ans;
	}

  

}