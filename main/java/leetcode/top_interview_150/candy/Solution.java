package leetcode.top_interview_150.candy;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 135. 分发糖果
 * <p>
 * n 个孩子站成一排。
 * 给你一个整数数组 ratings 表示每个孩子的评分。
 * 你需要按照以下要求，给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 * <p>
 * 示例1：
 * 输入：ratings = [1,0,2]
 * 输出：5
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
 * <p>
 * 示例2：
 * 输入：ratings = [1,2,2]
 * 输出：4
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
 * 第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
 * <p>
 * 提示：
 * n == ratings.length
 * 1 <= n <= 2 * 10^4
 * 0 <= ratings[i] <= 2 * 10^4
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/candy
 * @title: 分发糖果
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "candy", "in.txt");
    }


    public int candy(int[] ratings) {

        int n = ratings.length;
        int[] cnt = new int[ratings.length];
        Arrays.fill(cnt, 1);


        // 从前往后看 如果 前 > 后 cnt[i] = cnt[i-1] + 1
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                cnt[i] = cnt[i - 1] + 1;
            }
        }


        // 从后往前看
        // 经过上面一轮后 如果 前 > 后 一定有 cnt[i] = cnt[i-1]  + 1成立
        // 但是 如果 出现
        // rating 1 2 3 4 2 1 2
        // cnt    1 2 3 4 1 1 2
        // 发现这里不对     X
        int ans = cnt[n-1];

        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && cnt[i] <= cnt[i + 1]) {
                cnt[i] = cnt[i + 1] + 1;
            }
            ans += cnt[i];
        }
        return ans;
    }


}