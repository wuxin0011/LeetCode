package leetcode.ox3if.data_struct.pre_sum.Solution_0002;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 2389. 和有限的最长子序列
 * <p>
 * 给你一个长度为 n的整数数组 nums ，和一个长度为 m 的整数数组 queries 。
 * 返回一个长度为 m 的数组 answer ，其中 answer[i] 是 nums 中 元素之和小于等于 queries[i] 的 子序列 的 最大 长度 。
 * 子序列 是由一个数组删除某些元素（也可以不删除）但不改变剩余元素顺序得到的一个数组。
 * <p>
 * 示例 1：
 * 输入：nums = [4,5,2,1], queries = [3,10,21]
 * 输出：[2,3,4]
 * 解释：queries 对应的 answer 如下：
 * - 子序列 [2,1] 的和小于或等于 3 。可以证明满足题目要求的子序列的最大长度是 2 ，所以 answer[0] = 2 。
 * - 子序列 [4,5,1] 的和小于或等于 10 。可以证明满足题目要求的子序列的最大长度是 3 ，所以 answer[1] = 3 。
 * - 子序列 [4,5,2,1] 的和小于或等于 21 。可以证明满足题目要求的子序列的最大长度是 4 ，所以 answer[2] = 4 。
 * <p>
 * 示例 2：
 * 输入：nums = [2,3,4,5], queries = [1]
 * 输出：[0]
 * 解释：空子序列是唯一一个满足元素和小于或等于 1 的子序列，所以 answer[0] = 0 。
 * <p>
 * 提示：
 * n == nums.length
 * m == queries.length
 * 1 <= n, m <= 1000
 * 1 <= nums[i], queries[i] <= 10^6
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/longest-subsequence-with-limited-sum
 * @title: 和有限的最长子序列
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "answerQueries", "in.txt");
    }


    public int[] answerQueries(int[] nums, int[] queries) {
        int n = nums.length;
        Arrays.sort(nums);
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            if (queries[i] < sum[0]) { // 比最小值还小
                // ignore
            } else if (sum[n] < queries[i]) { // 比最大值还大
                ans[i] = n;
            } else {
                ans[i] = lower_bound(sum, queries[i] + 1) - 1;
            }

        }
        //System.out.println(Arrays.toString(ans));
        return ans;
    }

    public static int lower_bound(int[] sum, int target) {
        int l = 0, r = sum.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (sum[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }


}