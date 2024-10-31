package leetcode.contest.weekly.w_400.w_401.c;

import code_generation.utils.IoUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-401/problems/maximum-total-reward-using-operations-i
 * @title: 执行操作可获得的最大总奖励 I
 */
public class C {


    // 具体解见第四题


    static class S2 {
        public static void main(String[] args) {
            IoUtil.testUtil(S2.class, "maxTotalReward", "C.txt");
        }
        public int maxTotalReward(int[] r) {
            return 10;
        }
    }

    static class Timeout {
        public static void main(String[] args) {
            IoUtil.testUtil(Timeout.class, "maxTotalReward", "C.txt");
        }


        public int maxTotalReward(int[] r) {
            HashSet<Integer> values = new HashSet<>();
            for (int val : r) {
                values.add(val);
            }
            r = new int[values.size()];
            int index = 0;
            int tot = 0;
            for (Integer val : values) {
                r[index] = val;
                tot += val;
                index++;
            }
            this.r = r;
            Arrays.sort(r);
            map = new HashMap<>();
            return dfs(0, 0);
        }

        int[] r;

        Map<String, Integer> map;

        public int dfs(int i, int v) {
            if (i == r.length) {
                return v;
            }
            String key = key(i, v);
            if (map.containsKey(key)) {
                return map.get(key);
            }
            int res = 0;
            if (v < r[i]) {
                res = Math.max(dfs(i + 1, v), dfs(i + 1, v + r[i]));
            } else {
                res = dfs(i + 1, v);
            }
            map.put(key, res);
            return res;
        }

        public static String key(int i, int v) {
            return i + ":" + v;
        }

    }

}