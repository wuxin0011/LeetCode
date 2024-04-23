package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 *
 * 377. 组合总和 Ⅳ
 *
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。
 * 请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * 题目数据保证答案符合 32 位整数范围。
 *
 * 示例 1：
 * 输入：nums = [1,2,3], target = 4
 * 输出：7
 * 解释：
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 *
 * 示例 2：
 * 输入：nums = [9], target = 3
 * 输出：0
 *
 * 提示：
 * 	1 <= nums.length <= 200
 * 	1 <= nums[i] <= 1000
 * 	nums 中的所有元素 互不相同
 * 	1 <= target <= 1000
 * 进阶：如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件？
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/combination-sum-iv
 * @title: 组合总和Ⅳ
 */
public class Code_0050_377 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0050_377.class,"combinationSum4","txt_file\\Code_0050_377.txt");
    }
     

    public int combinationSum4(int[] nums, int target) {

        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i = 1;i<=target ;i++) {
            for (int num : nums) {
                if(num<=i){
                    dp[i] += dp[i-num];
                }
            }
        }
        return dp[target];
    }

    public int combinationSum4Demo(int[] nums, int target) {
       return dfs(nums,target);
    }


    public static int dfs(int[] nums,int cost){
        if(cost==0){
            return 1;
        }
        if(cost<0){
            return 0;
        }
        int ans = 0;
        for (int num : nums) {
            if(cost>=num){
                ans += dfs(nums,cost - num);
            }
        }
        return ans;
    }





}