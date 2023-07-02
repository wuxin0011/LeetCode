package com.wuxin.simple_other;

import com.wuxin.annotation.Description;
import com.wuxin.utils.InvocationHandlerMethodTime;
import com.wuxin.utils.LogarithmicDevice;

import java.util.Random;

/**
 * @author: wuxin0011
 * @Description:
 */
@Description(value = "整数反转", url = "https://leetcode.cn/problems/reverse-integer/")
public class ReverseInteger implements LogarithmicDevice {

    public static void main(String[] args) {
        InvocationHandlerMethodTime.getRunTime(ReverseInteger.class);
    }

    public int reverse(int x) {
        int ans = 0;
        for (; x != 0; x /= 10) {
            if (ans < Integer.MIN_VALUE / 10 || ans > Integer.MAX_VALUE / 10) {
                return 0;
            }
            ans = ans * 10 + x % 10;
        }
        return ans;
    }

    /**
     * 使用字符串反转 简单 但是效率
     *
     * @param x
     * @return
     */
    public Integer reverseTest02(int x) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(x);
        try {
            return Integer.parseInt(stringBuilder.reverse().toString());
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void logarithmicDevice() {
        int i = 100;
        boolean flag = true;
        while (i > 0) {
            int i1 = new Random().nextInt(Integer.MAX_VALUE/10);
            if (!reverseTest02(i1).equals(reverse(i1))) {
                System.out.println(i1 + " , test01 = " + reverseTest02(i1) + ",test02 =" + reverse(i1));
                flag = false;
                break;
            }
            i--;
        }
        System.out.println(flag ? "测试通过" : "测试不通过");
    }
}
