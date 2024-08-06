package leetcode.contest.biweekly.bi_100.bi_135.b;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-135/problems/minimum-length-of-string-after-operations
 * @title: 操作后字符串的最短长度
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "minimumLength", "B.txt");
    }


    public int minimumLength(String s) {
        char[] a = s.toCharArray();
        int n = a.length;
        if (n <= 2) {
            return n;
        }
        int[] cnts = new int[26];
        for (int i = 0; i < n; i++) {
            cnts[a[i] - 'a']++;
        }
        for (int cnt : cnts) {
            if (cnt <= 2) {
                continue;
            }
            n -= (cnt - 1) / 2 * 2;
        }
        return n;
    }


}