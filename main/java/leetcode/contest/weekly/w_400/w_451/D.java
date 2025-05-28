package leetcode.contest.weekly.w_400.w_451;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-451/problems/lexicographically-smallest-string-after-adjacent-removals">移除相邻字符后字典序最小的字符串</a>
 * @title: 移除相邻字符后字典序最小的字符串
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "lexicographicallySmallestString", "D.txt");
    }

    private static final int MOD = (int) 1e9 + 7;


    public static boolean check(char a, char b) {
        return Math.abs(a - b) == 1 || a == 'a' && b == 'z' || (a == 'z' && b == 'a');
    }

    char[] s;

    public String lexicographicallySmallestString(String ss) {
        if (ss.length() <= 1) return ss;
        s = ss.toCharArray();
        return null;
    }

    public void dfs(int i, int j) {

    }


}