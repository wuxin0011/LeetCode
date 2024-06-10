package template.number;

/**
 * @author: wuxin0011
 * @Description:
 */
public class Code {


    /**
     * 最小公倍数
     *
     * @param a a
     * @param b b
     * @return 返回最小公倍数
     */
    public static int gcd(int a, int b) {
        while (a % b != 0) {
            int mod = a % b;
            a = b;
            b = mod;
        }
        return b;
    }


    /**
     * 最大公约数
     *
     * @param a a
     * @param b b
     * @return 最大公约数
     */
    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }


    /**
     * 判断一个数是否是回文数
     *
     * @param n 数字
     * @return true
     */
    public static boolean isPalindrome(int n) {
        if (n < 0) {
            return false;
        }
        int of = 1;
        while (n / of >= 10) {
            of *= 10;
        }
        while (n != 0) {
            // 323 / 100 => 3
            int left = n / of; // 除最最边数字

            // 323 % 10 =>
            int right = n % 10; // 取出最低为数字
            if (left != right) {
                return false;
            }
            // 同时去掉最高位和最低位
            // example: 323 % 100 => 23 23 / 10 => 2
            n = (n % of) / 10;
            of /= 100;
        }
        return true;
    }


    /**
     * 判断一个数是否是质数（素数）
     * 定义 ： 除了1和本身外 不能被其他数整除
     *
     * @param n num
     * @return ok
     */
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * 上取整
     *
     * @param a int
     * @param b int
     * @return int
     */
    public static int ceil(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        if (a % b == 0) {
            return a / b;
        }
        if (a > 0) {
            return (a + b - 1) / b;
        } else {
            return a / b;
        }
    }


    /**
     * 下取整
     *
     * @param a int
     * @param b int
     * @return int
     */
    public static int floor(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        if (a % b == 0) {
            return a / b;
        }
        if (a > 0) {
            return a / b;
        } else {
            return (a - b + 1) / b;
        }
    }


    /**
     * 快速幂
     *
     * @param x val
     * @param n 幂的次数
     * @return result
     */
    public static long pow(long x, long n) {
        return pow(x, n, (long) 1e9 + 7);
    }


    /**
     * 快速幂
     *
     * @param x   val
     * @param n   幂次数
     * @param mod 取mod
     * @return x ^ n
     */
    public static long pow(long x, long n, long mod) {
        boolean isNeg = n < 0;
        if (isNeg) n = -n;
        long res = 1;
        while (n > 0) {
            // 当前位数有1
            if ((n & 1) == 1) {
                res = res * x % mod;
            }
            x = x * x % mod;
            n >>= 1;
        }
        return isNeg ? 1 / res : res;
    }


    public static void main(String[] args) {
        System.out.println(1 << 10); // 1 左移10位
        System.out.println(10 << 1); //10 左移1位数
        System.out.println("ceil and floor");
        System.out.println(ceil(11, 10));
        System.out.println(ceil(-11, -10));
        System.out.println(floor(11, 10));
        System.out.println(floor(-11, -10));
        System.out.println("=======end========");


        System.out.println(pow(2, 10) == (int) Math.pow((double) 2, (double) 10));
        System.out.println(pow(2, 3) == (int) Math.pow((double) 2, (double) 3));
    }
}
