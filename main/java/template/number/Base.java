package template.number;

/**
 * @author: wuxin0011
 * @Description:
 */
public class Base {


    /**
     * 最小公倍数
     *
     * @param a a
     * @param b b
     * @return 返回最小公倍数
     */
    public static int gcd(int a, int b) {
        while (b != 0) {
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




    public static void main(String[] args) {
        System.out.println(1 << 10); // 1 左移10位
        System.out.println(10 << 1); //10 左移1位数
        System.out.println("ceil and floor");
        System.out.println(ceil(11, 10));
        System.out.println(ceil(-11, -10));
        System.out.println(floor(11, 10));
        System.out.println(floor(-11, -10));
        System.out.println("=======end========");
    }
}
