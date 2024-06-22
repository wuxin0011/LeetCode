package leetcode.ox3if.bitwise_operations.and_or.and_or_0008;

import code_generation.utils.IoUtil;

/**
 *
 * 1521. 找到最接近目标值的函数值
 *
 *
 * Winston 构造了一个如上所示的函数func。
 * 他有一个整数数组arr和一个整数target，他想找到让|func(arr, l, r) - target|最小的 l和 r。
 * 请你返回|func(arr, l, r) - target|的最小值。
 * 请注意，func 的输入参数l 和r需要满足0 <= l, r < arr.length。
 *
 * 示例 1：
 * 输入：arr = [9,12,3,7,15], target = 5
 * 输出：2
 * 解释：所有可能的 [l,r] 数对包括 [[0,0],[1,1],[2,2],[3,3],[4,4],[0,1],[1,2],[2,3],[3,4],[0,2],[1,3],[2,4],[0,3],[1,4],[0,4]]， Winston 得到的相应结果为 [9,12,3,7,15,8,0,3,7,0,0,3,0,0,0] 。最接近 5 的值是 7 和 3，所以最小差值为 2 。
 *
 * 示例 2：
 * 输入：arr = [1000000,1000000,1000000], target = 1
 * 输出：999999
 * 解释：Winston 输入函数的所有可能 [l,r] 数对得到的函数值都为 1000000 ，所以最小差值为 999999 。
 *
 * 示例 3：
 * 输入：arr = [1,2,4,8,16], target = 0
 * 输出：0
 *
 * 提示：
 * 	1 <= arr.length <= 10^5
 * 	1 <= arr[i] <= 10^6
 * 	0 <= target <= 10^7
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/find-a-value-of-a-mysterious-function-closest-to-target
 * @title: 找到最接近目标值的函数值
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "closestToTarget", "in.txt");
    }

    public int closestToTarget(int[] arr, int target) {
        return minimumDifference(arr, target);
    }
    // https://leetcode.cn/contest/weekly-contest-400/problems/find-subarray-with-bitwise-and-closest-to-k/
    // 力扣改题目掩盖自己出原题骚操作哦
    // 新题目链接 将上面 and => or
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            ans = Math.min(ans, Math.abs(val - k));
            for (int j = i - 1; j >= 0; j--) {
                // 最核心的 部分
                if ((val & nums[j]) == nums[j]) {
                    break;
                }
                nums[j] &= val;
                ans = Math.min(ans, Math.abs(nums[j] - k));
            }
        }
        return ans;
    }
}