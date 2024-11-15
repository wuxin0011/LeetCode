package template.math.number;

/**
 * @author: wuxin0011
 * @Description: 回文数
 */
public class Palindrome {


    /**
     * 判断一个数是否是回文数
     *
     * @param x 数字
     * @return true
     */
    public static boolean isPalindrome(long x) {
        if (x < 0) return false;
        long off = 1;
        while(x / off >= 10) {
            off *= 10;
        }
        while( x > 0) {
            if( x % 10 != x / off ) {
                return false;
            }
            x = (x % off) / 10;
            off /= 100;
        }
        return true;
    }


    // 原理是每次取的原来种子的最右侧数字 然后消除这一个数字
    // 输入一个种子构造回文数
    // true表示奇回文
    public static long builderPalindromeNumber(long x,boolean odd) {
        long y = x;
        if(odd) x /= 10;
        while(x > 0) {
            y = y * 10 + x % 10;
            x /= 10;
        }
        return y;
    }


}
