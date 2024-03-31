package leetcode.contest.biweekly.bi_100.bi_127.a;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url
 * @title
 */
public class A {
    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "minimumSubarrayLength", "A.txt");
    }


    public int minimumSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int ans = n + 1;
        int tot = 0;
        for (int num : nums) {
            tot |= num;
            if (tot >= k) {
                break;
            }
            if (num >= k) {
                return 1;
            }
        }
        if (tot < k) {
            return -1;
        }
        for (int i = 0; i < n; i++) {
            int v = nums[i];
            if (v >= k) {
                return 1;
            }
            for (int j = i + 1; j < n; j++) {
                v |= nums[j];
                if (v >= k) {
                    ans = Math.min(ans, j - i + 1);
                    break;
                }
            }
        }
        return ans == n + 1 ? -1 : ans;

    }
}
