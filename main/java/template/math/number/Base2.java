package template.math.number;

/**
 * @author: wuxin0011
 * @Description:
 */
public class Base2 {


    /*位运算优化的gcd*/

    public static int gcd(int a, int b) {
        if (a == 0 || b == 0)
            return a | b;
        int t = Integer.numberOfTrailingZeros(a | b);
        a >>= Integer.numberOfTrailingZeros(a);
        while (b != 0) {
            b >>= Integer.numberOfTrailingZeros(b);
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            b -= a;
        }
        return a << t;
    }

    public static long gcd(long a, long b) {
        if (a == 0 || b == 0)
            return a | b;

        int t = Long.numberOfTrailingZeros(a | b);
        a >>= Long.numberOfTrailingZeros(a);

        while (b != 0) {
            b >>= Long.numberOfTrailingZeros(b);
            if (a > b) {
                long temp = a;
                a = b;
                b = temp;
            }
            b -= a;
        }
        return a << t;
    }
}
