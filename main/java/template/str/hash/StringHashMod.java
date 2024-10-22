package template.str.hash;

/**
 * @author: wuxin0011
 * @Description:
 */
public class StringHashMod {


    // 不带取模版本的

    /**
     * @link <a href="https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/submissions/574426236/">测试结果</a>
     */

    static class StringHash {
        long pow[], hash[], base = 499, mod = (long) 1e9 + 7;

        StringHash(String s) {
            int n = s.length();
            pow = new long[n + 1];
            hash = new long[n + 1];
            pow[0] = 1L;
            hash[0] = s.charAt(0);
            for (int i = 1; i < n; i++) {
                pow[i] = pow[i - 1] * base;
                hash[i] = (hash[i - 1] * base + (s.charAt(i)));
            }
        }

        // [L,R)
        long get(int l, int r) {
            long ans = hash[r - 1];
            if (l > 0) {
                ans = (ans - hash[l - 1] * pow[r - l]);
            }
            return ans;
        }
    }


    /**
     * @link <a title="测试链接" href="https://leetcode.cn/problems/palindromic-substrings/submissions/574548153/">提交测试</a>
     */
    static class StringHash2 {
        private static final int BASE = 499;
        private static final int MOD = (int)(1e9 + 7);

        static int MAXN = (int) (1e5 + 1), n;
        static long[] power = new long[MAXN + 2], pre = new long[MAXN + 2], suf = null;
        static char[] chars, reverseChars;


        static {
            power[0] = 1;
            for (int i = 1; i <= MAXN; i++) {
                power[i] = power[i - 1] * BASE % MOD;
            }
        }

        static void reverseString(char[] a) {
            int l = 0, r = a.length - 1;
            while (l < r) {
                char t = a[l];
                a[l] = a[r];
                a[r] = t;
                r--;
                l++;
            }
        }

        private static void calcHash(char[] a) {
            calcHash(a, false);
        }


        private static void heapCalcHash(long[] h, char[] chs) {
            for (int i = 0; i < n; i++) {
                h[i + 1] = (h[i] * BASE + chs[i]) % MOD;
            }
        }

        private static void calcHash(char[] a, boolean reverse) {
            chars = a;
            n = a.length;
            heapCalcHash(pre, a);
            if (reverse) {
                reverseString(a);
                if (suf == null) {
                    suf = new long[MAXN + 2];
                }
                heapCalcHash(suf, a);
            }
        }

        private static long getHash(long[] hash, int l, int r) {
            return (hash[r + 1] - hash[l] * power[r - l + 1] % MOD + MOD) % MOD;
        }


        // 获取 [l,r] 的 hash
        private static long getHash(int l, int r) {
            return getHash(pre, l, r);
        }


        // 检查[l,r]是否是回文
        static boolean isPalindromeString(int l, int r) {
            long preHash = getHash(pre, l, r);
            long subHash = getHash(suf, n - r - 1, n - l - 1);
            return preHash == subHash;
        }
    }


    public static void main(String[] args) {
        String s = "ababadddopo";
        StringHash2.calcHash(s.toCharArray(), true);
        boolean valid = true;
        next:
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                boolean hashCheck = StringHash2.isPalindromeString(i, j);
                boolean ok = check(s, i, j);
                if (ok != hashCheck) {
                    valid = false;
                    break next;
                }
                // System.out.printf("{%s,%s} = %s\n", i, j, s.substring(i, j + 1));
            }
        }
        System.out.println(valid ? "ok " : "error");
    }

    // [l,r]
    static boolean check(String s, int l, int r) {

        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }

        return true;
    }


}
