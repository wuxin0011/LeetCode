package leetcode.contest.weekly.w_400.w_453;

import code_generation.utils.IoUtil;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-453/problems/count-partitions-with-max-min-difference-at-most-k">统计极差最大为 K 的分割方式数</a>
 * @title: 统计极差最大为 K 的分割方式数
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class,"countPartitions","C.txt");
    }
    private static final int MOD = (int)1e9 + 7;

    static int N = (int)1e5;

    static int[] qmax = new int[N],qmin = new int[N];
    static int lmax,rmax,lmin,rmin;

    public int countPartitions(int[] a, int k) {
        int n = a.length;
        lmax = rmax = lmin = rmin = 0;
        long sums = 0;
        long[] dp = new long[n + 1];
        dp[0] = 1L;

        for (int i = 0,left = 0; i < n; i++) {
            sums += dp[i];

            while(lmin < rmin && a[i] <= a[qmin[rmin-1]]) rmin--;
            qmin[rmin++] = i;

            while(lmax < rmax && a[i] >= a[qmax[rmax-1]]) rmax--;
            qmax[rmax++] = i;

            while(lmin < rmin && lmax < rmax && a[qmax[lmax]] - a[qmin[lmin]] > k) {
                sums -= dp[left];
                left++;
                if(qmin[lmin] < left)lmin++;
                if(qmax[lmax] < left)lmax++;
            }

            dp[i + 1] = (sums + MOD) % MOD;
        }

        return (int)dp[n];
    }

  

}