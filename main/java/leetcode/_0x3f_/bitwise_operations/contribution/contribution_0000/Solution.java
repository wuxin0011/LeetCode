package leetcode._0x3f_.bitwise_operations.contribution.contribution_0000;

import code_generation.utils.RandomArrayUtils;

/**
 * 477. 汉明距离总和
 * <p>
 * 两个整数的汉明距离 指的是这两个数字的二进制数对应位不同的数量。
 * 给你一个整数数组 nums，请你计算并返回 nums 中任意两个数之间 汉明距离的总和 。
 * <p>
 * 示例 1：
 * 输入：nums = [4,14,2]
 * 输出：6
 * 解释：在二进制表示中，4 表示为 0100 ，14 表示为 1110 ，2表示为 0010 。（这样表示是为了体现后四位之间关系）
 * 所以答案为：
 * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6
 * <p>
 * 示例 2：
 * 输入：nums = [4,14,4]
 * 输出：4
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 10^9
 * 给定输入的对应答案符合 32-bit 整数范围
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/total-hamming-distance
 * @title: 汉明距离总和
 */
public class Solution {

    public static void main(String[] args) {
//        IoUtil.testUtil(Solution.class, "totalHammingDistance", "in.txt");
//        IoUtil.testUtil(Solution.class, "totalHammingDistanceError", "in.txt");
        boolean ok = true;
        Solution solution = new Solution();
        for (int i = 0; i < 1000; i++) {
            int[] ints = RandomArrayUtils.randomIntArray(RandomArrayUtils.int_10_4, 0, RandomArrayUtils.int_10_9);
            if (solution.totalHammingDistanceTimeOut(ints) != solution.totalHammingDistance(ints)) {
                ok = false;
                break;
            }
        }

        if (ok) {
            System.out.println("OK");
        } else {
            System.out.println("error");
        }

    }


    // 对每个数位1单独统计
    // 1 的 个数 * 0 的个数就是答案
    public int totalHammingDistance(int[] nums) {
        //
        int ans = 0;
        for (int k = 0; k < 32; k++) {
            int one = 0, zero = 0;
            for (int num : nums) {
                if ((num >> k & 1) == 1) {
                    one++;
                } else {
                    zero++;
                }
            }
            ans += (one * zero);
        }

        return ans;
    }


    public int totalHammingDistanceTimeOut(int[] nums) {
        //
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                ans += Integer.bitCount((nums[i] ^ nums[j]));
            }
        }
        return ans;
    }


}