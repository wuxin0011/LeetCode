package leetcode.contest.biweekly.bi_100.bi_138.a;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-138/problems/find-the-key-of-the-numbers
 * @title: 求出数字答案
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "generateKey", "A.txt");
    }


    public int generateKey(int num1, int num2, int num3) {
        String s1 = Integer.toString((num1));
        String s2 = Integer.toString((num2));
        String s3 = Integer.toString((num3));
        int n = Math.max(Math.max(s1.length(), s2.length()), s3.length());
        if (s1.length() < n) {
            int x = n - s1.length();
            while (x > 0) {
                s1 = "0" + s1;
                x--;
            }
        }
        if (s2.length() < n) {
            int x = n - s2.length();
            while (x > 0) {
                s2 = "0" + s2;
                x--;
            }
        }
        if (s3.length() < n) {
            int x = n - s3.length();
            while (x > 0) {
                s3 = "0" + s3;
                x--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int x = Math.min(Math.min(s1.charAt(i), s2.charAt(i)), s3.charAt(i)) - '0';
            sb.append(x);
        }
        return Integer.parseInt(sb.toString());
    }


}