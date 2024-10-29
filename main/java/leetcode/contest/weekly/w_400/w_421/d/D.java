package leetcode.contest.weekly.w_400.w_421.d;

import code_generation.utils.IoUtil;

import java.util.List;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-421/problems/total-characters-in-string-after-transformations-ii">字符串转换后的长度 II</a>
 * @title: 字符串转换后的长度 II
 */
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "lengthAfterTransformations", "D.txt");
    }

    static int mod = (int) 1e9 + 7;
    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        int[] a = s.chars().map(x -> x - 'a').toArray();
        int[] ops = nums.stream().mapToInt(Integer::intValue).toArray();
        long[] cnt = new long[26];
        for (int x : a) {
            cnt[x]++;
        }
        while (t > 0) {
            long[] origin = new long[26];
            for (int i = 0; i < 26; i++) {
                for (int x = 1; x <= ops[i]; x++) {
                    origin[(x + i) % 26] += cnt[i];
                    origin[(x + i) % 26] %= mod;
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