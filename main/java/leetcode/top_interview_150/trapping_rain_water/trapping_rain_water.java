package leetcode.top_interview_150.trapping_rain_water;

import leetcode.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description: 接雨水
 */
public class trapping_rain_water {

    public static void main(String[] args) {
        IoUtil.testUtil(trapping_rain_water.class, "trap");
    }

    public int trap(int[] height) {
        if (height == null || height.length < 2) return 0;
        int n = height.length;
        int[] pre_max = new int[n];
        int max = height[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, height[i]);
            pre_max[i] = max;
        }
        max = height[n - 1];
        int ans = 0;
        for (int i = n - 1; i >= 1; i--) {
            max = Math.max(max, height[i]);
            ans += Math.min(max, pre_max[i]) - height[i];
        }
        return ans;
    }
}
