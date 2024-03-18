package leetcode.contest.biweekly.bi_100.bi_125.d;

import leetcode.utils.IoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wuxin0011
 * @Description: 图  脑筋急转弯！！
 * @url https://leetcode.cn/problems/find-the-maximum-sum-of-node-values/
 * @title 最大节点价值之和
 */
public class D {
    public static void main(String[] args) {
        IoUtil.testUtil(D.class,"maximumValueSum","in.txt");
        IoUtil.testUtil(D.class,"maximumValueSum2","in.txt");
    }

    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        int n = nums.length;
        List<Integer>[] g = new ArrayList[n];
        for(int i = 0;i<n;i++){
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            g[x].add(y);
            g[y].add(x);
        }
        return dfs(0, -1, g, nums, k)[0];
    }

    private long[] dfs(int x, int fa, List<Integer>[] g, int[] nums, int k) {
        long f0 = 0, f1 = Long.MIN_VALUE; // f[x][0] 和 f[x][1]
        for (int y : g[x]) {
            if (y != fa) {
                long[] r = dfs(y, x, g, nums, k);
                long t = Math.max(f1 + r[0], f0 + r[1]);
                f0 = Math.max(f0 + r[0], f1 + r[1]);
                f1 = t;
            }
        }
        return new long[]{Math.max(f0 + nums[x], f1 + (nums[x] ^ k)),
                Math.max(f1 + nums[x], f0 + (nums[x] ^ k))};
    }


    public long maximumValueSum2(int[] nums, int k, int[][] edges) {
        long f0 = 0, f1 = Long.MIN_VALUE;
        for (int x : nums) {
            long t = Math.max(f1 + x, f0 + (x ^ k));
            f0 = Math.max(f0 + x, f1 + (x ^ k));
            f1 = t;
        }
        return f0;
    }




}
