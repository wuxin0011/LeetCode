package leetcode.contest.weekly.w_200.w_232.d;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url https://leetcode.cn/problems/maximum-score-of-a-good-subarray/
 * @title
 */
@SuppressWarnings("all")
public class D {
    public static void main(String[] args) {
        IoUtil.testUtil(D.class, IoUtil.DEFAULT_METHOD_NAME, "D.txt");
    }

    public int maximumScore(int[] nums, int k) {
        int ans = nums[k];
        int n = nums.length;
        for (int l = k, mi = nums[k], r = k; l > 0 || r < n - 1; ) {
            if (r == n - 1 || l > 0 && nums[l - 1] > nums[r + 1]) { // left > right , left--
                l--;
                mi = Math.min(mi, nums[l]);
            } else {
                r++;
                mi = Math.min(mi, nums[r]);
            }
            ans = Math.max(ans, (r - l + 1) * mi);
        }
        return ans;
    }


}
