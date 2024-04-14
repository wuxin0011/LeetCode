package leetcode.contest.weekly.w_300.w_393.a;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-393/problems/latest-time-you-can-obtain-after-replacing-characters
 * @title: 替换字��可以得到的最晚时间
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "findLatestTime", "A.txt");
    }


    public String findLatestTime(String s) {
        char[] ch = s.toCharArray();
        if (ch[0] == '?' || ch[1] == '?') {
            if (ch[0] == '?' && ch[1] == '?') {
                ch[0] = '1';
                ch[1] = '1';
            } else if (ch[0] != '?') {
                if (ch[0] == '0') {
                    ch[1] = '9';
                }
                if (ch[0] == '1') {
                    ch[1] = '1';
                }
            } else {
                if (ch[1] > '1') {
                    ch[0] = '0';
                } else {
                    ch[0] = '1';
                }
            }
        }

        if (ch[3] == '?' || ch[4] == '?') {
            if (ch[3] == '?' && ch[4] == '?') {
                ch[3] = '5';
                ch[4] = '9';
            } else if (ch[3] != '?') {
                ch[4] = '9';
            } else {
                ch[3] = '5';
            }
        }

        return new String(ch);
    }


}