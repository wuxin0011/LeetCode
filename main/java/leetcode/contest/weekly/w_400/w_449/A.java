package leetcode.contest.weekly.w_400.w_449;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.List;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-449/problems/minimum-deletions-for-at-most-k-distinct-characters">不同字符数量最多为 K 时的最少删除数</a>
 * @title: 不同字符数量最多为 K 时的最少删除数
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"minDeletion","A.txt");
    }
    private static final int MOD = (int)1e9 + 7;

    public int minDeletion(String s, int k) {
        int[] cnt = new int[27];
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            cnt[c]++;
        }
        List<Integer> lt = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (cnt[i] == 0) continue;
            lt.add(cnt[i]);
        }
        lt.sort((a, b) -> a.compareTo(b));
        if (lt.size() <= k) return 0;
        int ans = 0;
        for (int i = 0; i < lt.size(); i++) {
            if (lt.size() - i == k) break;
            ans += lt.get(i);
        }
        return ans;
    }

  

}