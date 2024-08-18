package leetcode.contest.biweekly.bi_100.bi_137.b;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/biweekly-contest-137/problems/find-the-power-of-k-size-subarrays-ii
 * @title: 长度为 K 的子数组的能量值 II
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class,"resultsArray","B.txt");
    }


    public int[] resultsArray(int[] a, int k) {
        int n = a.length;
        int[] ans = new int[n - k + 1];
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            if (i >= k) {
                if (a[i - k + 1] - a[i - k] != 1) {
                    cnt--;
                }
            }
            if (i > 0 && a[i] - a[i - 1] != 1) {
                cnt++;
            }
            if (i >= k - 1) {
                ans[i - k + 1] = cnt == 0 ? a[i] : -1;
            }
        }
        return ans;
    }

  

}