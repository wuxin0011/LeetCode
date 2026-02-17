package template.str.hash;

import java.util.Random;

/**
 * 封装比较好的哈希推荐使用
 * @author: wuxin0011
 * @Description: https://leetcode.cn/problems/a7VOhD/submissions/699088004/
 */
// 字符串哈希模板开始
class StringHash {
    private long mod, base;
    private long[] h, p, rev_h;
    private static long base0, mod0;
    private static Random rand = new Random();
    int n;

    static {
        base0 = rand.nextInt(100 - 26 + 1) + 26;
        long modMin = 1000000007L;
        long modMax = (1L << 31) - 1;
        mod0 = modMin + (long) (rand.nextDouble() * (modMax - modMin + 1));
    }

    public StringHash(char[] s) {
        this.base = base0;
        this.mod = mod0;
        n = s.length;
        h = new long[n + 1];
        p = new long[n + 1];
        rev_h = new long[n + 1];

        h[0] = 0;
        p[0] = 1;
        for (int i = 1; i <= n; ++i) {
            h[i] = (h[i - 1] * base + s[i - 1]) % mod;
            p[i] = (p[i - 1] * base) % mod;
        }

        rev_h[0] = 0;
        for (int i = 1; i <= n; ++i) {
            rev_h[i] = (rev_h[i - 1] * base + s[n - i]) % mod;
        }
    }

    // [l,r]
    public long get(int l, int r) {
        return get(l, r, false);
    }
    public long get(int l, int r, boolean isRev) {
        if (isRev) {
            return (rev_h[n - l] - rev_h[n - r - 1] * p[r - l + 1] % mod + mod) % mod;
        } else {
            return (h[r + 1] - h[l] * p[r - l + 1] % mod + mod) % mod;
        }
    }

    public boolean isPalindrome(int l, int r) {
        if (l >= r) return true;
        return get(l, r, false) == get(l, r, true);
    }
}
// 字符串哈希模板结束
