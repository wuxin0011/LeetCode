package com.wuxin.array;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 */
public class PlusOne {
    public static void main(String[] args) {
        int[] ints = {9};
        int[] ints1 = new PlusOne().plusOne(ints);
        System.out.println(Arrays.toString(ints1));
    }

    public int[] plusOne(int[] digits) {
        if (digits == null) {
            return null;
        }
        int len = digits.length;
        // 考虑到存在进位情况 目前设置多个空格存放变量
        int[] n = new int[len + 1];
        // 表示当前值
        int val = 0;
        // 表示进位
        int cur = 0;

        for (int i = n.length - 1; i > 0; i--) {
            val = i == n.length - 1 ? digits[i - 1] + cur + 1 : digits[i - 1] + cur;
            cur = (int) Math.floor((double) val / 10);
            n[i] = val % 10;
        }

        if (cur != 0) {
            n[0] = cur;
        }
        // 需要做处理
        if (n[0] == 0) {
            int[] m = new int[digits.length];
            for (int i = 1; i < n.length; i++) {
                m[i - 1] = n[i];
            }
            return m;
        } else {
            return n;
        }

    }
}
