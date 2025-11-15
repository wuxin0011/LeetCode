package leetcode.contest.weekly.w_400.w_463;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-463/problems/xor-after-range-multiplication-queries-ii">区间乘法查询后的异或 II</a>
 * @title: 区间乘法查询后的异或 II
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "xorAfterQueries", "D.txt");
    }

    private static final int MOD = (int) 1e9 + 7;


    public int xorAfterQueries(int[] a, int[][] queries) {
        int n = a.length;
        int B = (int) Math.sqrt(queries.length);
        //Object[] diff = new Object[B];
        int[][] diff = new int[B][];
        for (int[] query : queries) {
            int l = query[0], r = query[1], k = query[2], v = query[3];
            if (k < B) {
                if (diff[k] == null) {
                    diff[k] = new int[n + k + 1];
                    Arrays.fill(diff[k], 1);
                }
                int[] cur = diff[k];
                cur[l] = (int) (1L * cur[l] * v % MOD);
                r = r - (r - l) % k + k;
                cur[r] = (int) (1L * cur[r] * inv(v) % MOD);
            } else {
                for (int i = l; i <= r; i+=k) {
                    a[i] = (int) ((a[i] * 1L * v) % MOD);
                }
            }
        }
        for (int k = 0; k < B; k++) {
            if (diff[k] == null) continue;
            int[] d = diff[k];
            for (int st = 0; st < k; st++) {
                int mul = 1;
                for (int i = st; i < n; i += k) {
                    mul = (int) (1L * mul * d[i] % MOD);
                    a[i] = (int) (1L * a[i] * mul % MOD);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans ^= a[i];
        }
        return ans;
    }

    public static long pow(long x, int n) {
        long ans = 1;
        for (; n > 0; n >>= 1) {
            if (n % 2 == 1) {
                ans *= x;
                ans %= MOD;
            }
            x *= x;
            x %= MOD;
        }
        return ans;
    }

    public static long inv(long x) {
        return pow(x, MOD - 2);
    }


}