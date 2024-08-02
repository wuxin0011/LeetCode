package leetcode._0x3f_.bitwise_operations.contribution.contribution_0004;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/find-xor-sum-of-all-pairs-bitwise-and
 * @title: 所有数对按位与结果的异或和
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"getXORSum","in.txt");
    }
     

    public int getXORSum(int[] arr1, int[] arr2) {
        int a1 = arr1[0],a2 = arr2[0];
        for (int i = 1; i < arr1.length; i++) {
            a1 = a1 ^ arr1[i];
        }
        for (int i = 1; i < arr2.length; i++) {
            a2 = a2 ^ arr2[i];
        }
        return a1 & a2;
    }

  

}