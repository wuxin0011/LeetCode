package leetcode.contest.weekly.w_300.w_390.b;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url
 * @title
 */
public class B {
    public static void main(String[] args) {


        IoUtil.testUtil(B.class, IoUtil.DEFAULT_METHOD_NAME, "B.txt");
    }

    public int minOperations(int k) {
        if (k <= 2) {
            return k - 1;
        }

        int mi = k;
        for (int v = 1; v <= k; v++) {
            System.out.println("v = " + v + ",k = " + k);
            mi = Math.min(calc(v, k), mi);
        }
        return mi;
    }

    public static int calc(int v, int k) {
        if (v != 0 && k % v == 0) {
            return v - 1 + k / v - 1;
        }
        int val = v;
        int cnt = v - 1;
        while (v < k) {
            v += val;
            cnt++;
        }
        return cnt;
    }


}
