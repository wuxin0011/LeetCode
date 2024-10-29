package leetcode.contest.weekly.w_400.w_421.c;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-421/problems/find-the-number-of-subsequences-with-equal-gcd">最大公约数相等的子序列数量</a>
 * @title: 最大公约数相等的子序列数量
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class,"subsequencePairCount","C.txt");
    }


    static int MOD = (int)1e9 + 7;
    public int subsequencePairCount(int[] nums) {
        this.nums = nums;
        this.memo = new Long[nums.length][201][201];
        return Math.max((int)((dfs(nums.length - 1,0,0) - 1 + MOD) % MOD),0);
	}


    Long[][][] memo;
    int[] nums;

    public long dfs(int i,int j,int k) {
        if( i < 0) {
            return j == k ? 1 : 0;
        }
        long ans = 0;
        if(memo[i][j][k] != null) return memo[i][j][k];
        ans += dfs(i - 1,j,k);
        ans += dfs(i - 1,gcds[j][nums[i]],k);
        ans += dfs(i - 1,j,gcds[k][nums[i]]);
        ans %= MOD;
        return memo[i][j][k] = ans;
    }


    static int[][] gcds = new int[201][201];

    static {
        int n = gcds.length,m = gcds[0].length;
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < m;j++) {
                gcds[i][j] = gcd(i,j);
            }
        }
    }

    static int gcd(int i,int j) {
        while(j != 0) {
            int t = i % j;
            i = j;
            j = t;
        }
        return i;
    }



}