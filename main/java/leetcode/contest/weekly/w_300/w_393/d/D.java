package leetcode.contest.weekly.w_300.w_393.d;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-393/problems/minimum-sum-of-values-by-dividing-array
 * @title: 划分数组得到最小的值之和
 */
public class D {

    public static void main(String[] args) {
        IoUtil.testUtil(D.class, "minimumValueSum", "D.txt");
    }


    int[] nums, andValues;
    int ans = Integer.MAX_VALUE;

    public int minimumValueSum(int[] nums, int[] andValues) {
        this.nums = nums;
        this.andValues = andValues;
        dfs(0, 0, -1);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public int dfs(int i, int j, int val) {
        if (i == nums.length) {
            return j == andValues.length ? 1 : Integer.MAX_VALUE;
        }
        if (j == andValues.length) {
            return Integer.MAX_VALUE;
        }
        val &= nums[i];
        if (val > nums[j]) {
            return Integer.MAX_VALUE;
        }
        if (val == andValues[j]) {
            ans = Math.min(ans, dfs(i + 1, j + 1, 0) + nums[i]);
        } else {
            return dfs(i + 1, j, val);
        }
        return val;
    }


}