package leetcode.contest.biweekly.bi_100.bi_165;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/biweekly-contest-165/problems/maximum-xor-of-subsequences">子序列最大 XOR 值</a>
 * @title: 子序列最大 XOR 值
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "maxXorSubsequences", "D.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public int maxXorSubsequences(int[] a) {
        int[] base = new int[32];
        for (int x : a) {
            for (int i = 31; i >= 0; i--) {
                if ((x >> i & 1) == 1) {
                    if (base[i] > 0) {
                        x ^= base[i];
                    } else {
                        base[i] = x;
                        break;
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 31; i >= 0; i--) {
            ans = Math.max(ans, (ans ^ base[i]));
        }
        return ans;
    }


}