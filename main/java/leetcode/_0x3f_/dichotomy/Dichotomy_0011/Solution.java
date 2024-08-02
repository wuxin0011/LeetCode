package leetcode._0x3f_.dichotomy.Dichotomy_0011;

import code_generation.utils.IoUtil;

/**
 * 1283. 使结果不超过阈值的最小除数
 * <p>
 * 给你一个整数数组nums 和一个正整数threshold ，你需要选择一个正整数作为除数，然后将数组里每个数都除以它，并对除法结果求和。请你找出能够使上述结果小于等于阈值threshold的除数中 最小 的那个。每个数除以除数后都向上取整，比方说 7/3 = 3 ， 10/2 = 5 。题目保证一定有解。
 * <p>
 * 示例 1：输入：nums = [1,2,5,9], threshold = 6输出：5解释：如果除数为 1 ，我们可以得到和为 17 （1+2+5+9）。如果除数为 4 ，我们可以得到和为 7 (1+1+2+3) 。如果除数为 5 ，和为 5 (1+1+1+2)。
 * <p>
 * 示例 2：输入：nums = [2,3,5,7,11], threshold = 11输出：3
 * <p>
 * 示例 3：输入：nums = [19], threshold = 5输出：4
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= nums.length <= 5 * 10^4
 * 1 <= nums[i] <= 10^6
 * nums.length <=threshold <= 10^6
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/find-the-smallest-divisor-given-a-threshold
 * @title: find-the-smallest-divisor-given-a-threshold
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "smallestDivisor", "in.txt");
    }


    public int smallestDivisor(int[] nums, int threshold) {

        int mx = 0;
        for (int num : nums) if (mx < num) mx = num;
        int r = mx, l = 1;
        while (l<=r) {
            int mid = l + ((r - l) >> 1);
            int target = calc(nums, mid);
            // System.out.println("nums = " + mid + ",target = " + target + "threshold = " + threshold + " ok = " + (threshold == target));
            // if (target == threshold) {
            //     return mid;
            // }
            if (target > threshold) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    public static int calc(int[] arr, int v) {
        int tot = 0;
        for (int i : arr) {
            tot += (i+v-1)/v;
        }
        return tot;
    }

}