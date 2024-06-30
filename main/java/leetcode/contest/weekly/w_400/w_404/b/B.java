package leetcode.contest.weekly.w_400.w_404.b;

import code_generation.utils.IoUtil;

/**
 *
 * 100357. 找出有效子序列的最大长度 I
 *
 * 给你一个整数数组 nums。
 * nums 的子序列 sub 的长度为 x ，如果其满足以下条件，则称其为 有效子序列：
 * 	(sub[0] + sub[1]) % 2 == (sub[1] + sub[2]) % 2 == ... == (sub[x - 2] + sub[x - 1]) % 2
 * 返回 nums 的 最长的有效子序列 的长度。
 * 一个子序列指的是从原数组中删除一些元素（也可以不删除任何元素），剩余元素保持原来顺序组成的新数组。
 *
 * 示例 1：
 * 输入： nums = [1,2,3,4]
 * 输出： 4
 * 解释：
 * 最长的有效子序列是 [1, 2, 3, 4]。
 *
 * 示例 2：
 * 输入： nums = [1,2,1,1,2,1,2]
 * 输出： 6
 * 解释：
 * 最长的有效子序列是 [1, 2, 1, 2, 1, 2]。
 *
 * 示例 3：
 * 输入： nums = [1,3]
 * 输出： 2
 * 解释：
 * 最长的有效子序列是 [1, 3]。
 *
 * 提示：
 * 	2 <= nums.length <= 2 * 10^5
 * 	1 <= nums[i] <= 10^7
 * @author: wuxin0011
 * @Description:
 * @url https://leetcode.cn/problems/find-the-maximum-length-of-valid-subsequence-i/
 * @title
 */
public class B {
    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "maximumLength", "B.txt");
    }


    public int maximumLength(int[] nums) {
        return maximumLength(nums, 2);
    }

    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        if (n <= 2) return n;
        int ans = 0;
        for (int i = 0; i < k; i++) {
            ans = Math.max(ans, f(nums, i, k));
            if (ans == n) {
                return ans;
            }
        }
        return ans;
    }


    public static int f(int[] nums, int t, int k) {
        if (t == 0) {
            int a = help(nums, t, 0, 0, k);
            int b = help(nums, t, 1, 1, k);
            return Math.max(a, b);
        } else {
            int a = help(nums, t, 0, 1, k);
            int b = help(nums, t, 1, 0, k);
            return Math.max(a, b);
        }
    }


    public static int help(int[] nums, int t, int a, int b, int k) {
        int l = find(nums, 0, a, k);
        int n = nums.length;
        if (l == n) {
            return 0;
        }
        int r = find(nums, l + 1, b, k);
        if (r == n) {
            return 0;
        }
        int len = 1;
        while (r < n) {
            if ((nums[l] + nums[r]) % k == t) {
                l = r;
                len++;
            }
            r++;
        }
        return len;
    }

    public static int find(int[] nums, int st, int v, int k) {
        int n = nums.length;
        int l = st;
        while (l < n && nums[l] % k != v) {
            l++;
        }
        return l;
    }


}
