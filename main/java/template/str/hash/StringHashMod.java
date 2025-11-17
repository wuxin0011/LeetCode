package template.str.hash;

import code_generation.utils.RandomArrayUtils;

import java.util.Random;

/**
 * @author: wuxin0011
 * @Description: 单模hash 有出错概率 （不负责） 效率优与双模hash
 */
public class StringHashMod {


    // 不带取模版本的

    /**
     * @link <a href="https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/submissions/574426236/">测试结果</a>
     */


    static class StringHash {
        private long[] pow, hash;
        // 随机 base 防止被 hack ！
        private static final long BASE = (long) (1e9 + 7) + new Random().nextInt((int) 1e8);

        StringHash(String s) {
            int n = s.length();
            pow = new long[n + 1];
            hash = new long[n + 1];
            pow[0] = 1L;
            hash[0] = s.charAt(0);
            for (int i = 1; i < n; i++) {
                pow[i] = pow[i - 1] * BASE;
                hash[i] = (hash[i - 1] * BASE + (s.charAt(i)));
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

    // long 类型hash
    public static class StringHash2 {

        private static final long BASE = (long) (1e9 + 7) + new Random().nextInt((int) 1e8);
        private static final long MOD = (long)(1e9 + 7);

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

        // 默认不检查回文的
        private static void calcHash(char[] a) {
            calcHash(a, false);
        }
        // 是否构建检查回文的
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


        private static void heapCalcHash(long[] h, char[] chs) {
            for (int i = 0; i < n; i++) {
                h[i + 1] = (h[i] * BASE + chs[i]) % MOD;
            }
        }



        private static long getHash(long[] hash, int l, int r) {
            return (hash[r + 1] - hash[l] * power[r - l + 1] % MOD + MOD) % MOD;
        }


        // 获取 [l,r] 的 hash
        public static long getHash(int l, int r) {
            return getHash(pre, l, r);
        }


        // 检查[l,r]是否是回文
        static boolean isPalindromeString(int l, int r) {
            long preHash = getHash(pre, l, r);
            long subHash = getHash(suf, n - r - 1, n - l - 1);
            return preHash == subHash;
        }
    }


    // int 类型hash

    /**
     * @link <a title="测试链接" href="https://leetcode.cn/problems/palindromic-substrings/submissions/575085385/">提交测试</a>
     */
    static class StringHash3 {
        private static final long BASE = (long)(1e9 + 7) + new Random().nextInt((int) 1e8);
        private static final long MOD = (long)1e9 + 7;

        static int MAXN = (int) (1e5 + 1), n;
        static int[] power = new int[MAXN + 2], pre = new int[MAXN + 2], suf = null;
        static char[] chars, reverseChars;


        static {
            power[0] = 1;
            for (int i = 1; i <= MAXN; i++) {
                power[i] = (int) (power[i - 1] * 1L * BASE % MOD);
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


        public static void heapCalcHash(int[] h, char[] chs) {
            for (int i = 0; i < n; i++) {
                h[i + 1] = (int) ((h[i] * 1L * BASE + chs[i]) % MOD);
            }
        }

        public static void calcHash(char[] a, boolean reverse) {
            chars = a;
            n = a.length;
            heapCalcHash(pre, a);
            if (reverse) {
                reverseString(a);
                if (suf == null) {
                    suf = new int[MAXN + 2];
                }
                heapCalcHash(suf, a);
            }
        }

        public static int getHash(int[] hash, int l, int r) {
            return (int) (((hash[r + 1] - hash[l] * 1L * power[r - l + 1]) % MOD + MOD) % MOD);
        }


        // 获取 [l,r] 的 hash
        public static int getHash(int l, int r) {
            return getHash(pre, l, r);
        }


        // 检查[l,r]是否是回文
        public static boolean isPalindromeString(int l, int r) {
            int preHash = getHash(pre, l, r);
            int subHash = getHash(suf, n - r - 1, n - l - 1);
            return preHash == subHash;
        }
    }


    public static void main(String[] args) {
        test02();
//         test03();
    }


    static void test02() {
        System.out.println("stringhash 2");
        boolean ok = true;
        long t1 = 0, t2 = 0, time = 0;
        String errorStr = "";
        next:
        for (int T = 100; T > 0; T--) {
            String s = new String(RandomArrayUtils.randomCharArray(100, (int) 1e4));
            StringHash2.calcHash(s.toCharArray(), true);
            long c1 = 0, c2 = 0;
            time++;

            for (int i = 0; i < s.length(); i++) {
                for (int j = i + 1; j < s.length(); j++) {
                    long st = System.currentTimeMillis();
                    boolean checkResult = check(s, i, j);
                    long ed = System.currentTimeMillis();
                    boolean manacherQueryResult = StringHash2.isPalindromeString(i, j);
                    long e2 = System.currentTimeMillis();
                    if (checkResult != manacherQueryResult) {
                        ok = false;
                        errorStr = s;
                        break next;
                    }

                    c1 += (ed - st);
                    c2 += (e2 - ed);
                }
            }
            t1 += c1;
            t2 += c2;
        }
        System.out.println(ok ? "ok" : "fail");
        if (ok) {
            System.out.println("暴力耗时       : " + (t1 * 1.0 / 100) + ":ms");
            System.out.println("string hash  : " + (t2 * 1.0 / 100) + ":ms");
        } else {
            System.out.println(time);
        }
    }


    static void test03() {
        System.out.println("stringhash 3");
        boolean ok = true;
        long t1 = 0, t2 = 0, time = 0;
        String errorStr = "";
        next:
        for (int T = 100; T > 0; T--) {
            String s = new String(RandomArrayUtils.randomCharArray(100, (int) 1e4));
            StringHash3.calcHash(s.toCharArray(), true);
            long c1 = 0, c2 = 0;
            time++;

            for (int i = 0; i < s.length(); i++) {
                for (int j = i + 1; j < s.length(); j++) {
                    long st = System.currentTimeMillis();
                    boolean checkResult = check(s, i, j);
                    long ed = System.currentTimeMillis();
                    boolean manacherQueryResult = StringHash3.isPalindromeString(i, j);
                    long e2 = System.currentTimeMillis();
                    if (checkResult != manacherQueryResult) {
                        ok = false;
                        errorStr = s;
                        break next;
                    }

                    c1 += (ed - st);
                    c2 += (e2 - ed);
                }
            }
            t1 += c1;
            t2 += c2;
        }
        System.out.println(ok ? "ok" : "fail");
        if (ok) {
            System.out.println("暴力耗时       : " + (t1 * 1.0 / 100) + ":ms");
            System.out.println("string hash  : " + (t2 * 1.0 / 100) + ":ms");
        } else {
            System.out.println(time);
        }
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
