package leetcode.contest.weekly.w_300.w_398.d;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-398/problems/find-number-of-ways-to-reach-the-k-th-stair
 * @title: 到达第 K 级台阶的方案数
 */
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "waysToReachStair", "D.txt");
    }

    Map<String, Integer> map;

    public int waysToReachStair(int k) {
        map = new HashMap<>();
        return f(1, k, 0, 0);
    }

    public int f(int i, int k, int jump, int use) {
        if (i > k + 1) { // 按照题目信息 大于 k +1 不可能回到 K
            return 0;
        }
        String key = buildKey(i, jump, use);
        if (map.containsKey(key)) {
            return map.get(key);
        }
        int ans = i == k ? 1 : 0;
        ans += f(i + (1 << jump), k, jump + 1, 0);
        if (use == 0 && i > 0) {
            ans += f(i - 1, k, jump, 1);
        }
        map.put(key, ans);
        return ans;
    }

    public String buildKey(int i, int j, int u) {
        return ":" + i + ":" + j + ":" + u;
    }


}