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
@Description(value = "字符串相乘", url = "https://leetcode.cn/problems/multiply-strings/", diff = Difficulty.MEDIUM, tag = Tag.STRING)
public class MultiplyStrings implements LogarithmicDevice {


    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(MultiplyStrings.class);
    }

    /**
     * 不能使用任何内置的 BigInteger 库或直接将输入转换为整数
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if (num1 == null
                || num1.length() == 0
                || num2 == null
                || num2.length() == 0
                || "0".equals(num1)
                || "0".equals(num2)) {
            return "0";
        }
        int l1 = num1.length();
        int l2 = num2.length();
        int[] ints = new int[l1 + l2];
        for (int i = l1 - 1; i >= 0; i--) {
            int a = num1.charAt(i) - '0';
            for (int i1 = l2 - 1; i1 >= 0; i1--) {
                int b = num2.charAt(i1) - '0';
                ints[i + i1 + 1] += a * b;
            }
        }

        for (int i = ints.length - 1; i > 0; --i) {
            // 获取进位数
            ints[i - 1] += ints[i] / 10;
            // 获取当前值
            ints[i] %= 10;
        }

        // to str
        int i = ints[0] == 0 ? 1 : 0;
        StringBuilder ans = new StringBuilder();
        for (; i < ints.length; ++i) {
            ans.append(ints[i]);
        }

        return ans.toString();
    }

    @Override
    public void logarithmicDevice() {
        String num1 = "2", num2 = "3";
        TestUtils.testBoolean(multiply(num1, num2), "6", "ok");
        num1 = "123";
        num2 = "456";
        TestUtils.testBoolean(multiply(num1, num2), "56088", "ok");

    }
}
