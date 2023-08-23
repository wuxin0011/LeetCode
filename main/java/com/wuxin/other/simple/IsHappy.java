package com.wuxin.other.simple;

import com.wuxin.annotation.Description;

/**
 * @author: wuxin0011
 * @Description: 判断一个数是不是快乐数
 * @link https://leetcode.cn/problems/happy-number/
 *
 */
@Description(value = "判断一个数是不是快乐数",url = "https://leetcode.cn/problems/happy-number/" )
public class IsHappy {

    public static void main(String[] args) {

        System.out.println(Math.pow(2, 2));

        System.out.println(new IsHappy().isHappy(19));

        IsHappy isHappy = new IsHappy();
        for (int i = 0; i < 100; i++) {
            if( isHappy.isHappy(i)){
                System.out.println(" is Happy = "+ i);
            }
        }

    }


    // 1 <= n <= 2^31 - 1

    public boolean isHappy(int n) {
        int result = n;
        int count = 100;
        while (count > 0) {
            result = getPowResult(result);
            if (result == 1) {
                return true;
            }
            count--;
        }
        return false;
    }


    public static int getPowResult(int n) {
        int m = n;
        int cur = 0;
        int result = 0;
        while (m >= 10) {
            // 当前位数
            cur = m % 10;
            // 获取当前值
            m = m / 10;
            result = result + cur * cur;
        }
        result += m * m;
        return result;
    }
}
