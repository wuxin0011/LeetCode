package leetcode.everyday;

import leetcode.utils.IoUtil;

/*
*
*   使用动态规划，推理当前状态
*
 */


/**
 * @author: wuxin0011
 * @Description:  检查数组是否存在有效划分
 * @url https://leetcode.cn/problems/check-if-there-is-a-valid-partition-for-the-array/
 */
@SuppressWarnings("all")
public class Code_0010_2369 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0010_2369.class, "validPartition", "./txt_file/Code_0010_2369.txt");
    }


    public boolean validPartition(int[] nums) {
        int n = nums.length;
        if( n < 1) return false;
        if( n == 2 ) return nums[0] == nums[1];
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        dp[2] = nums[1] == nums[0];
        for(int i = 3;i<=n;i++){
            if( nums[i-1] == nums[i-2] ){
                dp[i] |= dp[i-2];
            }
            if( nums[i-1] == nums[i-2] && nums[i-2] == nums[i-3] ){
                dp[i] |= dp[i-3];
            }
            if( nums[i-1] - nums[i-2] == 1 &&  nums[i-2] - nums[i-3] == 1 ){
                dp[i] |= dp[i-3];
            }
        }
        return dp[n];

    }



}
