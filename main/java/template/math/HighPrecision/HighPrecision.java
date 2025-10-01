package template.math.HighPrecision;

/**
 * @author: wuxin0011
 * @Description:
 */
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class HighPrecision {
    private static final BigDecimal TWO = BigDecimal.valueOf(2);
    private static final MathContext MC = new MathContext(100, RoundingMode.HALF_UP);

    // 计算 1/n 的第 m 位小数
    public static int nthDecimalDigit(int n, int m) {
        if (n == 1) return 0;

        // 使用公式: floor(10^m / n) % 10
        BigInteger tenPowM = BigInteger.TEN.pow(m);
        BigInteger tenN = BigInteger.valueOf(10L * n);

        // 使用模运算优化大数计算
        BigInteger numerator = tenPowM.mod(tenN);
        return numerator.divide(BigInteger.valueOf(n)).mod(BigInteger.TEN).intValue();
    }

    // 高精度平方根 (牛顿迭代法)
    public static BigDecimal sqrt(BigDecimal x, int scale) {
        if (x.compareTo(BigDecimal.ZERO) < 0) {
            throw new ArithmeticException("Square root of negative number");
        }

        MathContext mc = new MathContext(scale + 10, RoundingMode.HALF_UP);
        BigDecimal guess = x.divide(TWO, mc);
        BigDecimal tolerance = BigDecimal.ONE.scaleByPowerOfTen(-scale - 5);

        while (true) {
            BigDecimal nextGuess = guess.add(x.divide(guess, mc)).divide(TWO, mc);
            BigDecimal diff = nextGuess.subtract(guess).abs();
            if (diff.compareTo(tolerance) < 0) {
                return nextGuess.setScale(scale, RoundingMode.HALF_UP);
            }
            guess = nextGuess;
        }
    }

    // 高精度自然对数 (泰勒展开)
    public static BigDecimal ln(BigDecimal x, int scale) {
        if (x.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ArithmeticException("Logarithm of non-positive number");
        }

        MathContext mc = new MathContext(scale + 10, RoundingMode.HALF_UP);

        // 将 x 调整到 (0.5, 2] 范围内
        int shift = 0;
        BigDecimal adjustedX = x;
        while (adjustedX.compareTo(BigDecimal.valueOf(2)) > 0) {
            adjustedX = adjustedX.divide(BigDecimal.valueOf(10), mc);
            shift++;
        }
        while (adjustedX.compareTo(BigDecimal.valueOf(0.5)) <= 0) {
            adjustedX = adjustedX.multiply(BigDecimal.valueOf(10), mc);
            shift--;
        }

        // 泰勒展开: ln(1+y) = y - y^2/2 + y^3/3 - ...
        BigDecimal y = adjustedX.subtract(BigDecimal.ONE).divide(adjustedX.add(BigDecimal.ONE), mc);
        BigDecimal y2 = y.multiply(y, mc);
        BigDecimal result = BigDecimal.ZERO;
        BigDecimal term = y;
        BigDecimal yPower = y;

        for (int n = 1; n < 1000; n += 2) {
            term = yPower.divide(BigDecimal.valueOf(n), mc);
            if (term.abs().compareTo(BigDecimal.ONE.scaleByPowerOfTen(-scale - 5)) < 0) {
                break;
            }
            result = result.add(term);
            yPower = yPower.multiply(y2, mc);
        }

        result = result.multiply(TWO);

        // 加上调整的部分: ln(x) = ln(adjustedX) + shift * ln(10)
        if (shift != 0) {
            BigDecimal ln10 = ln(BigDecimal.TEN, scale + 5);
            result = result.add(ln10.multiply(BigDecimal.valueOf(shift)));
        }

        return result.setScale(scale, RoundingMode.HALF_UP);
    }

    // 高精度指数函数
    public static BigDecimal exp(BigDecimal x, int scale) {
        MathContext mc = new MathContext(scale + 10, RoundingMode.HALF_UP);

        // 使用泰勒展开: e^x = 1 + x + x^2/2! + x^3/3! + ...
        BigDecimal result = BigDecimal.ONE;
        BigDecimal term = BigDecimal.ONE;
        BigDecimal xPower = BigDecimal.ONE;
        BigInteger factorial = BigInteger.ONE;

        for (int n = 1; n < 100; n++) {
            factorial = factorial.multiply(BigInteger.valueOf(n));
            xPower = xPower.multiply(x, mc);
            term = xPower.divide(new BigDecimal(factorial), mc);

            if (term.abs().compareTo(BigDecimal.ONE.scaleByPowerOfTen(-scale - 5)) < 0) {
                break;
            }
            result = result.add(term);
        }

        return result.setScale(scale, RoundingMode.HALF_UP);
    }

    // 高精度圆周率 (Chudnovsky 算法)
    public static BigDecimal pi(int digits) {
        MathContext mc = new MathContext(digits + 10, RoundingMode.HALF_UP);

        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal constant = sqrt(BigDecimal.valueOf(10005), digits + 10)
                .multiply(BigDecimal.valueOf(426880));

        BigDecimal a = BigDecimal.ONE;
        BigDecimal b = BigDecimal.ZERO;
        BigDecimal c = BigDecimal.ONE;

        for (int k = 0; k < digits / 14 + 10; k++) {
            if (k > 0) {
                // 更新各项
                BigInteger kBig = BigInteger.valueOf(k);
                a = a.multiply(BigDecimal.valueOf(-(6L*k-5)*(2L*k-1)*(6L*k-1)))
                        .divide(BigDecimal.valueOf(k*k*k * 26680L * 26680L), mc);
                b = b.add(BigDecimal.valueOf(545140134L * k));
                c = c.multiply(BigDecimal.valueOf(-(6L*k-5)*(2L*k-1)*(6L*k-1)))
                        .divide(BigDecimal.valueOf(k*k*k * 26680L * 26680L), mc);
            }

            BigDecimal term = a.multiply(b.add(BigDecimal.valueOf(13591409)))
                    .divide(c, mc);
            sum = sum.add(term);

            if (term.abs().compareTo(BigDecimal.ONE.scaleByPowerOfTen(-digits - 5)) < 0) {
                break;
            }
        }

        return constant.divide(sum, mc).setScale(digits, RoundingMode.HALF_UP);
    }
}