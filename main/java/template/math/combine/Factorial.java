package template.math.combine;

/**
 * @author: wuxin0011
 * @Description: 组合数学 逆元
 */
public class Factorial {
    private final long[] f;
    private final long[] g;
    private final int mod;

    public Factorial(int N, int mod) {
        N++;
        this.mod = mod;
        f = new long[N];
        g = new long[N];
        f[0] = 1;
        for (int i = 1; i < N; i++) {
            f[i] = f[i - 1] * i % mod;
        }
        g[N - 1] = pow(f[N - 1], mod - 2);
        for (int i = N - 2; i >= 0; i--) {
            g[i] =  g[i + 1] * (i + 1) % mod;
        }
    }

    // 阶乘
    public long fac(int n) {
        return f[n];
    }

    // 阶乘的逆元
    public long fac_inv(int n) {
        return g[n];
    }

    // 组合数
    public long comb(int n, int m) {
        if (n < m || m < 0 || n < 0) {
            return 0;
        }
        return (f[n] * g[m] % mod * g[n - m]) % mod;
    }

    // 排列数
    public long permu(int n, int m) {
        if (n < m || m < 0 || n < 0) {
            return 0;
        }
        return f[n] * g[n - m] % mod;
    }

    // 卡特兰数
    public long catalan(int n) {
        return (comb(2 * n, n) - comb(2 * n, n - 1) + mod) % mod;
    }

    // 逆元
    public long inv(int n) {
        return f[n - 1] * g[n] % mod;
    }

    private long pow(long a, int n) {
        long res = 1L;
        for (; n > 0; n /= 2) {
            if ((n % 2) == 1) {
                res = res * a % mod;
            }
            a = a * a % mod;
        }
        return res;
    }
}