package leetcode._0x3f_.dp.base.Solution_0000;

import code_generation.utils.IoUtil;

/**
 *
 *
 * 70. 爬楼梯
 *
 * 假设你正在爬楼梯。
 * 需要 n阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。
 * 你有多少种不同的方法可以爬到楼顶呢？
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 *
 * 示例 2：
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 *
 *
 * 提示：
 * 	1 <= n <= 45
 *
 *
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/climbing-stairs
 * @title: 爬楼梯
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class,"climbStairs","in.txt");
    }


    static int[] dp;

    static int N = 50;

    static {
        dp = new int[N];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3;i<dp.length;i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

    }

    public int climbStairs(int n) {    
        return dp[n];
	}

  

}