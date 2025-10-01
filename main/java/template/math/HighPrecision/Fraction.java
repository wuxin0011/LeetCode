package template.math.HighPrecision;

/**
 * @author: wuxin0011
 * @Description:
 */

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Fraction implements Comparable<Fraction> {
    private BigInteger numerator;   // 分子
    private BigInteger denominator; // 分母

    public Fraction(BigInteger numerator, BigInteger denominator) {
        if (denominator.equals(BigInteger.ZERO)) {
            throw new ArithmeticException("Denominator cannot be zero");
        }

        // 标准化分数
        BigInteger gcd = numerator.gcd(denominator);
        this.numerator = numerator.divide(gcd);
        this.denominator = denominator.divide(gcd);

        // 确保分母为正
        if (this.denominator.compareTo(BigInteger.ZERO) < 0) {
            this.numerator = this.numerator.negate();
            this.denominator = this.denominator.negate();
        }
    }

    public Fraction(long numerator, long denominator) {
        this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }

    // 加法
    public Fraction add(Fraction other) {
        BigInteger newNum = this.numerator.multiply(other.denominator)
                .add(other.numerator.multiply(this.denominator));
        BigInteger newDen = this.denominator.multiply(other.denominator);
        return new Fraction(newNum, newDen);
    }

    // 减法
    public Fraction subtract(Fraction other) {
        BigInteger newNum = this.numerator.multiply(other.denominator)
                .subtract(other.numerator.multiply(this.denominator));
        BigInteger newDen = this.denominator.multiply(other.denominator);
        return new Fraction(newNum, newDen);
    }

    // 乘法
    public Fraction multiply(Fraction other) {
        BigInteger newNum = this.numerator.multiply(other.numerator);
        BigInteger newDen = this.denominator.multiply(other.denominator);
        return new Fraction(newNum, newDen);
    }

    // 除法
    public Fraction divide(Fraction other) {
        BigInteger newNum = this.numerator.multiply(other.denominator);
        BigInteger newDen = this.denominator.multiply(other.numerator);
        return new Fraction(newNum, newDen);
    }

    // 转换为小数 (指定精度)
    public BigDecimal toDecimal(int scale) {
        return new BigDecimal(numerator)
                .divide(new BigDecimal(denominator), scale, RoundingMode.HALF_UP);
    }

    // 比较
    @Override
    public int compareTo(Fraction other) {
        BigInteger left = this.numerator.multiply(other.denominator);
        BigInteger right = other.numerator.multiply(this.denominator);
        return left.compareTo(right);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Fraction other = (Fraction) obj;
        return numerator.equals(other.numerator) && denominator.equals(other.denominator);
    }

    @Override
    public String toString() {
        if (denominator.equals(BigInteger.ONE)) {
            return numerator.toString();
        }
        return numerator + "/" + denominator;
    }

    // Getters
    public BigInteger getNumerator() { return numerator; }
    public BigInteger getDenominator() { return denominator; }
}