package leetcode.top_interview_150.product_of_array_except_self;

import code_generation.utils.IoUtil;

/**
 *
 *
 * 238. 除自身以外数组的乘积
 *
 * 给你一个整数数组nums，返回 数组answer，其中answer[i]等于nums中除nums[i]之外其余各元素的乘积。
 * 题目数据 保证 数组nums之中任意元素的全部前缀元素和后缀的乘积都在 32 位 整数范围内。
 * 请不要使用除法，且在O(n) 时间复杂度内完成此题。
 *
 * 示例 1:
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 *
 * 示例 2:
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0,0]
 *
 * 提示：
 * 	2 <= nums.length <= 10^5
 * 	-30 <= nums[i] <= 30
 * 	保证 数组nums之中任意元素的全部前缀元素和后缀的乘积都在 32 位 整数范围内
 * 进阶：你可以在 O(1)的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/product-of-array-except-self
 * @title: 除自身以外数组的乘积
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "productExceptSelf", "in.txt");
        IoUtil.testUtil(Solution.class, "productExceptSelf1", "in.txt");
    }


    // - [二维矩阵 ]( https://leetcode.cn/problems/construct-product-matrix/) 本题进阶版
    // - [ 最大或值]( https://leetcode.cn/problems/maximum-or)


    public int[] productExceptSelf(int[] nums) {
        // 这个类似与接雨水 求前缀值
        int n = nums.length;
        int[] pre = new int[n];
        // 注意不能初始化为0
        pre[0] = 1;

        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] * nums[i - 1];
        }
        int[] ans = new int[n];
        int suf = 1;
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = suf * pre[i];
            suf = suf * nums[i];
        }
        return ans;
    }


    public int[] productExceptSelf1(int[] nums) {
        // 这个类似与接雨水 求前缀值
        int n = nums.length;
        int[] pre = new int[n];
        int[] suf = new int[n];

        // 注意不能初始化为0
        pre[0] = 1;
        suf[n - 1] = 1;

        for (int i = 1, j = n - 2; i < n && j >= 0; i++, j--) {
            pre[i] = pre[i - 1] * nums[i - 1];
            suf[j] = suf[j + 1] * nums[j + 1];
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = pre[i] * suf[i];
        }
        return ans;
    }


}