package leetcode.contest.weekly.w_400.w_456;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-456/problems/partition-array-to-minimize-xor">划分数组得到最小 XOR</a>
 * @title: 划分数组得到最小 XOR
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "minXor", "C.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public int minXor(int[] a, int k) {
        int n = a.length;
        Integer[][] memo = new Integer[n + 1][k + 1];
        int[] xor = new int[n];
        int[] pre = new int[n];
        for (int i = 0; i < n; i++) {
            xor[i] = i == 0 ? a[i] : xor[i - 1] ^ a[i];
            pre[i] = i == 0 ? a[i] : Math.max(pre[i - 1], a[i]);
        }
        class Info {
            public int dfs(int i, int j) {
                if (i + 1 == j) return pre[i];
                if (j == 1) return xor[i];
                if (memo[i][j] != null) return memo[i][j];
                int ans = Integer.MAX_VALUE / 2;
                int c = 0;
                for (int k = i; k >= j - 1; k--) {
                    c ^= a[k];
                    ans = Math.min(ans, Math.max(c, dfs(k - 1, j - 1)));
                }
                return memo[i][j] = ans;
            }
        }
        return new Info().dfs(n - 1, k);
    }


}