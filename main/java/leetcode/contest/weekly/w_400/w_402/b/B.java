package leetcode.contest.weekly.w_400.w_402.b;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-402/problems/count-pairs-that-form-a-complete-day-ii
 * @title: 构成整天的下标对数目 II
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "countCompleteDayPairs", "B.txt");
    }


    public long countCompleteDayPairs(int[] hours) {
        int[] cnts = new int[24];
        for (int hour : hours) {
            hour %= 24;
            cnts[hour]++;
        }
        long ans = 0;
        for (int i = 0; i <= 12; i++) {
            int a = cnts[i];
            if (a == 0) {
                continue;
            }
            if (i == 0 || i == 12) {
                ans += (a *1L* (a - 1) / 2);
            } else {
                int b = cnts[24 - i];
                ans += a * 1L * b;
            }
        }
        return ans;
    }


    public long countCompleteDayPairs1(int[] hours) {
        int n = hours.length;
        Map<Integer, Long> map = new HashMap<>();
        for (int hour : hours) {
            hour %= 24;
            map.put(hour, map.getOrDefault(hour, 0L) + 1);
        }

        long cnt = 0;
        for (int i = 0; i <= 12; i++) {
            long a = map.getOrDefault(i, 0L);
            if (a == 0) {
                continue;
            }
            if (i == 0 || i == 12) {
                cnt += (a * (a - 1)) / 2;
            } else {
                long b = map.getOrDefault(24 - i, 0L);
                cnt += a * b;
            }

        }
        return cnt;
    }


}