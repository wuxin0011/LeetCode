package leetcode.ox3if.data_struct.pre_sum.Solution_0005;

import code_generation.utils.IoUtil;

import java.util.Random;

/**
 * 1744. 你能在你最喜欢的那天吃到你最喜欢的糖果吗？
 * 给你一个下标从 0 开始的正整数数组candiesCount，其中candiesCount[i]表示你拥有的第i类糖果的数目。
 * 同时给你一个二维数组queries，其中queries[i] = [favoriteTypei, favoriteDayi, dailyCapi]。
 * 你按照如下规则进行一场游戏：
 * 你从第0天开始吃糖果。
 * 你在吃完 所有第 i - 1类糖果之前，不能吃任何一颗第 i类糖果。
 * 在吃完所有糖果之前，你必须每天 至少吃 一颗糖果。
 * 请你构建一个布尔型数组answer，用以给出 queries 中每一项的对应答案。此数组满足：
 * answer.length == queries.length 。answer[i] 是 queries[i] 的答案。
 * answer[i] 为true的条件是：在每天吃 不超过 dailyCapi颗糖果的前提下，你可以在第favoriteDayi天吃到第favoriteTypei类糖果；否则 answer[i]为 false。
 * 注意，只要满足上面 3 条规则中的第二条规则，你就可以在同一天吃不同类型的糖果。
 * 请你返回得到的数组answer。
 * <p>
 * 示例 1：
 * 输入：candiesCount = [7,4,5,3,8], queries = [[0,2,2],[4,2,4],[2,13,1000000000]]
 * 输出：[true,false,true]
 * <p>
 * 提示：
 * 1- 在第 0 天吃 2 颗糖果(类型 0），第 1 天吃 2 颗糖果（类型 0），第 2 天你可以吃到类型 0 的糖果。
 * 2- 每天你最多吃 4 颗糖果。即使第 0 天吃 4 颗糖果（类型 0），第 1 天吃 4 颗糖果（类型 0 和类型 1），你也没办法在第 2 天吃到类型 4 的糖果。换言之，你没法在每天吃 4 颗糖果的限制下在第 2 天吃到第 4 类糖果。
 * 3- 如果你每天吃 1 颗糖果，你可以在第 13 天吃到类型 2 的糖果。
 * <p>
 * 示例 2：
 * 输入：candiesCount = [5,2,6,4,1], queries = [[3,1,2],[4,10,3],[3,10,100],[4,100,30],[1,3,1]]
 * 输出：[false,true,true,false,false]
 * 提示：
 * 1 <= candiesCount.length <= 10^5
 * 1 <= candiesCount[i] <= 10^5
 * 1 <= queries.length <= 10^5
 * queries[i].length == 3
 * 0 <= favoriteTypei < candiesCount.length
 * 0 <= favoriteDayi <= 10^9
 * 1 <= dailyCapi <= 10^9
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/can-you-eat-your-favorite-candy-on-your-favorite-day
 * @title: 你能在你最喜欢的那天吃到你最喜欢的糖果吗？
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "canEat", "in.txt");
    }


    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int n = queries.length;
        boolean[] ans = new boolean[n];
        long[] sum = new long[candiesCount.length];
        sum[0] = candiesCount[0];
        for (int i = 1; i < candiesCount.length; i++) {
            sum[i] = sum[i - 1] + candiesCount[i];
        }
        for (int i = 0; i < queries.length; i++) {
            // sum[queries[i][0]] > queries[i][1] 最少
            // queries[i][0] == 0 可以直接跳过
            // 1L * (queries[i][1] + 1) * queries[i][2] 最多
            // sum[queries[i][0] - 1] // 除了当前类型 之和
            ans[i] = sum[queries[i][0]] > queries[i][1]
                    && (queries[i][0] == 0 || sum[queries[i][0] - 1] < 1L * (queries[i][1] + 1) * queries[i][2]);
        }
        return ans;
    }


    // 测试这玩玩的数据
    public static final Random rd = new Random();

    public boolean[] canEat1(int[] candiesCount, int[][] queries) {

        int n = queries.length;
        boolean[] ans = new boolean[n];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = rd.nextBoolean();
        }
        return ans;
    }


}