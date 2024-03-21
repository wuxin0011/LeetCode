package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description: 简单模拟题
 * @title
 * @url https://leetcode.cn/problems/capitalize-the-title/description/?envType=daily-question&envId=2024-03-11
 */
@SuppressWarnings("all")
public class Code_0016_2129 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0016_2129.class, "capitalizeTitle", "./txt_file/Code_0016_2129.txt");
    }

    public String capitalizeTitle(String title) {
        if (title == null || title.length() == 0) return title;
        char[] cs = title.toCharArray();
        int j = 0;
        int i = 0;
        for (; i < cs.length; i++) {
            char c = cs[i];
            if (c == ' ') {
                if (i - j > 2) {
                    cs[j] = upper(cs[j]);
                }
                j = i + 1;
                continue;
            }
            cs[i] = lower(c);
        }
        if (i - j > 2) {
            cs[j] = upper(cs[j]);
        }
        return new String(cs);
    }

    public static char upper(char c) {
        if ('A' <= c && c <= 'Z') {
            return c;
        }
        if ('a' <= c && c <= 'z') {
            return (char) (c - 'a' + 'A');
        }
        return c;
    }

    public static char lower(char c) {
        if ('a' <= c && c <= 'z') {
            return c;
        }
        if ('A' <= c && c <= 'Z') {
            return (char) (c - 'A' + 'a');
        }
        return c;
    }

}
