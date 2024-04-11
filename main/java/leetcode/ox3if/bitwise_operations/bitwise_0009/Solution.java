package leetcode.ox3if.bitwise_operations.bitwise_0009;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 *
 * 338. 比特位计数
 *
 * 给你一个整数 n ，对于0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 *
 * 示例 1：输入：n = 2输出：[0,1,1]解释：0 --> 01 --> 12 --> 10
 *
 * 示例 2：输入：n = 5输出：[0,1,1,2,1,2]解释：0 --> 01 --> 12 --> 103 --> 114 --> 1005 --> 101
 *
 * 提示：
 *
 *
 * 	0 <= n <= 105
 *
 *
 * 进阶：
 *
 *
 * 	很容易就能实现时间复杂度为 O(n log n) 的解决方案，你可以在线性时间复杂度 O(n) 内用一趟扫描解决此问题吗？
 * 	你能不使用任何内置函数解决此问题吗？（如，C++ 中的__builtin_popcount ）
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/counting-bits
 * @title: 比特位计数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"countBits","in.txt");
    }
     

    public int[] countBits(int n) {
        int[] h = new int[n+1];
        for (int i = 0; i <= n; i++) {
            h[i] = Integer.bitCount(i);
        }
        return h;
    }

  

}