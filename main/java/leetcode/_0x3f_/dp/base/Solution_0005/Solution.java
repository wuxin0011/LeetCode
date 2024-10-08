package leetcode._0x3f_.dp.base.Solution_0005;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 *
 * 198. 打家劫舍
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋。
 * 每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *     偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 * 提示：
 * 	1 <= nums.length <= 100
 * 	0 <= nums[i] <= 400
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/house-robber
 * @title: 打家劫舍
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "rob", "in.txt");
        IoUtil.testUtil(Solution.class, "rob1", "in.txt");
        IoUtil.testUtil(Solution.class, "rob2", "in.txt");
        IoUtil.testUtil(Solution.class, "rob3", "in.txt");
    }
     

    // 严格依赖一步一步递推版本
    public int rob(int[] nums) {

        if( nums == null || nums.length == 0 ) {
            return 0;
        }
        int n = nums.length;
        if( n == 1){
            return nums[0];
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }


    // 最优版本
    public int rob1(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int prepre = nums[0];
        int pre = Math.max(nums[0], nums[1]);
        int cur = 0;
        for (int i = 2; i < n; i++) {
            cur = Math.max(pre, prepre + nums[i]);
            prepre = pre;
            pre = cur;
        }
        return cur;
    }


    // 基础递归版本
    public int rob2(int[] nums) {
        return f(nums.length - 1, nums);
    }

    public static int f(int i, int[] nums) {
        if (i < 0) {
            return 0;
        }
        return Math.max(f(i - 1, nums), f(i - 2, nums) + nums[i]);
    }



    // 递归基础上挂缓存
    int[] memo;
    public int rob3(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo,-1);
        return f1(nums.length - 1, nums);
    }

    public  int f1(int i, int[] nums) {
        if (i < 0) {
            return 0;
        }
        if(memo[i]!=-1){
            return memo[i];
        }
        memo[i] = Math.max(f1(i - 1, nums), f1(i - 2, nums) + nums[i]);
        return memo[i];
    }
}