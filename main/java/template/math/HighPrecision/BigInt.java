package template.math.HighPrecision;

/**
 * @author: wuxin0011
 * @Description:
 */

import java.math.BigInteger;

public class BigInt {
    private static final BigInteger ZERO = BigInteger.ZERO;
    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger TWO = BigInteger.valueOf(2);

    // 快速幂取模
    public static BigInteger powMod(BigInteger a, BigInteger b, BigInteger mod) {
        BigInteger res = ONE;
        a = a.mod(mod);
        while (b.compareTo(ZERO) > 0) {
            if (b.testBit(0)) { // b % 2 == 1
                res = res.multiply(a).mod(mod);
            }
            a = a.multiply(a).mod(mod);
            b = b.shiftRight(1); // b /= 2
        }
        return res;
    }

    // 最大公约数
    public static BigInteger gcd(BigInteger a, BigInteger b) {
        while (!b.equals(ZERO)) {
            BigInteger temp = b;
            b = a.mod(b);
            a = temp;
        }
        return a;
    }

    // 扩展欧几里得算法
    public static BigInteger[] exgcd(BigInteger a, BigInteger b) {
        if (b.equals(ZERO)) {
            return new BigInteger[]{a, ONE, ZERO};
        }
        BigInteger[] result = exgcd(b, a.mod(b));
        BigInteger gcd = result[0];
        BigInteger x = result[2];
        BigInteger y = result[1].subtract(a.divide(b).multiply(result[2]));
        return new BigInteger[]{gcd, x, y};
    }

    // 模逆元 (a^(-1) mod m)
    public static BigInteger modInverse(BigInteger a, BigInteger m) {
        BigInteger[] result = exgcd(a, m);
        if (!result[0].equals(ONE)) {
            throw new ArithmeticException("Modular inverse does not exist");
        }
        return result[1].mod(m);
    }

    // 判断是否为素数 (Miller-Rabin 测试)
    public static boolean isPrime(BigInteger n, int certainty) {
        if (n.compareTo(TWO) < 0) return false;
        if (n.equals(TWO)) return true;
        if (!n.testBit(0)) return false; // 偶数

        // 将 n-1 写成 d*2^s
        BigInteger d = n.subtract(ONE);
        int s = 0;
        while (!d.testBit(0)) {
            d = d.shiftRight(1);
            s++;
        }

        // 进行多次测试
        for (int i = 0; i < certainty; i++) {
            BigInteger a = randomBigInteger(TWO, n.subtract(ONE));
            BigInteger x = a.modPow(d, n);

            if (x.equals(ONE) || x.equals(n.subtract(ONE))) {
                continue;
            }

            boolean composite = true;
            for (int j = 0; j < s - 1; j++) {
                x = x.modPow(TWO, n);
                if (x.equals(ONE)) return false;
                if (x.equals(n.subtract(ONE))) {
                    composite = false;
                    break;
                }
            }
            if (composite) return false;
        }
        return true;
    }

    // 生成随机大整数
    public static BigInteger randomBigInteger(BigInteger min, BigInteger max) {
        BigInteger range = max.subtract(min).add(ONE);
        BigInteger result;
        do {
            result = new BigInteger(range.bitLength(), new java.util.Random());
        } while (result.compareTo(range) >= 0);
        return result.add(min);
    }
}