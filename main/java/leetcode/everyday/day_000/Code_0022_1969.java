package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url https://leetcode.cn/problems/minimum-non-zero-product-of-the-array-elements
 * @title
 */
public class Code_0022_1969 {
    public static void main(String[] args) {
        IoUtil.testUtil(Code_0022_1969.class,IoUtil.DEFAULT_METHOD_NAME,"txt_file\\Code_0022_1969.txt");
    }

    private static final int MOD = 1_000_000_007;

    private long pow(long x, int p) {
        x %= MOD;
        long res = 1;
        while (p-- > 0) {
            res = res * x % MOD;
            x = x * x % MOD;
        }
        return res;
    }

    public int minNonZeroProduct(int p) {
        long k = (1L << p) - 1;
        return (int) (k % MOD * pow(k - 1, p - 1) % MOD);
    }


}
