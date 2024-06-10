package leetcode.ox3if.math.a_number_theory.a_Judging_prime_numbers.Solution_0001;

import code_generation.utils.IoUtil;

/**
 * 2614. 对角线上的质数
 * <p>
 * 给你一个下标从 0 开始的二维整数数组 nums 。
 * 返回位于 nums 至少一条 对角线 上的最大 质数 。如果任一对角线上均不存在质数，返回 0 。
 * 注意：
 * 如果某个整数大于 1 ，且不存在除 1 和自身之外的正整数因子，则认为该整数是一个质数。
 * 如果存在整数 i ，使得nums[i][i] = val 或者nums[i][nums.length - i - 1]= val ，则认为整数 val 位于 nums 的一条对角线上。
 * 在上图中，一条对角线是 [1,5,9] ，而另一条对角线是 [3,5,7] 。
 * <p>
 * 示例 1：
 * 输入：nums = [[1,2,3],[5,6,7],[9,10,11]]
 * 输出：11
 * 解释：数字 1、3、6、9 和 11 是所有 "位于至少一条对角线上" 的数字。由于 11 是最大的质数，故返回 11 。
 * <p>
 * 示例 2：
 * 输入：nums = [[1,2,3],[5,17,7],[9,11,10]]
 * 输出：17
 * 解释：数字 1、3、9、10 和 17 是所有满足"位于至少一条对角线上"的数字。由于 17 是最大的质数，故返回 17 。
 * <p>
 * 提示：
 * 1 <= nums.length <= 300
 * nums.length == numsi.length
 * 1 <= nums[i][j]<= 4*10^6
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/prime-in-diagonal
 * @title: 对角线上的质数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "diagonalPrime", "in.txt");
    }


    public int diagonalPrime(int[][] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (isPrime(nums[i][i])) {
                ans = Math.max(ans, nums[i][i]);
            }
            if (isPrime(nums[i][n - i - 1])) {
                ans = Math.max(ans, nums[i][n - i - 1]);
            }
        }
        return ans;
    }

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