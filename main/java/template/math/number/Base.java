package template.math.number;

/**
 * @author: wuxin0011
 * @Description:
 */
public class Base {



    // 数组的LCM
    public static int arrayLCM(int[] a){
        int m = a[0];
        for(int i = 1;i < a.length;i++) {
            m = lcm(m,a[i]);
        }
        return m;
    }


    // 数组的GCD
    public static int arrayGCD(int[] a){
        int g = a[0];
        for(int i = 1;i < a.length;i++) {
            g = gcd(g,a[i]);
        }
        return g;
    }


    /**
     * 最小公倍数
     *
     * @param a a
     * @param b b
     * @return 返回最小公倍数
     */
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }


    /**
     * 最大公约数
     *
     * @param a a
     * @param b b
     * @return 最大公约数
     */
    public static int lcm(int a, int b) {
        return (a  / gcd(a, b) * b);
    }


    public static long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
    public static long lcm(long a, long b) {
        return (a  / gcd(a, b) * b);
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
     * 上取整
     *
     * @param a int
     * @param b int
     * @return int
     */
    public static long ceil(long a, long b) {
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
    public static long floor(long a, long b) {
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


    public static int calcMOD(int mod, int... args) {
        long s = 0;
        for (int v : args) {
            s += v % mod;
        }
        return (int) (s + mod) % mod;
    }

}
