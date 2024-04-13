package leetcode.contest.biweekly.bi_100.bi_128.a;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/biweekly-contest-128/problems/score-of-a-string
 * @title: 字符串的分数
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"scoreOfString","A.txt");
    }
     

    public int scoreOfString(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 1; i < s.length(); i++) {
            ans += Math.abs(s.charAt(i)-s.charAt(i-1));
        }
        return ans;
    }

  

}