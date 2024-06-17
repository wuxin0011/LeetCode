package template.number;

/**
 * @author: wuxin0011
 * @Description: 回文数
 */
public class Palindrome {


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


}
