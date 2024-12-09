package leetcode.contest.weekly.w_400.w_427.a;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: <a href="https://leetcode.cn/contest/weekly-contest-427/problems/transformed-array">转换数组</a>
 * @title: 转换数组
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "constructTransformedArray", "A.txt");
    }


    public int[] constructTransformedArray(int[] nums) {
        int[] ans = Arrays.copyOf(nums, nums.length);
        int n = nums.length;
        for (int i = 0, v; i < nums.length; i++) {
            v = nums[i];
            if (v > 0) {
                v += i;
                v %= n;
                ans[i] = nums[v];
            } else if (v < 0) {
                v = i - (-v % n) + n;
                v %= n;
//                System.out.println("nums[i] = " + nums[i] + " i = " + i + ",v = " + v + "n = " + n);
//                v = Math.abs(i + v) + n;
                ans[i] = nums[v];
            } else {
                ans[i] = v;
            }
        }
        return ans;
    }


}