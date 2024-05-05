package leetcode.contest.weekly.w_300.w_396.a;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-396/problems/valid-word
 * @title: 有效单词
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "isValid", "A.txt");
    }


    public boolean isValid(String word) {
        if (word.length() < 3) return false;
        boolean num = false;
        boolean yuan = false;
        boolean fu = false;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (isError(c)) return false;
            if (isYuan(c)) yuan = true;
            if (isNumber(c)) num = true;
            if (isF(c)) fu = true;
        }

        return fu && yuan;
    }

    public static boolean isYuan(char c) {
        return c == 'a' || c == 'A' || c == 'e' || c == 'E' || c == 'i' || c == 'I' || c == 'o' || c == 'O' || c == 'u' || c == 'U';
    }

    public static boolean isF(char c) {
        if ('a' <= c && c <= 'z') {
            return !isYuan(c);

        } else if ('A' <= c && c <= 'Z') {
            return !isYuan(c);
        }
        return false;
    }

    public static boolean isNumber(char c) {
        return '0' <= c && c <= '9';
    }

    public static boolean isError(char c) {
        return c == '@' || c == '$' || c == '#';
    }


}