package leetcode.ox3if.bitwise_operations.contribution.contribution_0001;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1863. 找出所有子集的异或总和再求和
 * <p>
 * 一个数组的 异或总和 定义为数组中所有元素按位 XOR 的结果；如果数组为 空 ，则异或总和为 0 。
 * 例如，数组[2,5,6] 的 异或总和 为 2 XOR 5 XOR 6 = 1 。
 * 给你一个数组 nums ，请你求出 nums 中每个 子集 的 异或总和 ，计算并返回这些值相加之 和 。
 * 注意：在本题中，元素 相同 的不同子集应 多次 计数。
 * 数组 a 是数组 b 的一个 子集 的前提条件是：从 b 删除几个（也可能不删除）元素能够得到 a 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,3]
 * 输出：6
 * 解释：[1,3] 共有 4 个子集：
 * - 空子集的异或总和是 0 。
 * - [1] 的异或总和为 1 。
 * - [3] 的异或总和为 3 。
 * - [1,3] 的异或总和为 1 XOR 3 = 2 。
 * 0 + 1 + 3 + 2 = 6
 * <p>
 * 示例 2：
 * 输入：nums = [5,1,6]
 * 输出：28
 * 解释：[5,1,6] 共有 8 个子集：
 * - 空子集的异或总和是 0 。
 * - [5] 的异或总和为 5 。
 * - [1] 的异或总和为 1 。
 * - [6] 的异或总和为 6 。
 * - [5,1] 的异或总和为 5 XOR 1 = 4 。
 * - [5,6] 的异或总和为 5 XOR 6 = 3 。
 * - [1,6] 的异或总和为 1 XOR 6 = 7 。
 * - [5,1,6] 的异或总和为 5 XOR 1 XOR 6 = 2 。
 * 0 + 5 + 1 + 6 + 4 + 3 + 7 + 2 = 28
 * <p>
 * 示例 3：
 * 输入：nums = [3,4,5,6,7,8]
 * 输出：480
 * 解释：每个子集的全部异或总和值之和为 480 。
 * <p>
 * 提示：
 * 1 <= nums.length <= 12
 * 1 <= nums[i] <= 20
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/sum-of-all-subset-xor-totals
 * @title: 找出所有子集的异或总和再求和
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "subsetXORSum", "in.txt");
        // IoUtil.testUtil(Solution.class, "test", "in.txt");
    }

    int ans = 0;

    public int subsetXORSum(int[] nums) {
        dfs(nums, 0, 0);
        return ans;
    }

    public void dfs(int[] nums, int i, int val) {
        if (i == nums.length) {
            ans += val;
            return;
        }
        dfs(nums, i + 1, val ^ nums[i]);
        dfs(nums, i + 1, val);
    }

    List<List<Integer>> res = new ArrayList<>();

    public int test(int[] nums) {
        System.out.println("nums = " + Arrays.toString(nums));
        f(nums, 0, new ArrayList<>());
        for (List<Integer> re : res) {
            System.out.println(re);
        }
        return 0;
    }

    public void f(int[] nums, int i, List<Integer> path) {
        if (i == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        f(nums, i + 1, path);
        path.add(nums[i]);
        f(nums, i + 1, path);
        path.remove(path.size() - 1);


    }


}