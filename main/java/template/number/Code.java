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
     * @param num num
     * @return ok
     */
    public static boolean isPrime(int num) {
        if (num <= 3) {
            return num > 1; // 3 2 , 1 not
        }
        if (num % 2 == 0 || num % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            if (isPalindrome(i)) {
                System.out.println(i);
            }
        }
        System.out.println(1 << 10); // 1 左移10位
        System.out.println(10 << 1); //10 左移1位数
    }
}
