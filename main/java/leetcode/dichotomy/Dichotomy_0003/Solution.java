package leetcode.dichotomy.Dichotomy_0003;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/maximum-running-time-of-n-computers
 * @title: maximum-running-time-of-n-computers
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maxRunTime", "in.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public long maxRunTime(int n, int[] batteries) {
        long sum = 0;
        long mx = 0;
        for (int battery : batteries) {
            sum += battery;
            if (battery > mx) {
                mx = battery;
            }
        }
        long l = 0, r = sum,ans = 0;
        // 最大值小于和 能够满足条件
        if (mx * n <= sum) {
            return sum / n;
        }
        while (l <= r) {
            long mid = l + ((r - l) >> 1);
            if (check(batteries, mid, n)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }


    public static boolean check(int[] batteries, long t, int n) {
        long sum = 0;
        for (int battery : batteries) {
            if (battery > t) {
                n--;
            }else{
                sum += battery;
            }
            // 如果遇到 tot >= n*t 就OK
            if(sum>=n*t){
                return true;
            }

        }
        return false;
    }

}