package leetcode.contest.weekly.w_400.w_407.b;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-407/problems/vowels-game-in-a-string
 * @title: 字符串元音游戏
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "doesAliceWin", "B.txt");
    }


    public boolean doesAliceWin(String s) {
        char[] a = s.toCharArray();
        int n = a.length;
        int tot = 0;
        for (char c : a) {
            if (is(c)) tot++;
        }
        int u = 1;
        for (int i = 0; i < n; ) {
            int j = i;
            int x = 0;
            while (j < n && x != (tot % 2 == u ? tot : (tot - 1))) {
                if (is(a[j])) {
                    x++;
                }
                j++;
            }
            if (x % 2 != u) {
                return u == 0;
            }
            u ^= 1;
            tot -= x;
            i = j;
            if (tot == 0) {
                break;
            }
        }
        return u == 0;
    }

    public static boolean is(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }


}