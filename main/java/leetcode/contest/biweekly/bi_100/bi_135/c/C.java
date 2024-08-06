package leetcode.contest.biweekly.bi_100.bi_135.c;

import code_generation.annotation.TestCaseGroup;
import code_generation.utils.IoUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-135/problems/minimum-array-changes-to-make-differences-equal
 * @title: 使差值相等的最少数组改动次数
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "minChanges", "C.txt");
    }


    @TestCaseGroup(start = 4)
    public int minChanges(int[] a, int k) {
        int n = a.length;
//        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, Integer> cnt = new HashMap<>();
        int i = 0;
        int mid = n >> 1;
        int mx = 0;
        while (i <= mid) {
            int v = Math.abs(a[i] - a[n - i - 1]);
            System.out.printf("(%d,%d) = %d \n",a[i],a[n - i - 1],v);
            if (v <= k) {

            }
            int t = cnt.getOrDefault(v, 0) + 1;
            mx = Math.max(mx, t);
            cnt.put(v, t);
            i++;
        }
        // System.out.println(cnt + ",n = " + n + ",k = " + k + ",mx = " + mx);
        // System.out.println(mx);
        int use = 1;
        int ans = 0;
        for (Map.Entry<Integer, Integer> item : cnt.entrySet()) {
            if (use == 1 && item.getValue() == mx) {
                use = 0;
            } else {
                ans += item.getValue();
            }
        }
        return ans;

    }


}