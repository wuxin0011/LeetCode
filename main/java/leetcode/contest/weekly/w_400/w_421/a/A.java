package leetcode.contest.weekly.w_400.w_421.a;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-421/problems/find-the-maximum-factor-score-of-array">数组的最大因子得分</a>
 * @title: 数组的最大因子得分
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "maxScore", "A.txt");
    }


    public long maxScore(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return nums[0] * 1L * nums[0];
        }
        int n = nums.length;
        long ans = 0;
        for (int i = -1; i < n; i++) {
            ans = Math.max(ans, f(nums, i));
        }
        return ans;
    }



    long f(int[] arr, int idx) {
        int u = -1;
        for (int i = 0; i < arr.length; i++) {
            if (i == idx) continue;
            u = i;
            break;
        }
        long g = arr[u], c = arr[u];
        for (int i = 0; i < arr.length; i++) {
            if (idx == i || i == u) continue;
            g = gcd(g, arr[i]);
            c = lcm(c, arr[i]);
        }
        return g * c;
    }

    public static long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public static long lcm(long a, long b) {
        return (a / gcd(a, b) * b);
    }
}