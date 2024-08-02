package leetcode._0x3f_.bitwise_operations.contribution.contribution_0003;

import code_generation.utils.IoUtil;

/**
 * 2275. 按位与结果大于零的最长组合
 * <p>
 * 对数组nums 执行 按位与 相当于对数组nums 中的所有整数执行 按位与 。
 * 例如，对 nums = [1, 5, 3] 来说，按位与等于 1 & 5 & 3 = 1 。
 * 同样，对 nums = [7] 而言，按位与等于 7 。
 * 给你一个正整数数组 candidates 。计算 candidates 中的数字每种组合下 按位与 的结果。 candidates 中的每个数字在每种组合中只能使用 一次 。
 * 返回按位与结果大于 0 的 最长 组合的长度。
 * <p>
 * 示例 1：
 * 输入：candidates = [16,17,71,62,12,24,14]
 * 输出：4
 * 解释：组合 [16,17,62,24] 的按位与结果是 16 & 17 & 62 & 24 = 16 > 0 。
 * 组合长度是 4 。
 * 可以证明不存在按位与结果大于 0 且长度大于 4 的组合。
 * 注意，符合长度最大的组合可能不止一种。
 * 例如，组合 [62,12,24,14] 的按位与结果是 62 & 12 & 24 & 14 = 8 > 0 。
 * <p>
 * 示例 2：
 * 输入：candidates = [8,8]
 * 输出：2
 * 解释：最长组合是 [8,8] ，按位与结果 8 & 8 = 8 > 0 。
 * 组合长度是 2 ，所以返回 2 。
 * <p>
 * 提示：
 * 1 <= candidates.length <= 10^5
 * 1 <= candidates[i] <= 10^7
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/largest-combination-with-bitwise-and-greater-than-zero
 * @title: 按位与结果大于零的最长组合
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "largestCombination", "in.txt");
    }


    public int largestCombination(int[] candidates) {
        // 统计那个位置1的个数最多
        int[] cnts = new int[32];
        int ans = 0;
        for (int val : candidates) {
            int i = 0;
            while (val > 0) {
                cnts[i] += val % 2;
                val /= 2;
                i++;
            }

        }
        for (int cnt : cnts) {
            ans = Math.max(ans, cnt);
        }
        // System.out.println(Arrays.toString(cnts));
        return ans;
    }


    public int largestCombination1(int[] candidates) {


        // 统计那个位置1的个数最多
        int[] cnts = new int[32];
        int ans = 0;
        for (int val : candidates) {
            int c = 0;
            for (int i = 31; i >= 0; i--) {
                int id = 31 - i;
                cnts[i] += (val >> id & 1);
                c |= 1 << id;
                if (c > val) {
                    break;
                }
            }

        }
        for (int cnt : cnts) {
            ans = Math.max(ans, cnt);
        }
        // System.out.println(Arrays.toString(cnts));
        return ans;
    }


}