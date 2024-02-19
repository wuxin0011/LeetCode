package leetcode.base.array.other;

import leetcode.utils.LogarithmicDevice;

/**
 * @author: wuxin0011
 * @Description:
 */
public class AddBinary implements LogarithmicDevice {

    @Override
    public void logarithmicDevice() {
        String[] s1s = {"Java","javaScript","12","110","3000"};
        String[] s2s = {};
    }

    public String addBinary(String a, String b) {

        // 均为空
        if (isEmpty(a) && isEmpty(b)) {
            return null;
        }

        // a 与 b 中如果有一个为 空 另外一个为空 返回不为空的
        if (isEmpty(a) && !isEmpty(b)) {
            return b;
        }

        if (isEmpty(b) && !isEmpty(a)) {
            return a;
        }
        // l1 l2
        int l1 = a.length() - 1;
        int l2 = b.length() - 1;
        // 使 l1 == l2
        if (l1 > l2) {
            while (l2 < l1) {
                b = "0" + b;
                l2++;
            }
        } else if (l1 < l2) {
            while (l1 < l2) {
                a = "0" + a;
                l1++;
            }
        }

        int val = 0;
        int cur = 0;
        StringBuilder target = new StringBuilder();

        while (l1 >= 0 && l2 >= 0) {
            val = getVal(a.charAt(l1), b.charAt(l2), cur);
            cur = getCur(a.charAt(l1), b.charAt(l2), cur);
            target.insert(0, val);
            l1--;
            l2--;
        }
        if (cur != 0) {
            target.insert(0, "1");
        }
        return target.toString();

    }

    /**
     * 判断字符串是否为空
     *
     * @param a 字符串
     * @return boolean
     */
    public static boolean isEmpty(String a) {
        return a == null || " ".equals(a) || a.length() == 0;
    }

    /**
     * 获取当前进位数
     *
     * @param c1  第一个字符
     * @param c2  第二个字符
     * @param cur 当一个进位数
     * @return int
     */
    public static int getCur(char c1, char c2, int cur) {
        int v = getVal(c1) + getVal(c2) + cur;
        return v >= getVal('2') ? 1 : 0;
    }

    /**
     * 获取当前值
     *
     * @param c1  字符一
     * @param c2  字符二
     * @param val 当前值
     * @return 结果
     */
    public static int getVal(char c1, char c2, int val) {
        int v = getVal(c1) + getVal(c2) + val;
        if (v == getVal('1') || v == getVal('3')) {
            return 1;
        }
        return 0;
    }


    /**
     * 获取数值大小
     *
     * @param c 字符
     * @return int
     */
    public static int getVal(char c) {
        return c - '0';
    }
}
