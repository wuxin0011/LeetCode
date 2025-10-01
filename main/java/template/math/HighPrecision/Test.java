package template.math.HighPrecision;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author: wuxin0011
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        // 1. 大整数运算示例
        BigInteger a = new BigInteger("12345678901234567890");
        BigInteger b = new BigInteger("98765432109876543210");

        BigInteger gcd = BigInt.gcd(a, b);
        System.out.println("GCD: " + gcd);

        // 2. 计算 1/7 的第100位小数
        int digit = HighPrecision.nthDecimalDigit(7, 100);
        System.out.println("1/7 的第100位小数: " + digit);

        // 3. 高精度计算示例
        BigDecimal sqrt2 = HighPrecision.sqrt(BigDecimal.valueOf(2), 50);
        System.out.println("√2 (50位): " + sqrt2);

        // 4. 分数运算示例
        Fraction f1 = new Fraction(1, 3);
        Fraction f2 = new Fraction(1, 6);
        Fraction sum = f1.add(f2);
        System.out.println("1/3 + 1/6 = " + sum + " = " + sum.toDecimal(10));

        // 5. 计算圆周率
        BigDecimal pi = HighPrecision.pi(100);
        System.out.println("π (100位): " + pi);
    }
}
