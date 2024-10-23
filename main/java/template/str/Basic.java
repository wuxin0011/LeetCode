package template.str;

/**
 * @author: wuxin0011
 * @Description: 字符串中常见函数判断
 */
public class Basic {


    static void reverseString(char[] chars) {
        int l = 0,r = chars.length - 1;
        while(l < r) {
            char c = chars[l];
            chars[l] = chars[r];
            chars[r] = c;
            r--;
            l++;
        }

    }
    static String reverseString(String s) {
        char[] charArray = s.toCharArray();
        reverseString(charArray);
        return new String(charArray);
    }



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
    // 也可以通过字符串hash判断

    /**
     * @see
     * @param s
     * @return
     */
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


    // 字符串向后移动
    static char convert(char c, char stChar, int step, int mod) {
        return (char) ((c - stChar + step) % mod + stChar);
    }

    // example
    static char convertExample(char c) {
        return (char) ((c - 'a' + 1) % 26 + 'a');
    }


    public static void main(String[] args) {
//        char c = convert('z','a',1,26);
        char[] chs = {'a', 'a', 'b', 'z', 'd'};
        for (char c : chs) {
            System.out.println(convertExample(c) + " : " + convert(c, 'a', 1, 26));
        }
    }


}
