package template.str;

/**
 * @author: wuxin0011
 * @Description: 字符串中常见函数判断
 */
public class Basic {


    // 判断元音字符(含大写）
    public static boolean isVol(char c) {
        if ('A' <= c && c <= 'Z') {
            c = (char) (c - 'A' + 'a');
        }
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public static boolean isP(char[] s){
        int l = 0,r = s.length - 1;
        while (l<r){
            if(s[l] != s[r]) return false;
            l++;
            r--;
        }
        return true;
    }



    // 判断一个范围字符串是不是回文串 用 暴力 dp 加速
    // 相关题目
    // https://leetcode.cn/problems/palindrome-partitioning-iv/solutions
    public boolean check(String s) {
        char[] a = s.toCharArray();
        int n = a.length;
        boolean[][] dp = new boolean[n + 1][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (a[i] == a[j]) {
                    dp[i][j] = j - i <= 2 || dp[i + 1][j - 1];
                }
            }
        }
        // ...
        return true;
    }


}
