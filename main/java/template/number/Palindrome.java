package template.number;

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


}
