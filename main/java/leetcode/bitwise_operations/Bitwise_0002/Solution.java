package leetcode.bitwise_operations.Bitwise_0002;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/single-number-iii
 * @title: single-number-iii
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "singleNumber", "in.txt", false, false);
    }


    // 除了两个数字出现一次 其余出现偶数次数
    // 消除其他可以使用 异或运算
    // 如何分辨出 剩下两个数字呢 ？
    // 分组 ！ 之前知道在出现两个数字中找出唯一出现一次使用异或就能得到ans
    // 如果想办法将上面数组分成两组 是不是就能得到ans ？
    // 那么改如何分组呢 ？不同数字，对应数位上不可能都是一样的 必然有一个数位上与运算为1
    // 例如 [1,2,1,3,2,5]
    // 使用 异或运算之后
    // 3 ^ 5  ====> 0011 ^ 0111



    public int[] singleNumber(int[] nums) {
        int eor = 0;
        for (int num : nums) {
            eor ^= num;
        }

        int lowbit = eor & -eor;
        int[] ans = new int[2];
        for (int x : nums) {
            // 分组异或
            ans[(x & lowbit) == 0 ? 0 : 1] ^= x;
        }
        return ans;
    }

}