package leetcode.contest.weekly.w_300.w_396.b;

import code_generation.utils.IoUtil;

import java.util.HashMap;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-396/problems/minimum-number-of-operations-to-make-word-k-periodic
 * @title: K周期字符串需要的最少操作次数
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "minimumOperationsToMakeKPeriodic", "B.txt");
    }


    public int minimumOperationsToMakeKPeriodic(String word, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        int n = word.length();
        int mx = 0;
        for (int i = 0; i < n; i += k) {
            String s = word.substring(i, i + k);
            int cnt = map.getOrDefault(s, 0) + 1;
            map.put(s, cnt);
            mx = Math.max(cnt,mx);
        }
        int tot = mx * k;
//        System.out.println(mx);
        return (n - tot) / k;
    }


}