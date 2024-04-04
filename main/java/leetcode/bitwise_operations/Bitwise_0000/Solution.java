package leetcode.bitwise_operations.Bitwise_0000;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/single-number
 * @title: single-number
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"singleNumber","in.txt");
    }


    // 使用简单异或运算就能得到
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }

}