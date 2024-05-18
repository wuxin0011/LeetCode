package leetcode.ox3if.data_struct.pre_sum.hash.hash_0002;

import code_generation.utils.IoUtil;
/**
 * 1524. 和为奇数的子数组数目
 * <p>
 * 给你一个整数数组arr。
 * 请你返回和为 奇数的子数组数目。
 * 由于答案可能会很大，请你将结果对10^9 + 7取余后返回。
 * <p>
 * 示例 1：
 * 输入：arr = [1,3,5]
 * 输出：4
 * 解释：所有的子数组为 [[1],[1,3],[1,3,5],[3],[3,5],[5]] 。
 * 所有子数组的和为 [1,4,9,3,8,5].
 * 奇数和包括 [1,9,3,5] ，所以答案为 4 。
 * <p>
 * 示例 2 ：
 * 输入：arr = [2,4,6]
 * 输出：0
 * 解释：所有子数组为 [[2],[2,4],[2,4,6],[4],[4,6],[6]] 。
 * 所有子数组和为 [2,6,12,4,10,6] 。
 * 所有子数组和都是偶数，所以答案为 0 。
 * <p>
 * 示例 3：
 * 输入：arr = [1,2,3,4,5,6,7]
 * 输出：16
 * <p>
 * 示例 4：
 * 输入：arr = [100,100,99,99]
 * 输出：4
 * <p>
 * 示例 5：
 * 输入：arr = [7]
 * 输出：1
 * <p>
 * 提示：
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 100
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/number-of-sub-arrays-with-odd-sum
 * @title: 和为奇数的子数组数目
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "numOfSubarrays", "in.txt");
    }

    private static final int MOD = (int) 1e9 + 7;

    public int numOfSubarrays(int[] arr) {
        int ans = 0;
        int tot = 0;
        // 奇数 = 偶数 + 奇数
        // 奇数 = 奇数 + 奇数 + 奇数
        // => 转换成 0 1
        int o = 1, j = 0;
        for (int v : arr) {
            tot += v;
            if ((tot & 1) == 0) { // 和为偶数 => -前缀和为奇数
                o++;
                ans = (ans + j) % MOD;
            } else { // 和为奇数 =>  - 前缀和为偶数
                j++;
                ans = (ans + o) % MOD;
            }
        }
        return ans;
    }


}