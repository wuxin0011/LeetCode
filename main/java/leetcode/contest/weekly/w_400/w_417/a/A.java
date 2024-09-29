package leetcode.contest.weekly.w_400.w_417.a;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-417/problems/find-the-k-th-character-in-string-game-i
 * @title: 找出第 K 个字符 I
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "kthCharacter", "A.txt");
    }


    public char kthCharacter(int k) {
        char[] sk = new char[100001];
        sk[0] = 'a';
        int size = 1;
        while (size <= k) {
            char[] nxt = next(sk, size);
            for (int i = size; i < size + nxt.length; i++) {
                sk[i] = nxt[i - size];
            }
            size *= 2;
        }

        return sk[k - 1];
    }

    public char[] next(char[] cs, int size) {
        char[] a = new char[size];
        for (int i = 0; i < size; i++) {
            a[i] = (char) ((cs[i] - 'a' + 1 ) % 26 + 'a');
        }
        return a;
    }


}