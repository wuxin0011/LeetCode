package leetcode.contest.biweekly.bi_100.bi_125.a;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description: 简单遍历
 * @title 超过阈值的最少操作数 I
 * @url https://leetcode.cn/problems/minimum-operations-to-exceed-threshold-value-i/description/
 */
public class A {
    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"minOperations","in.txt");
    }

    public int minOperations(int[] nums, int k) {
        int cnt = 0;
        for(int num : nums){
            if( num  < k ){
                cnt++;
            }
        }
        return cnt;
    }
}
