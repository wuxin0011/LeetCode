package leetcode.contest.weekly.w_300.w_399.b;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-399/problems/string-compression-iii
 * @title: 压缩字符串 III
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "compressedString", "B.txt");
    }


    public String compressedString(String word) {
        char[] chs = word.toCharArray();
        StringBuilder sb = new StringBuilder();
        int l = 0, r = 0;
        while (r < chs.length) {
            if (r - l < 9 && chs[r] == chs[l]) {
                r++;
                continue;
            }
            sb.append(r - l);
            sb.append(chs[l]);
            l = r;
        }

        if (r != l) {
            sb.append(r - l);
            sb.append(chs[l]);
        }


        return sb.toString();
    }


}