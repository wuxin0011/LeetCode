package leetcode.contest.biweekly.bi_100.bi_131.b;

import code_generation.utils.IoUtil;

/**
 * 100303. 查询数组中元素的出现位置
 * <p>
 * 给你一个整数数组nums，一个整数数组queries和一个整数x。
 * 对于每个查询queries[i]，你需要找到nums中第queries[i]个x的位置，并返回它的下标。如果数组中x的出现次数少于queries[i]，该查询的答案为 -1 。
 * 请你返回一个整数数组answer，包含所有查询的答案。
 * <p>
 * 示例 1：
 * 输入：nums = [1,3,1,7], queries = [1,3,2,4], x = 1
 * 输出：[0,-1,2,-1]
 * 解释：
 * 第 1 个查询，第一个 1 出现在下标 0 处。
 * 第 2 个查询，nums中只有两个 1 ，所以答案为 -1 。
 * 第 3 个查询，第二个 1 出现在下标 2 处。
 * 第 4 个查询，nums中只有两个 1 ，所以答案为 -1 。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3], queries = [10], x = 5
 * 输出：[-1]
 * 解释：
 * 第 1 个查询，nums中没有 5 ，所以答案为 -1 。
 * <p>
 * 提示：
 * 1 <= nums.length, queries.length <= 10^5
 * 1 <= queries[i] <= 10^5
 * 1 <= nums[i], x <= 10^4
 *
 * @author: wuxin0011
 * @Description:
 * @url
 * @title
 */
public class B {
    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "occurrencesOfElement", "B.txt");
    }

    public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        int n = nums.length;
        int[][] sums = new int[n + 1][2];

        for (int i = 0; i < n; i++) {
            sums[i + 1][0] = sums[i][0] + (nums[i] == x ? 1 : 0);
            sums[i + 1][1] = nums[i] == x ? i : sums[i][0];
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = query(sums, queries[i]);
        }
        return ans;
    }

    public static int query(int[][] sums, int cnt) {
        int n = sums.length;
        int mx = sums[n - 1][0];
        if (cnt > mx) {
            return -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (sums[mid][0] >= cnt) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return sums[l][1];
    }
}
