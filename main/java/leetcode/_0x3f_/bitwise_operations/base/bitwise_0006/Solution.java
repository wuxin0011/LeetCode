package leetcode._0x3f_.bitwise_operations.base.bitwise_0006;

import code_generation.utils.IoUtil;

/**
 *
 * 342. 4的幂
 *
 * 给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4x
 *
 * 示例 1：输入：n = 16输出：true
 *
 * 示例 2：输入：n = 5输出：false
 *
 * 示例 3：输入：n = 1输出：true
 *
 * 提示：
 *
 *
 * 	-231 <= n <= 231 - 1
 *
 *
 *
 *
 * 进阶：你能不使用循环或者递归来完成本题吗？
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/power-of-four
 * @title: 的幂
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"isPowerOfFour","in.txt");
    }
     

    public boolean isPowerOfFour(int n) {
        if(n==1) return true;
        if(n<4 || n % 4 !=0 ){
            return false;
        }
        while( n > 1 && n % 4 == 0){
            n /= 4;
        }
        return n == 1;
	}

  

}