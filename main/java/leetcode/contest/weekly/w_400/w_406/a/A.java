package leetcode.contest.weekly.w_400.w_406.a;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-406/problems/lexicographically-smallest-string-after-a-swap
 * @title: 交换后字典序最小的字符串
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "getSmallestString", "A.txt");
    }


    public String getSmallestString(String s) {
        char[] a = s.toCharArray();
        int n = a.length;
        for (int i = 1; i < n; i++) {
            if (a[i - 1] > a[i] && (a[i] - '0') % 2 == (a[i - 1] - '0') % 2) {
                char c = a[i - 1];
                a[i - 1] = a[i];
                a[i] = c;
                break;
            }
        }
        return new String(a);
    }


}