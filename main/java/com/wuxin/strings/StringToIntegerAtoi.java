package com.wuxin.strings;

import com.wuxin.annotation.Description;
import com.wuxin.utils.Bean.Difficulty;
import com.wuxin.utils.Bean.Tag;
import com.wuxin.utils.InvocationHandlerMethodTime;
import com.wuxin.utils.LogarithmicDevice;
import com.wuxin.utils.TestUtils;

/**
 * @author: wuxin0011
 * @Description:
 */

@Description(value = "字符串转换整数 (atoi) 注意：除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符 ！！", url = "https://leetcode.cn/problems/string-to-integer-atoi/", diff = Difficulty.MEDIUM, tag = Tag.STRING)
public class StringToIntegerAtoi implements LogarithmicDevice {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(StringToIntegerAtoi.class);
    }

    public int myAtoi(String s) {

        // 是否为空字符串
        if (s == null || "".equals(s)) {
            return 0;
        }
        // 空格字符串为 ' '
        int i = 0;
        while (s.charAt(i) == ' ') {
            if (i == s.length() - 1) {
                return 0;
            }
            i++;
        }
        // 判断是否是正负数
        boolean flag = false;
        if (s.charAt(i) == '-' || s.charAt(i) == '+') {
            i++;
        }
        // 计算符号
        flag = (i - 1) >= 0 && s.charAt(i - 1) == '-';
        int result = 0;
        for (; i < s.length(); i++) {
            char cur = s.charAt(i);
            // 判断是否是数字 如果是不是数字直接退出
            if (isNumber(cur)) {
                // 溢出判断 因为 result 下次需要 * 10 因此会溢出
                if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && s.charAt(i) > '7')) {
                    // 根据符号返回结果
                    return flag ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                // 合并上一步结果
                result = result * 10 + (cur - '0');
            } else {
                return flag ? -1 * result : result;
            }

        }

        return flag ? -1 * result : result;
    }

    public static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    @Override
    public void logarithmicDevice() {
        TestUtils.testBoolean(4193, myAtoi("4193 with words"));
        TestUtils.testBoolean(-42, myAtoi("   -42"));
        TestUtils.testBoolean(42, myAtoi("42--------"));
        TestUtils.testBoolean(0, myAtoi("words and 987"));
    }
}
