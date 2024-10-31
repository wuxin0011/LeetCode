package leetcode.contest.weekly.w_400.w_421.d;

import code_generation.utils.IoUtil;

import java.util.List;

/**
 * @author: agitated-curranfnd
 * @Description: https://leetcode.cn/submissions/detail/577100345/
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-421/problems/total-characters-in-string-after-transformations-ii">字符串转换后的长度 II</a>
 * @title: 字符串转换后的长度 II
 */
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "lengthAfterTransformations", "D.txt");
    }

    static int mod = (int) 1e9 + 7;

    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        long[] cnt = new long[26];
        for (int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        // 初始状态
        int[][] f0 = new int[26][1];
        for (int i = 0; i < 26; i++) {
            f0[i][0] = 1;
        }

        // 构造线性关键矩阵
        int[][] m = new int[26][26];
        for (int i = 0; i < 26; i++) {
            int c = nums.get(i);
            for (int j = i + 1; j <= i + c; j++) {
                m[i][j % 26] = 1;
            }
        }

        // 矩阵快速幂
        m = pow(m, f0, t, mod);
        long ans = 0;
        for (int i = 0; i < 26; i++) {
            ans += m[i][0] * cnt[i];
            ans %= mod;
        }
        return (int) (ans % mod);

    }


    public static int[][] pow(int[][] a, int[][] b, int n, int mod) {
        int[][] res = b;
        while (n > 0) {
            if ((n & 1) > 0) {
                res = mul(a, res, mod);
            }
            a = mul(a, a, mod);
            n >>= 1;
        }
        return res;
    }


    public static int[][] mul(int[][] a, int[][] b, int mod) {
        int n = a.length;
        int m = b[0].length;
        int k = a[0].length;
        int[][] ans = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int c = 0; c < k; c++) {
                    ans[i][j] = (int) ((ans[i][j] + a[i][c] * 1L * b[c][j]) % mod);
                }
            }
        }
        return ans;
    }


}