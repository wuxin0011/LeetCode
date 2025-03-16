package leetcode.contest.weekly.w_400.w_441;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-441/problems/zero-array-transformation-iv">零数组变换 IV</a>
 * @title: 零数组变换 IV
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "minZeroArray", "C.txt");
    }


    public int minZeroArray(int[] nums, int[][] queries) {
        if (nums == null) return 0;
        int n = nums.length;
        int mx = -0x3fffff;
        for (int num : nums) {
            mx = Math.max(mx, num);
        }
        if (mx <= 0) return 0;
        List<Integer>[] ls = new List[n];
        Arrays.setAll(ls, i -> new ArrayList<Integer>());
        boolean[] ok = new boolean[n];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int v = queries[i][2];
            for (int j = l; j <= r; j++) {
                if(ok[j]) continue;
                ls[j].add(v);
                ok[j] |= _01_back(ls[j], nums[j]);
            }
            if (all(ok)) {
                return i + 1;
            }
        }

        return -1;
    }


    public static boolean all(boolean[] ok) {
        for (boolean b : ok) {
            if (!b) return false;
        }
        return true;
    }
    public static boolean _01_back(List<Integer> a, int target) {
        int n = a.size();
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 0; i < a.size(); i++) {
            int x = a.get(i);
            for(int c = target;c >= x;c--) {
                dp[c] |= dp[c - x];
            }
            if(dp[target]) return true;
        }
        return dp[target];
    }

}