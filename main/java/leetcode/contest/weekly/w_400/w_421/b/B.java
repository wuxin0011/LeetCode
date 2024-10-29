package leetcode.contest.weekly.w_400.w_421.b;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-421/problems/total-characters-in-string-after-transformations-i">字符串转换后的长度 I</a>
 * @title: 字符串转换后的长度 I
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "lengthAfterTransformations", "B.txt");
    }


    static int mod = (int) 1e9 + 7;

    public int lengthAfterTransformations(String s, int t) {
        int[] a = s.chars().map(x -> x - 'a').toArray();
        long[] cnt = new long[26];
        for (int x : a) {
            cnt[x]++;
        }
        while (t > 0) {
            long[] origin = new long[26];
            for (int i = 0; i < 26; i++) {
                if (i == 25) {
                    origin[0] += cnt[25];
                    origin[1] += cnt[25];
                    origin[0] %= mod;
                    origin[1] %= mod;
                } else {
                    origin[i + 1] += cnt[i];
                    origin[i + 1] %= mod;
                }
            }
            cnt = origin;
            t--;
        }
        long ans = 0;
        for (int i = 0; i < 26; i++) {
            ans += cnt[i];
            ans %= mod;
        }
        return (int) (ans % mod);
    }


}