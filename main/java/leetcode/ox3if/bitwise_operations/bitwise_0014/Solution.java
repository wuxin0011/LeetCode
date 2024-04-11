package leetcode.ox3if.bitwise_operations.bitwise_0014;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 * 2917. 找出数组中的 K-or 值
 *
 * 给你一个整数数组 nums 和一个整数 k 。让我们通过扩展标准的按位或来介绍 K-or 操作。在 K-or 操作中，如果在 nums 中，至少存在 k 个元素的第 i 位值为 1 ，那么 K-or 中的第 i 位的值是 1 。返回 nums 的 K-or 值。
 *
 * 示例 1：输入：nums = [7,12,9,8,9,15], k = 4输出：9解释：用二进制表示 numbers：NumberBit 3Bit 2Bit 1Bit 070111121100910018100091001151111Result = 91001位 0 在 7, 9, 9, 15 中为 1。位 3 在 12, 9, 8, 9, 15 中为 1。 只有位 0 和 3 满足。结果是 (1001)2 = 9。
 *
 * 示例 2：输入：nums = [2,12,1,11,4,5], k = 6输出：0解释：没有位在所有 6 个数字中都为 1，如 k = 6 所需要的。所以，答案为 0。
 *
 * 示例 3：输入：nums = [10,8,5,9,11,6,8], k = 1输出：15解释：因为 k == 1 ，数组的 1-or 等于其中所有元素按位或运算的结果。因此，答案为 10 OR 8 OR 5 OR 9 OR 11 OR 6 OR 8 = 15 。
 *
 * 提示：
 *
 *
 * 	1 <= nums.length <= 50
 * 	0 <= nums[i] < 231
 * 	1 <= k <= nums.length
 *
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/find-the-k-or-of-an-array
 * @title: find-the-k-or-of-an-array
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"findKOr","in.txt");
    }
     

    public int findKOr(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int v = 0;
            for (int num : nums) {
                v += (num>>i&1);
            }
            if(v>=k){
                ans |= 1 << i;
            }
        }
        return ans;
    }

  

}