package leetcode.contest.biweekly.bi_100.bi_134.a;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-134/problems/alternating-groups-i
 * @title: 交替组 I
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "numberOfAlternatingGroups", "A.txt");
    }


    public int numberOfAlternatingGroups(int[] a) {
        int n = a.length;
        int cnt = 0;
        if (n < 3) {
            return 0;
        }
        for (int i = 0; i < n; i++) {
            if (i >= 2 && a[i - 2] != a[i - 1] && a[i - 1] != a[i]) {
                cnt++;
            }
        }
        if (a[0] != a[n - 1] && a[n - 1] != a[n - 2]) {
            cnt++;
        }
        if (a[1] != a[0] && a[0] != a[n - 1]) {
            cnt++;
        }
        return cnt;
    }


}