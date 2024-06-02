package leetcode.top_interview_150.trapping_rain_water;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description: 接雨水
 */
public class trapping_rain_water {

    public static void main(String[] args) {
        IoUtil.testUtil(trapping_rain_water.class, "trap");
        // IoUtil.testUtil(trapping_rain_water.class, "trap1");
        // IoUtil.testUtil(trapping_rain_water.class, "trap2");
    }


    public int trap(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int n = height.length;
        int ans = 0;
        int l = 0, r = n - 1;
        int pre = 0, suf = 0;
        while (l <= r) {
            pre = Math.max(pre, height[l]);
            suf = Math.max(suf, height[r]);
            if (pre > suf) {
                ans += (suf - height[r]);
                r--;
            } else {
                ans += (pre - height[l]);
                l++;
            }
        }
        return ans;
    }


    public int trap1(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int n = height.length;
        int[] pre_max = new int[n];
        for (int i = 0; i < n; i++) {
            pre_max[i] = Math.max(pre_max[Math.max(i - 1, 0)], height[i]);
        }
        int max = height[n - 1];
        int ans = 0;
        for (int i = n - 2; i >= 0; i--) {
            max = Math.max(max, height[i]);
            ans += Math.min(max, pre_max[i]) - height[i];
        }
        return ans;
    }


    public int trap2(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int n = height.length;
        int[] pre_max = new int[n];
        for (int i = 0; i < n; i++) {
            pre_max[i] = Math.max(pre_max[Math.max(i - 1, 0)], height[i]);
        }

        int[] suf_max = new int[n];
        suf_max[n - 1] = height[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suf_max[i] = Math.max(suf_max[i + 1], height[i]);
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            ans += Math.min(pre_max[i], suf_max[i]) - height[i];
        }
        return ans;
    }




}
