package leetcode.contest.weekly.w_300.w_318.b;

import code_generation.utils.IoUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: wuxin0011
 * @Description:
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class,"maximumSubarraySum");
    }

    public long maximumSubarraySum(int[] nums, int k) {
        long ans = 0,tot = 0;
        if (nums == null || nums.length == 0) return ans;
        int n = nums.length, r = 0, l = 0;
        Set<Integer> set = new HashSet<>();

        while (r < n) {

            int cur = nums[r];
            while (set.contains(cur)) {
                set.remove(nums[l]);
                tot -= nums[l];
                l++;
            }
            set.add(cur);
            tot += cur;
            if (r - l + 1 == k) {
                ans = Math.max(tot, ans);
                set.remove(nums[l]);
                tot -= nums[l];
                l++;
            }
            r++;
        }


        return ans;
    }
}
