package leetcode.contest.weekly.w_400.w_453;

import code_generation.utils.IoUtil;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-453/problems/minimum-steps-to-convert-string-with-operations">字符串转换需要的最小操作数</a>
 * @title: 字符串转换需要的最小操作数
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "minOperations", "D.txt");
    }

    private static final int MOD = (int) 1e9 + 7;


    public int minOperations(String S, String T) {
        int[] s = S.chars().map(x -> x - 'a').toArray();
        int[] t = T.chars().map(x -> x - 'a').toArray();
        int n = s.length;
        int cnt1 = 0;
        for (int i = 0; i < n; i++) {
            if (s[i] != t[i]) cnt1++;
        }
        if (cnt1 <= 1) return cnt1;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, cnt1);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            int res = cnt1;
            for (int j = 0; j <= i; j++) {
                int[] s0 = new int[i - j + 1];
                int[] t0 = new int[i - j + 1];
                System.arraycopy(s, j, s0, 0, i - j + 1);
                System.arraycopy(t, j, t0, 0, i - j + 1);
                int swapCnt = swapCount(s0, t0);
                int revCnt = 1;
                //System.out.println("a " + Arrays.toString(s0));
                reverse(s0);
                // System.out.println("b " + Arrays.toString(s0));
                if (!Arrays.equals(t0, s0)) {
                    revCnt += swapCount(s0, t0);
                }
                res = Math.min(res,dp[j] + Math.min(swapCnt, revCnt));
            }
            dp[i + 1] = Math.min(res,dp[i + 1]);

        }

        return dp[n];
    }


    public static void reverse(int[] a) {
        int l = 0, r = a.length - 1;
        while (l < r) {
            int t = a[l];
            a[l] = a[r];
            a[r] = t;
            l++;
            r--;
        }
    }

    public static int swapCount(int[] s, int[] t) {
        Map<Pair<Integer, Integer>, Integer> cntMap = new HashMap<>();
        // swap
        int cnt = 0;
        for (int i = 0; i < s.length; i++) {
            int x = s[i];
            int y = t[i];
            if (x == y) continue;
            Pair<Integer, Integer> xy = new Pair<>(x, y);
            Pair<Integer, Integer> yx = new Pair<>(y, x);
            if (cntMap.getOrDefault(yx, 0) > 0) {
                cntMap.merge(yx, -1, Integer::sum);
            } else {
                cntMap.merge(xy, 1, Integer::sum);
                // cnt 这里是统计不交换 由于题目可以转换因此为了让s变成t因此最差策略也是 每个字符变换
                // 后面遇到需要交换的就不用统计了 少统计一次
                cnt++;
            }
        }
        return cnt;
    }


}