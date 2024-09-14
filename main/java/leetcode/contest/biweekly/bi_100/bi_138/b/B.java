package leetcode.contest.biweekly.bi_100.bi_138.b;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-138/problems/hash-divided-string
 * @title: 哈希分割字符串
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "stringHash", "B.txt");
    }


    public String stringHash(String s, int k) {
        int n = s.length();
        char[] sk = new char[n];
        int size = 0;
        int sums = 0;
        for (int i = 0; i < n; i++) {
            sums += s.charAt(i) - 'a';
            if ((i + 1) % k == 0) {
                sk[size++] = (char) (sums % 26 + 'a');
                sums = 0;
            }
        }
        return new String(sk, 0, size);
    }


}