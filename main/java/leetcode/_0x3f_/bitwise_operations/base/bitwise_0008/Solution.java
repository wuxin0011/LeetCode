package leetcode._0x3f_.bitwise_operations.base.bitwise_0008;

import code_generation.utils.IoUtil;

/**
 *
 *
 * 191. 位1的个数
 *
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中 设置位 的个数（也被称为汉明重量）。
 *
 * 示例 1：输入：n = 11输出：3解释：输入的二进制串 1011中，共有 3 个设置位。
 *
 * 示例 2：输入：n = 128输出：1解释：输入的二进制串 10000000中，共有 1 个设置位。
 *
 * 示例 3：输入：n = 2147483645输出：30解释：输入的二进制串 11111111111111111111111111111101 中，共有 30 个设置位。
 *
 * 提示：
 *
 *
 * 	1 <= n <= 231 - 1
 *
 *
 * 进阶：
 *
 *
 * 	如果多次调用这个函数，你将如何优化你的算法？
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/number-of-1-bits
 * @title: 位1的个数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"hammingWeight","in.txt");
    }
     

    public int hammingWeight(int n) {
        int cnt = 0;
        while(n>0){
            n &= n -1;
            cnt++;
        }
        return cnt;
	}
    public int hammingWeigh2(int n) {
        return Integer.bitCount(n);
    }

  

}