package leetcode.contest.biweekly.bi_100.bi_140.b;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-140/problems/maximize-the-total-height-of-unique-towers
 * @title: 高度互不相同的最大塔高和
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "maximumTotalSum", "B.txt");
    }


    public long maximumTotalSum(int[] a) {
        long ans = 0;
        Arrays.sort(a);
        int n = a.length;
        ans += a[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            a[i] = Math.min(a[i + 1]-1, a[i]);
            if (a[i] <= 0) {
                return -1;
            }
            ans += a[i];
        }
        return ans;
    }


}