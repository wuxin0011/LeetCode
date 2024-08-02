package leetcode._0x3f_.bitwise_operations.base.bitwise_0015;

import code_generation.utils.IoUtil;

/**
 *
 *
 *  693. 交替位二进制数
 *
 *  给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。
 *
 *  示例 1：输入：n = 5输出：true解释：5 的二进制表示是：101
 *
 *  示例 2：输入：n = 7输出：false解释：7 的二进制表示是：111.
 *
 *  示例 3：输入：n = 11输出：false解释：11 的二进制表示是：1011.
 *
 *  提示：
 *
 *
 *  1 <= n <= 231 - 1
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/binary-number-with-alternating-bits
 * @title: 交替位二进制数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"hasAlternatingBits","in.txt");
    }
     

    public boolean hasAlternatingBits(int n) {
        int f = -1;

        // 或者这种写法
//        for(int i = 0;i<32;i++){
//            // 找到第一个右边移动==1进制位作为起点
//            if((n >> (31 - i) & 1) == 1){
//                f = i;
//                break;
//            }
//        }


        // 或者这种写法
        for(int i = 31;i>=0;i--){
            // 找到第一个右边移动==1进制位作为起点
            if((n >> (31 - i) & 1) == 1){
                f = i;
            }
        }
        // System.out.println(f);
        for(int i = f;i<32;i++){
            int pre = n >> (31 - (i-1)) & 1;
            int cur = n >> (31 - (i)) & 1;
            if(pre == cur){
                return false;
            }
        }
        return true;
    }

  

}