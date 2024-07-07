package leetcode.contest.weekly.w_400.w_405.a;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-405/problems/find-the-encrypted-string
 * @title: 找出加密后的字符串
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "getEncryptedString", "A.txt");
    }


    public String getEncryptedString(String s, int k) {
        char[] a = s.toCharArray();
        char[] ans = new char[a.length];
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int id = (i + k) % n;
            ans[i] = a[id];
        }
        return new String(ans);
    }


}