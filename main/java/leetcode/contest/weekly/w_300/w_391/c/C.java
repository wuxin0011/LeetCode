package leetcode.contest.weekly.w_300.w_391.c;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url
 * @title
 */
public class C {
    public static void main(String[] args) {
        IoUtil.testUtil(C.class, IoUtil.DEFAULT_METHOD_NAME, "C.txt");
    }


    public long countAlternatingSubarrays(int[] nums) {
        int n = nums.length;
        long ans = 0;
        int r = 0, l = 0;
        int pre = -1;
        while (r < n) {
            while (r < n && (pre == -1 || pre != nums[r])) {
                pre = nums[r];
                r++;
            }
            int v = r - l;

            // 计算当前子数组中所有子数组的数量并加到答案中
            ans += (long) v * (v + 1) / 2;

            l = r;
            r++;
            if (r == n) {
                ans++;
            }
        }
        return ans;
    }
}
