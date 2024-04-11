package leetcode.ox3if.bitwise_operations.bitwise_0011;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 *
 *
 * 461. 汉明距离
 *
 * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
 *
 * 示例 1：输入：x = 1, y = 4输出：2解释：1   (0 0 0 1)4   (0 1 0 0)       ↑   ↑上面的箭头指出了对应二进制位不同的位置。
 *
 * 示例 2：输入：x = 3, y = 1输出：1
 *
 * 提示：
 *
 *
 * 	0 <=x, y <= 231 - 1
 *
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/hamming-distance
 * @title: 汉明距离
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"hammingDistance","in.txt");
    }
     

    public int hammingDistance(int x, int y) {
        if(x==y){
            return 0;
        }
        int d = x ^ y;
        return Integer.bitCount(d);
    }

  

}