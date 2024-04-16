package leetcode.ox3if.dp.base.Solution_0011;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 1749. 任意子数组和的绝对值的最大值
 * <p>
 * 给你一个整数数组nums。
 * 一个子数组[numsl, numsl+1, ..., numsr-1, numsr]的 和的绝对值为abs(numsl + numsl+1 + ... + numsr-1 + numsr)。
 * 请你找出 nums中 和的绝对值 最大的任意子数组（可能为空），并返回该 最大值。
 * abs(x)定义如下：
 * 如果x是负整数，那么abs(x) = -x。
 * 如果x是非负整数，那么abs(x) = x。
 * <p>
 * 示例 1：
 * 输入：nums = [1,-3,2,3,-4]
 * 输出：5
 * 解释：子数组 [2,3] 和的绝对值最大，为 abs(2+3) = abs(5) = 5 。
 * <p>
 * 示例 2：
 * 输入：nums = [2,-5,1,-4,3,-2]
 * 输出：8
 * 解释：子数组 [-5,1,-4] 和的绝对值最大，为 abs(-5+1-4) = abs(-8) = 8 。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * <p>
 * <p>
 * https://leetcode.cn/problems/maximum-absolute-sum-of-any-subarray/
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/maximum-absolute-sum-of-any-subarray
 * @title: 任意子数组和的绝对值的最大值
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maxAbsoluteSum", "in.txt");
    }


    public int maxAbsoluteSum(int[] nums) {
        int ans = 0,mx = 0,mi = 0;
        for (int num : nums){
            ans += num;
            if(ans>mx){
                mx = ans;
            }else if(ans < mi ){
                mi = ans;
            }
        }
        return mx - mi;
    }


}