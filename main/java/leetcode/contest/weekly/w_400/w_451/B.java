package leetcode.contest.weekly.w_400.w_451;

import code_generation.annotation.TestCaseGroup;
import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-451/problems/resulting-string-after-adjacent-removals">移除相邻字符</a>
 * @title: 移除相邻字符
 */
@TestCaseGroup(start = 3, end = 0x3fff, use = true)
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "resultingString", "B.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public static boolean check(char a, char b) {
        return Math.abs(a - b) == 1 || a == 'a' && b == 'z';
    }

    public String resultingString(String s) {
        char[] a = s.toCharArray();
        int n = a.length;
        char[] sk = new char[n];
        int size = 0;
        for (int i = 0; i < n; i++) {
            if (size > 0 && (check(a[i], sk[size - 1]) || check(sk[size - 1], a[i]))) {
                size--;
            } else {
                sk[size++] = a[i];
            }
        }
        return new String(sk, 0, size);
    }


}