package leetcode.ox3if.dp.knapsack.a_01_knaspack.Solution_0001;

import code_generation.annotation.TestCaseGroup;
import code_generation.utils.IoUtil;

/**
 * 416. 分割等和子集
 * <p>
 * 给你一个 只包含正整数 的 非空 数组nums 。
 * 请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 示例 1：
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 * <p>
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/partition-equal-subset-sum
 * @title: 分割等和子集
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "canPartition", "in.txt");
    }


    @TestCaseGroup(start = 3)
    public boolean canPartition(int[] nums) {

        // 选择一部分分开 那么 总和必须为偶数
        int s = 0;
        for (int num : nums) {
            s += num;
        }
        if (s % 2 == 1) {
            return false;
        }
        s /= 2;

        int n = nums.length;
        boolean[] f = new boolean[s + 1];
        f[0] = true;
        for (int num : nums) {
            for (int c = s; c >= num; c--) {
                f[c] |= f[c - num];
            }
            if (f[s]) {
                return true;
            }
        }
        return f[s];
    }



    public boolean canPartition1(int[] nums) {

        // 选择一部分分开 那么 总和必须为偶数
        int s = 0;
        for (int num : nums) {
            s += num;
        }
        if (s % 2 == 1) {
            return false;
        }
        s /= 2;

        int n = nums.length;
        boolean[][] f = new boolean[n + 1][s + 1];
        f[0][0] = true;
        for (int i = 0; i < n; i++) {
            for (int c = 0; c <= s; c++) {
                if (c < nums[i]) {
                    f[i + 1][c] = f[i][c];
                } else {
                    f[i + 1][c] = f[i][c] | f[i][c - nums[i]];
                }
                if (f[n][s]) {
                    return true;
                }
            }

        }
        return f[n][s];
    }

}