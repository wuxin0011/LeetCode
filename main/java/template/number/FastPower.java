package template.number;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description: 乘法快速幂 + 矩阵快速幂
 */
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
            boolean isNeg = n < 0;
            if (isNeg) n = -n;
            long res = 1;
            long v = x;
            while (n > 0) {
                // 当前位数有1
                if ((n & 1) == 1) {
                    res = res * v % mod;
                }
                v = v * v % mod;
                n >>= 1;
            }
            return isNeg ? 1 / res : res;
        }

    }


}
