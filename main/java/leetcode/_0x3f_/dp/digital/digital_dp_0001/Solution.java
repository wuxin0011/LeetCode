package leetcode._0x3f_.dp.digital.digital_dp_0001;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/rotated-digits
 * @title: 旋转数字
 */
public class Solution {


    // 方法一 由于题目数据量不是很大 因此可以预处理
    static class S1 {
        public static void main(String[] args) {
            IoUtil.testUtil(S1.class, "rotatedDigits", "in.txt");
        }

        static int N = 10002;
        static int[] dp = new int[N];

        static {
            dp[0] = 0;
            for (int i = 1; i < dp.length; i++) {
                if (is(i)) {
                    dp[i] = dp[i - 1] + 1;
                } else {
                    dp[i] = dp[i - 1];
                }
            }
        }


        public int rotatedDigits(int n) {
            // System.out.println("dp[10] = " + dp[n] + ",n =  " + n);
            return dp[n];
        }

        public static boolean is(int x) {
            int v = 0;
            boolean ok = false;
            while (x > 0) {
                v = x % 10;
                // System.out.println(v);
                if (v == 3 || v == 7 || v == 4) {
                    return false;
                }
                if (v == 2 || v == 5 || v == 6 || v == 9) {
                    ok = true;
                }
                x /= 10;
            }

            return ok;
        }

    }

}