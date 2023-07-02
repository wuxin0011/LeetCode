package com.wuxin.simple_other;

import com.wuxin.utils.InvocationHandlerMethodTime;
import com.wuxin.utils.LogarithmicDevice;
import com.wuxin.utils.TestUtils;

/**
 * @author: wuxin0011
 * @Description:
 */
public class IsPalindrome implements LogarithmicDevice {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(IsPalindrome.class);
    }

    @Override
    public void logarithmicDevice() {
//        TestUtils.testBoolean(isPalindrome(""), true, "ok");
        TestUtils.testBoolean(isPalindrome("raceacar"), false, "ok");
        TestUtils.testBoolean(isPalindrome("0P"), false, "ok");
    }

    public boolean isPalindrome(String s) {

        if (s == null || "".equals(s) || s.length() == 0) {
            return true;
        }

        int len = s.length();
        for (int i = 0, j = len - 1; i <= j; i++, j--) {
            while (i < len && !isNotBlank(s.charAt(i))) {
                i++;
            }
            while (j >= 0 && !isNotBlank(s.charAt(j))) {
                j--;
            }
            if (i < len && j >= 0 && j >= i && !isEqual(s.charAt(i), s.charAt(j))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(char c) {
        return 'a' <= c && c <= 'z' || 'A' <= c && c <= 'Z' || '0' <= c && c <= '9';
    }


    public static boolean isEqual(char c1, char c2) {
        if ('0' <= c1 && c1 <= '9' || '0' <= c2 && c2 <= '9') {
            return c1 == c2;
        }



        return c1 == c2 || Math.abs((c1 - c2)) == 32;
    }
}
