package leetcode._0x3f_.bitwise_operations.base.bitwise_0005;

import code_generation.utils.IoUtil;

/**
 *
 *
 * 231. 2 的幂
 *
 * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。如果存在一个整数 x 使得n == 2x ，则认为 n 是 2 的幂次方。
 *
 * 示例 1：输入：n = 1输出：true解释：20 = 1
 *
 * 示例 2：输入：n = 16输出：true解释：24 = 16
 *
 * 示例 3：输入：n = 3输出：false
 *
 * 提示：
 *
 *
 * 	-231 <= n <= 231 - 1
 *
 *
 *
 *
 * 进阶：你能够不使用循环/递归解决此问题吗？
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/power-of-two
 * @title: 2的幂
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"isPowerOfTwo","in.txt");
    }
     

    public boolean isPowerOfTwo(int n) {    

        return n > 0 && ( (n & (n-1)) == 0);
	}

    public boolean isPowerOfTwo1(int n) {

        if(n<1 && n % 2 != 0 || n == 0){
            return false;
        }
        while(n>1 && n % 2 == 0){
            n /= 2;
        }
        return n == 1 ;
    }

  

}