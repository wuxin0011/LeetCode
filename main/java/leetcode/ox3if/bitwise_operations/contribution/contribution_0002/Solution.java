package leetcode.ox3if.bitwise_operations.contribution.contribution_0002;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2425. 所有数对的异或和
 * <p>
 * 给你两个下标从 0开始的数组nums1 和nums2，两个数组都只包含非负整数。
 * 请你求出另外一个数组nums3，包含 nums1和 nums2中 所有数对的异或和（nums1中每个整数都跟 nums2中每个整数 恰好匹配一次）。
 * 请你返回 nums3中所有整数的 异或和。
 * <p>
 * 示例 1：
 * 输入：nums1 = [2,1,3], nums2 = [10,2,5,0]
 * 输出：13
 * 解释：
 * 一个可能的 nums3 数组是 [8,0,7,2,11,3,4,1,9,1,6,3] 。
 * 所有这些数字的异或和是 13 ，所以我们返回 13 。
 * <p>
 * 示例 2：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：0
 * 解释：
 * 所有数对异或和的结果分别为 nums1[0] ^ nums2[0] ，nums1[0] ^ nums2[1] ，nums1[1] ^ nums2[0] 和 nums1[1] ^ nums2[1] 。
 * 所以，一个可能的 nums3 数组是 [2,5,1,6] 。
 * 2 ^ 5 ^ 1 ^ 6 = 0 ，所以我们返回 0 。
 * <p>
 * 提示：
 * 1 <= nums1.length, nums2.length <= 10^5
 * 0 <= nums1[i], nums2[j] <= 10^9
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/bitwise-xor-of-all-pairings
 * @title: 所有数对的异或和
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "xorAllNums", "in.txt");
    }

    // [a,b] [c,d]
    // [a ^ c,a ^ d,b ^ c, b ^ d]
    // a ^ c ^ a ^ d ^ b ^ c ^ b ^ d
    // 0

    // [a,b] [c]
    // [a ^ c,b ^ c]
    // a ^ b

    // [a,b,c] [d]
    // ad bd cd
    //
    public int xorAllNums(int[] nums1, int[] nums2) {
        int ans = 0;
        if(nums2.length % 2 == 1){
            for (int num : nums1) {
                ans ^= num;
            }
            System.out.println("ans = " + ans);
        }
        if(nums1.length % 2 == 1){
            for (int num : nums2) {
                ans ^= num;
            }
        }
        return ans;
    }


}