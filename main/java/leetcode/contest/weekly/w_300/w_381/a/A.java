package leetcode.contest.weekly.w_300.w_381.a;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description: 输入单词需要的最少按键次数 I
 */
public class A {
    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "minimumPushes");
    }


    public int minimumPushes(String word) {
        int n = word.length();
        if (n <= 8) return n;
        if (n <= 16) return 8 + (n - 8) * 2;
        if (n <= 24) return 24 + (n - 16) * 3;
        return 48 + (n - 24) * 4;
    }


}
