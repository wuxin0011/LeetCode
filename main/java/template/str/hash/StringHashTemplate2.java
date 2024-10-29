package template.str.hash;

/**
 * @author: wuxin0011
 * @Description: 双模hash
 */
public class StringHashTemplate2 {


    public static class StringHash {
        char[] str;
        long p, mod;

        int n;
        long[] pre; // hash前缀和
        long[] pow; // p的幂次

        public StringHash(String s, int p, long mod) {
            this.str = s.toCharArray();
            this.p = p;
            this.mod = mod;

            this.n = str.length;
            pre = new long[n + 1];
            pow = new long[n + 1];

            pow[0] = 1;
            for (int i = 1; i <= n; i++) {
                pow[i] = pow[i - 1] * p % mod;
            }
            for (int i = 0; i < str.length; i++) {
                pre[i + 1] = (pre[i] * p % mod + str[i]) % mod;
            }
        }

        // [l,r]
        long query(int l, int r) {
            long res = pre[r + 1] - pre[l] * pow[r - l + 1] % mod;
            return (res % mod + mod) % mod;
        }

        long rotate(int l) {
            if (l < 0 || l >= str.length - 1) {
                return query(0, str.length - 1);
            } else {
                long h1 = query(0, l);
                long h2 = query(l + 1, str.length - 1);
                return (h2 * pow[l + 1] % mod + h1) % mod;
            }
        }

        static long evaluate(String s, int p, long mod) {
            long h = 0;
            for (char c : s.toCharArray()) {
                h = (h * p % mod + c) % mod;
            }
            return h;
        }
    }
}
