package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/grumpy-bookstore-owner
 * @title: 爱生气的书店老板
 */
public class Code_0051_1052 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0051_1052.class, "maxSatisfied", "txt_file\\Code_0051_1052.txt");
    }


    /**
     * @param customers
     * @param grumpy
     * @param minutes
     * @return
     * @see leetcode._0x3f_.slide_window.slide_0007.Solution
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;
        int mx = 0, tot = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            if (i >= minutes && grumpy[i - minutes] > 0) {
                sum -= customers[i - minutes];
            }
            if (grumpy[i] > 0) {
                sum += customers[i];
            } else {
                tot += customers[i];
            }
            if (i >= minutes - 1) {
                mx = Math.max(mx, sum);
            }
        }
        return tot + mx;
    }


}