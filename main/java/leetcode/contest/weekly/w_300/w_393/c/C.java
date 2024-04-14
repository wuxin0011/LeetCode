package leetcode.contest.weekly.w_300.w_393.c;

import code_generation.utils.IoUtil;

import java.util.*;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-393/problems/kth-smallest-amount-with-single-denomination-combination
 * @title: 单面值组合的第K小金额
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "findKthSmallest", "C.txt");
        IoUtil.testUtil(C.class, "findKthSmallestLong", "C.txt");
    }


    public long findKthSmallest(int[] coins, int k) {
        Arrays.sort(coins);
        long[] ans = new long[k];
        Arrays.fill(ans,Long.MAX_VALUE);
        int[] ks = new int[coins.length];
        Arrays.fill(ks, 1);
        long pre = -1;
        int n = coins.length;
        for (int i = 0; i < k; i++) {
            long val = 0;
            int miIdx = 0;
            for (int j = 0; j < n; j++) {
                val = (long) ks[j] * coins[j];
                for (int z = 0; z < n; z++) {
                    if(z==j){
                        continue;
                    }
                }
            }
            ans[i] = val;
            ks[miIdx]++;
        }

        return ans[k - 1];
    }

    public long findKthSmallestLong(int[] coins, int k) {
        Arrays.sort(coins);
        List<Long> ans = new ArrayList<>();
        for (int coin : coins) {
            long cur = coin;
            ans.add(cur);
            for (int i = 1; i <= k; i++) {
                cur += coin;
                ans.add(cur);
            }
        }
        ans.sort(Long::compareTo);
        long v = 0;
        int i = 1;
        for (; i < ans.size() && k > 0; k--) {
            while (i<ans.size() && ans.get(i-1).equals(ans.get(i))){
                i++;
            }
        }
        return ans.get(i);
    }


}