package leetcode.contest.biweekly.bi_100.bi_126.a;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url
 * @title
 */
public class A {
    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "sumOfEncryptedInt", "in.txt");
    }


    public int sumOfEncryptedInt(int[] nums) {
        int tot = 0;
        for (int num : nums) {
            tot += sum(num);
        }
        return tot;
    }

    public static int sum(int num) {
        char[] cs = String.valueOf(num).toCharArray();
        int mx = 0;
        for (char c : cs) {
            int v = c - '0';
            mx = Math.max(mx, v);
        }
        int tot = 0;
        int i = 0;
        int di = 1;
        while (i < cs.length) {
            tot += mx * di;
            di *= 10;
            i++;
        }
        return tot;
    }
}
