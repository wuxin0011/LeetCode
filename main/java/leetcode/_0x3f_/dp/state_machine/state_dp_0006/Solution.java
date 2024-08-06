package leetcode._0x3f_.dp.state_machine.state_dp_0006;

import code_generation.utils.IoUtil;

/**
 *
 *
 * 1493. 删掉一个元素以后全为 1 的最长子数组
 *
 * 给你一个二进制数组nums，你需要从中删掉一个元素。
 * 请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。
 * 如果不存在这样的子数组，请返回 0 。
 *
 * 提示 1：
 * 输入：nums = [1,1,0,1]
 * 输出：3
 * 解释：删掉位置 2 的数后，[1,1,1] 包含 3 个 1 。
 *
 * 示例 2：
 * 输入：nums = [0,1,1,1,0,1,1,0,1]
 * 输出：5
 * 解释：删掉位置 4 的数字后，[0,1,1,1,1,1,0,1] 的最长全 1 子数组为 [1,1,1,1,1] 。
 *
 * 示例 3：
 * 输入：nums = [1,1,1]
 * 输出：2
 * 解释：你必须要删除一个元素。
 * 提示：
 * 	1 <= nums.length <= 10^5
 * 	nums[i]要么是0要么是1 。
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/longest-subarray-of-1s-after-deleting-one-element
 * @title: longest-subarray-of-1s-after-deleting-one-element
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "longestSubarray", "in.txt");
    }


    public int longestSubarray(int[] a) {
        int u = 0;
        int n = a.length;
        int l = 0, r = 0, ans = 0;
        while (r < n) {
            if (a[r] == 0) u++;
            while (u > 1) {
                if (a[l] == 0) {
                    u--;
                }
                l++;
            }
            ans = Math.max(ans, r - l);
            r++;
        }
        return ans;
    }


}