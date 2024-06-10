package leetcode.ox3if.math.a_number_theory.a_Judging_prime_numbers.Solution_0002;

import code_generation.utils.IoUtil;

/**
 * 762. 二进制表示中质数个计算置位
 *
 * 给你两个整数left和right ，在闭区间 [left, right]范围内，统计并返回 计算置位位数为质数 的整数个数。
 * 计算置位位数 就是二进制表示中 1 的个数。
 * 	例如， 21的二进制表示10^101有 3 个计算置位。
 *
 * 示例 1：
 * 输入：left = 6, right = 10
 * 输出：4
 * 解释：
 * 6 -> 110 (2 个计算置位，2 是质数)
 * 7 -> 111 (3 个计算置位，3 是质数)
 * 9 -> 1001 (2 个计算置位，2 是质数)
 * 10-> 10^10 (2 个计算置位，2 是质数)
 * 共计 4 个计算置位为质数的数字。
 *
 * 示例 2：
 * 输入：left = 10, right = 15
 * 输出：5
 * 解释：
 * 10 -> 10^10 (2 个计算置位, 2 是质数)
 * 11 -> 10^11 (3 个计算置位, 3 是质数)
 * 12 -> 1100 (2 个计算置位, 2 是质数)
 * 13 -> 110^1 (3 个计算置位, 3 是质数)
 * 14 -> 1110 (3 个计算置位, 3 是质数)
 * 15 -> 1111 (4 个计算置位, 4 不是质数)
 * 共计 5 个计算置位为质数的数字。
 *
 * 提示：
 * 	1 <= left <= right <= 10^6
 * 	0 <= right - left <= 10^4
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/prime-number-of-set-bits-in-binary-representation
 * @title: 二进制表示中质数个计算置位
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"countPrimeSetBits","in.txt");
    }
     

    public int countPrimeSetBits(int left, int right) {
        int cnt = 0;
        for(int i = left;i<=right;i++) {
            if(isPrime(Integer.bitCount(i))){
                cnt++;
            }
        }
        return cnt;
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