package leetcode.contest.biweekly.bi_100.bi_120.b;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 2971. 找到最大周长的多边形
 * <p>
 * 给你一个长度为n的正整数数组nums。
 * 多边形指的是一个至少有 3条边的封闭二维图形。多边形的 最长边一定 小于所有其他边长度之和。
 * 如果你有k（k >= 3）个正数a1，a2，a3, ...，ak 满足a1 <= a2 <= a3 <= ... <= ak 且 a1 + a2 + a3 + ... + ak-1 > ak，那么 一定存在一个k条边的多边形，每条边的长度分别为a1，a2，a3，...，ak。
 * 一个多边形的 周长指的是它所有边之和。
 * 请你返回从 nums中可以构造的 多边形的 最大周长。如果不能构造出任何多边形，请你返回 -1。
 * <p>
 * 示例 1：
 * 输入：nums = [5,5,5]
 * 输出：15
 * 解释：nums 中唯一可以构造的多边形为三角形，每条边的长度分别为 5 ，5 和 5 ，周长为 5 + 5 + 5 = 15 。
 * <p>
 * 示例 2：
 * 输入：nums = [1,12,1,2,5,50,3]
 * 输出：12
 * 解释：最大周长多边形为五边形，每条边的长度分别为 1 ，1 ，2 ，3 和 5 ，周长为 1 + 1 + 2 + 3 + 5 = 12 。
 * 我们无法构造一个包含变长为 12 或者 50 的多边形，因为其他边之和没法大于两者中的任何一个。
 * 所以最大周长为 12 。
 * <p>
 * 示例 3：
 * 输入：nums = [5,5,50]
 * 输出：-1
 * 解释：无法构造任何多边形，因为多边形至少要有 3 条边且 50 > 5 + 5 。
 * <p>
 * 提示：
 * 3 <= n <= 10^5
 * 1 <= nums[i] <= 10^9
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-120/problems/find-polygon-with-the-largest-perimeter
 * @title: 找到最大周长的多边形
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class,"largestPerimeter","B.txt");
    }


    public long largestPerimeter(int[] nums) {

        long sum = 0;
        Arrays.sort(nums);
        for (int num : nums) {
            sum += num;
        }
        for (int i = nums.length - 1; i >= 2; i--) {
            if (sum - nums[i] <= nums[i]) {
                sum -= nums[i];
                continue;
            }
            return sum;
        }

        return -1;
    }

  

}