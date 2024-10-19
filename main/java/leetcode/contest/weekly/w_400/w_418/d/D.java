package leetcode.contest.weekly.w_400.w_418.d;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-418/problems/sorted-gcd-pair-queries
 * @title: 查询排序后的最大公约数
 */
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "gcdValues", "D.txt");
    }


    public int[] gcdValues(int[] nums, long[] queries) {
        int mx = -0x3ffffff;
        for (int x : nums) {
            mx = Math.max(x, mx);
        }
        int[] cnt_x = new int[mx + 1];
        for (int x : nums) {
            cnt_x[x]++;
        }
        long[] cnt_gcd = new long[mx + 1];


        for (int i = mx; i >= 1; i--) {
            long c = 0;
            // 容斥原理 + 枚举因子
            for (int j = i; j <= mx; j += i) {
                c += cnt_x[j];
                cnt_gcd[i] -= cnt_gcd[j];
            }
            cnt_gcd[i] += c * (c - 1) / 2;
        }


        for (int i = 2; i <= mx; i++) {
            cnt_gcd[i] += cnt_gcd[i - 1];
        }

//        System.out.println("sums = " + Arrays.toString(cnt_gcd));
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = lower_bound(cnt_gcd, queries[i] + 1);
        }
        return ans;
    }


    int lower_bound(long[] sums, long x) {
       int l = 0,r = sums.length - 1;
       while(l <= r) {
           int mid = l + ((r - l)>>1);
           if(sums[mid] >= x) {
               r = mid - 1;
           }else{
               l = mid + 1;
           }
       }
       return l;
    }


}