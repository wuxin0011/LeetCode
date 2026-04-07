package leetcode.contest.weekly.w_400.w_496;

import code_generation.utils.IoUtil;
/**
 * @author: qitongwei
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-496/problems/minimum-operations-to-achieve-at-least-k-peaks">产生至少 K 个峰值的最少操作次数</a>
 * @title: 产生至少 K 个峰值的最少操作次数
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "minOperations", "D.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    static int N = 5005, inf = 0x3fffffff;
    static int memo[][] = new int[N][N >> 1], a[] = new int[N];

    static int k;

    public int minOperations(int[] nums, int k0) {
        k = k0;
        int n = nums.length;
        if (k > n / 2) return -1;
        if (k == 0) return 0;
        if (n < 3) {
            if (nums[0] != nums[1]) {
                return 0;
            }
            return k > 1 ? -1 : 1;
        }
        int cnt = 0;
        if (nums[0] > nums[1] && nums[0] > nums[n - 1]) cnt++;
        if (nums[n - 1] > nums[0] && nums[n - 1] > nums[n - 2]) cnt++;
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) cnt++;
        }
        if (cnt >= k) return 0;
        for (int i = 0; i < n; i++) {
            a[i] = Math.max(0, Math.max(nums[(i - 1 + n) % n], nums[(i + 1) % n]) + 1 - nums[i]);
        }
        clear(n);
        int v1 = dfs(0, 0, n - 1);
        clear(n);
        int v2 = dfs(1, 0, n);
        int v = Math.min(v1, v2);
        return v < inf ? v : -1;
    }

    static void clear(int n) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                memo[i][j] = -1;
            }
        }
    }

    static int dfs(int i, int cnt, int r) {
        if (i >= r) {
            return cnt < k ? inf : 0;
        }
        if (memo[i][cnt] != -1) return memo[i][cnt];
        return memo[i][cnt] = Math.min(dfs(i + 1, cnt, r), dfs(i + 2, cnt + 1, r) + a[i]);
    }


}