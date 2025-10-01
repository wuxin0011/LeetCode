package template.math.HighPrecision;

import java.math.BigInteger;

/**
 * @author: wuxin0011
 * @Description:
 */
public class Utils {


    /**
     * 计算 1/n 的小数点后第 m 位数字（带循环节检测的优化版本）
     *
     * @param n 分母
     * @param m 位置（从0开始计数）
     * @return 第 m 位小数数字
     */
    public static int nthDecimalDigitOptimized(int n, int m) {
        if (n == 1) {
            return 0;
        }

        // 去除 n 的因子 2 和 5（它们不影响循环节，只影响小数起始位置）
        int nTemp = n;
        while (nTemp % 2 == 0) {
            nTemp /= 2;
        }
        while (nTemp % 5 == 0) {  // 修正：应该是5，不是5==5
            nTemp /= 5;
        }

        // 如果 nTemp == 1，说明是有限小数
        if (nTemp == 1) {
            // 计算有限小数的实际位数
            int decimalLength = getFiniteDecimalLength(n);
            if (m >= decimalLength) {
                return 0;  // 超出小数位数，后面都是0
            }
            // 对于有限小数，使用直接计算方法
            return calculateFiniteDecimalDigit(n, m);
        }

        // 使用数学公式计算无限循环小数
        return calculateInfiniteDecimalDigit(n, m);
    }

    /**
     * 获取有限小数的小数部分长度
     */
    private static int getFiniteDecimalLength(int n) {
        // 有限小数的长度由分母中2和5的因子数量决定
        int temp = n;
        int count2 = 0, count5 = 0;

        while (temp % 2 == 0) {
            temp /= 2;
            count2++;
        }
        while (temp % 5 == 0) {
            temp /= 5;
            count5++;
        }

        return Math.max(count2, count5);
    }

    /**
     * 计算有限小数的特定位数
     */
    private static int calculateFiniteDecimalDigit(int n, int m) {
        // 使用公式: (10^m * 1) / n 的个位数
        BigInteger tenPowM = BigInteger.TEN.pow(m);
        BigInteger result = tenPowM.divide(BigInteger.valueOf(n));
        return result.mod(BigInteger.TEN).intValue();
    }

    /**
     * 计算无限循环小数的特定位数
     */
    private static int calculateInfiniteDecimalDigit(int n, int m) {
        // 使用公式: floor(10^m / n) mod 10
        // 优化：使用模运算避免大数计算
        BigInteger tenN = BigInteger.valueOf(10L * n);
        BigInteger numerator = BigInteger.TEN.pow(m).mod(tenN);
        return numerator.divide(BigInteger.valueOf(n)).mod(BigInteger.TEN).intValue();
    }

    /**
     * 简化版本（推荐使用）
     */
    public static int nthDecimalDigitSimple(int n, int m) {
        if (n == 1) return 0;

        // 直接使用数学公式，Java 的 BigInteger 可以处理大数
        BigInteger tenPowM = BigInteger.TEN.pow(m);
        BigInteger result = tenPowM.divide(BigInteger.valueOf(n));
        return result.mod(BigInteger.TEN).intValue();
    }

    /**
     * 测试
     * https://codeforces.com/gym/104400/submission/339991139
     * 高性能版本（使用模运算优化）
     */
    public static int nthDecimalDigitFast(int n, int m) {
        if (n == 1) return 0;

        // 使用模运算避免大数：pow(10, m, 10*n) // n % 10
        BigInteger modulus = BigInteger.valueOf(10L * n);
        BigInteger tenPowM = BigInteger.TEN.modPow(BigInteger.valueOf(m), modulus);
        return tenPowM.divide(BigInteger.valueOf(n)).mod(BigInteger.TEN).intValue();
    }
}
