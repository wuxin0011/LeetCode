package leetcode._0x3f_.math.a_number_theory.a_Judging_prime_numbers.Solution_0000;

import code_generation.utils.IoUtil;

/**
 * 3115. 质数的最大距离
 * <p>
 * 给你一个整数数组 nums。
 * 返回两个（不一定不同的）质数在 nums 中下标 的 最大距离。
 * <p>
 * 示例 1：
 * 输入： nums = [4,2,9,5,3]
 * 输出： 3
 * 解释： nums[1]、nums[3] 和 nums[4] 是质数。因此答案是 |4 - 1| = 3。
 * <p>
 * 示例 2：
 * 输入： nums = [4,8,2,8]
 * 输出： 0
 * 解释： nums[2] 是质数。因为只有一个质数，所以答案是 |2 - 2| = 0。
 * <p>
 * 提示：
 * 1 <= nums.length <= 3 * 10^5
 * 1 <= nums[i] <= 100
 * 输入保证 nums 中至少有一个质数。
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/maximum-prime-difference
 * @title: 质数的最大距离
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maximumPrimeDifference", "in.txt");
    }


    public int maximumPrimeDifference(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (!isPrime(nums[l])) {
            l++;
        }
        while (!isPrime(nums[r])) {
            r--;
        }
        return r - l;
    }


    /**
     * 判断一个数是不是质数
     * n
     * [1,n] 出了1和本身外其他数字 mod 均不为0
     *
     * @param n
     * @return
     */
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }


}