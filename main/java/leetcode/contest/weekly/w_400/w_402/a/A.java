package leetcode.contest.weekly.w_400.w_402.a;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-402/problems/count-pairs-that-form-a-complete-day-i
 * @title: 构成整天的下标对数目 I
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "countCompleteDayPairs", "A.txt");
    }


    public int countCompleteDayPairs(int[] hours) {
        int n = hours.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int hour : hours) {
            hour %= 24;
            int t = 0;
            if(map.containsKey(hour)){
                t = map.get(hour) + 1;
            }else {
                t = 1;
            }
            map.put(hour, t);
        }

        int cnt = 0;
        for (int i = 0; i <= 12; i++) {
            int a = map.getOrDefault(i, 0);
            if (a == 0) {
                continue;
            }
            if (i == 0 || i == 12) {
                cnt += (a * (a - 1)) / 2;
            } else {
                int b = map.getOrDefault(24 - i, 0);
                cnt += a * b;
            }

        }
        return cnt;
    }

}