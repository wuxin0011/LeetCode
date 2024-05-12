package leetcode.contest.weekly.w_300.w_397.a;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-397/problems/permutation-difference-between-two-strings
 * @title: 两个字符串的排列差
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "findPermutationDifference", "A.txt");
    }


    public int findPermutationDifference(String s, String t) {
        int[] h1 = help(s);
        int[] h2 = help(t);
        int tot = 0;
        for (int i = 0; i < h1.length; i++) {
            if (h1[i] == -1) {
                continue;
            }
            tot += Math.abs(h1[i] - h2[i]);
        }
        return tot;
    }

    public static int[] help(String s) {
        int[] h1 = new int[26];
        Arrays.fill(h1, -1);
        int[] h2 = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            h1[c - 'a'] = i;
        }
        return h1;
    }


}