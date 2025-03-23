package template.math.number;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description: 乘法快速幂 + 矩阵快速幂
 * @see leetcode.contest.weekly.w_400.w_421.d.D  取模版本的矩阵快速幂
 */

@SuppressWarnings("all")
public class FastPower {

    // 矩阵快速幂
    // 要求矩阵m是正方形矩阵
    static class MatrixFastPower {

        public static int[][] power(int[][] m, int p) {
            int n = m.length;
            // 对角线全是1、剩下数字都是0的正方形矩阵，称为单位矩阵
            // 相当于正方形矩阵中的1，矩阵a * 单位矩阵 = 矩阵a
            int[][] ans = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(ans[i], 1);
            }
            for (; p != 0; p >>= 1) {
                if ((p & 1) != 0) {
                    ans = multiply(ans, m);
                }
                m = multiply(m, m);
            }
            return ans;
        }

        // 矩阵相乘
        // a的列数一定要等于b的行数
        public static int[][] multiply(int[][] a, int[][] b) {
            int n = a.length;
            int m = b[0].length;
            int k = a[0].length;
            int[][] ans = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    for (int c = 0; c < k; c++) {
                        ans[i][j] += a[i][c] * b[c][j];
                    }
                }
            }
            return ans;
        }
    }


    /**
     * 乘法快速幂
     */
    static class MultiplicationFastPower {
        /**
         * 快速幂
         *
         * @param x val
         * @param n 幂的次数
         * @return result
         */
        public static long pow(int x, int n) {
            return pow(x, n, (int) 1e9 + 7);
        }


        /**
         * 快速幂
         *
         * @param x   val
         * @param n   幂次数
         * @param mod 取mod
         * @return x ^ n
         */
        public static long pow(int x, int n, int mod) {
            // 参数不要传反了！！！
            boolean isNeg = n < 0;
            if (isNeg) n = -n;
            long res = 1;
            long v = x;
            while (n > 0) {
                if ((n & 1) == 1) {
                    res = res * v % mod;
                }
                v = v * v % mod;
                n >>= 1;
            }
            return isNeg ? 1 / res : res;
        }

    }


    /**
     * 取模版本的矩阵快速幂
     * @see leetcode.contest.weekly.w_400.w_421.d.D
     */
    static class FastMatMod {
        
        // 注意b是结果矩阵
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


        // 注意 mat1.col == mat2.row
        public static int[][] mul(int[][] mat1, int[][] mat2, int mod) {
            int n = mat1.length;
            int m = mat2[0].length;
            int k = mat1[0].length;
            int[][] ans = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    for (int c = 0; c < k; c++) {
                        ans[i][j] = (int) ((ans[i][j] + mat1[i][c] * 1L * mat2[c][j]) % mod);
                    }
                }
            }
            return ans;
        }
    }


    static class LLM {
        static long M9 = 998244353L, M1 = 1000_000_007L;
        static long M = M1; // TODO: choose suitable M.
        static int MAXN = 500300; // TODO: resize max N.
        static long[] cacheStepmul = new long[MAXN];
        static long[] cacheStepmulInv = new long[MAXN];
        static long[] exp2 = new long[MAXN];
        long num = 0;

        LLM() {
            this(0);
        }

        LLM(long num) {
            if (num >= 0) {
                this.num = num % M;
            } else {
                this.num = M - (-num) % M;
            }
        }

        LLM(LLM bm) {
            this(bm.num);
        }

        @Override
        public String toString() {
            return "LLM:" + num;
        }

        LLM add(long x) {
            if (x > 0) {
                num = (num + x % M) % M;
            } else if (x < 0) {
                num = (num - (-x) % M + M) % M;
            }
            return this;
        }

        LLM add(LLM bm) {
            return add(bm.num);
        }

        LLM sub(long x) {
            return add(-x);
        }

        LLM sub(LLM bm) {
            return add(-bm.num);
        }

        LLM mul(long x) {
            num = num * (x % M) % M;
            return this;
        }

        LLM mul(LLM bm) {
            return mul(bm.num);
        }

        LLM div(long x) {
            return mul(exp(x, M - 2));
        }

        LLM div(LLM bm) {
            return div(bm.num);
        }

        LLM exp(long p) {
            long ret = 1L, base = num % M;
            for (int i = 0; i <= 60 && p > 0; ++i) { //1<<30=1073741824
                if ((p & (1L << i)) > 0) {
                    ret = ret * base % M;
                    p ^= (1L << i);
                }
                base = base * base % M;
            }
            if (p != 0) throw new RuntimeException("p should reduced to 0 in exp.");
            num = ret;
            return this;
        }

        LLM exp(LLM bm) {
            return exp(bm.num);
        }

        static LLM exp(long x, long p) {
            return new LLM(x).exp(p);
        }

        static LLM C(long n, long m) {
            if (n < 0 || m < 0) throw new RuntimeException("n/m cannot be negative in C(n,m).");
            if (n < m) throw new RuntimeException("n is less than m in C(n,m).");
            if (n == 0) return new LLM(1);
            if (m == 0 || m == n) return new LLM(1);
            if (n < cacheStepmul.length) {
                // C(n,m)=n!/(n-m)!/m!
                return new LLM(stepmul(n)).mul(stepmulInv(n - m)).mul(stepmulInv(m));
            }
            // NOTE: O(m) complexity when n is large.
            LLM r = new LLM(1);
            long val = n;
            for (long i = 0; i < m; ++i) r.mul(val--);
            r.mul(stepmulInv(m));
            return r;
        }

        static long stepmul(long n) { // n!
            if (n < 0) throw new RuntimeException("n cannot be negative in stepmul.");
            if (n >= cacheStepmul.length) throw new RuntimeException("overflow cacheSm size.");
            if (cacheStepmul[0] == 0) {
                cacheStepmul[0] = 1;
                cacheStepmulInv[0] = exp(cacheStepmul[0], M - 2).num;
                for (int i = 1; i < cacheStepmul.length; ++i) {
                    cacheStepmul[i] = cacheStepmul[i - 1] * i % M;
                    cacheStepmulInv[i] = exp(cacheStepmul[i], M - 2).num;
                }
            }
            return cacheStepmul[(int) n];
        }

        static long stepmulInv(long n) {
            if (cacheStepmulInv[1] == 0) stepmul(2);
            return cacheStepmulInv[(int) n];
        }

        static long exp2(long n) { // 2^n
            if (n < 0) throw new RuntimeException("n cannot be negative in exp2.");
            if (n >= exp2.length) throw new RuntimeException("overflow exp2 size.");
            if (exp2[0] == 0) {
                exp2[0] = 1;
                for (int i = 1; i < exp2.length; ++i)
                    exp2[i] = exp2[i - 1] * 2 % M;
            }
            return exp2[(int) n];
        }
    }


    public class Mat {
        private long[][] m;
        private int row, col;
        private long mod;

        public Mat(long[][] m0, long mod0) {
            m = m0;
            row = m0 == null ? 0 : m.length;
            col = row == 0 ? 0 : m[0].length;
            mod = mod0;
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    m[r][c] %= mod;
                }

            }

        }

        public long get(int r, int c) {
            return m[r][c];
        }

        public Mat mul(Mat mat) {
            if (col != mat.row)
                return null;
            long[][] nm = new long[row][mat.col];
            for (int r = 0; r < row; r++)
                for (int c = 0; c < mat.col; c++)
                    for (int i = 0; i < col; i++)
                        nm[r][c] = (nm[r][c] + m[r][i] * mat.m[i][c]) % mod;
            return new Mat(nm, mod);
        }

        public Mat pow(long x) {
            Mat mat = new Mat(new long[][]{{1, 0}, {0, 1}}, mod), base = this;
            while (x > 0) {
                if (x % 2 != 0)
                    mat = mat.mul(base);
                base = base.mul(base);
                x /= 2;
            }
            return mat;
        }

        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof Mat))
                return false;
            Mat mat = (Mat) o;
            if (row != mat.row || col != mat.col)
                return false;
            for (int r = 0; r < row; r++)
                for (int c = 0; c < col; c++)
                    if (m[r][c] != mat.m[r][c])
                        return false;
            return true;
        }

        public void print() {
            System.out.println('[');
            for (int r = 0; r < row; r++) {
                System.out.print(' ');
                for (int c = 0; c < col; c++) {
                    if (c > 0)
                        System.out.print(' ');
                    System.out.print(m[r][c]);
                }
                System.out.println();
            }
            System.out.println(']');
        }
    }


}
