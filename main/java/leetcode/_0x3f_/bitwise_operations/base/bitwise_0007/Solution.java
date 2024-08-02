package leetcode._0x3f_.bitwise_operations.base.bitwise_0007;

import code_generation.utils.IoUtil;

/**
 *
 * 476. 数字的补数
 *
 * 对整数的二进制表示取反（0 变 1 ，1 变 0）后，再转换为十进制表示，可以得到这个整数的补数。例如，整数 5 的二进制表示是 "101" ，取反后得到 "010" ，再转回十进制表示得到补数 2 。给你一个整数 num ，输出它的补数。
 *
 * 示例 1：输入：num = 5输出：2解释：5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。
 *
 * 示例 2：输入：num = 1输出：0解释：1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。
 *
 * 提示：
 *
 *
 * 	1 <= num < 231
 *
 *
 *
 *
 * 注意：本题与 1009 https://leetcode-cn.com/problems/complement-of-base-10-integer/ 相同
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/number-complement
 * @title: 数字的补数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"findComplement","in.txt");
    }
     

    public int findComplement(int num) {
        int v = 0;
        for(int i = 0;i<31;i++){

            v |= ((num>>i&1) == 0 ? 1<<i : 0);
        }
        System.out.println(v);

        return 0;
    }

  

}