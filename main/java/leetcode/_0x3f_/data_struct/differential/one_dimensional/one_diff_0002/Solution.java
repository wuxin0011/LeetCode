package leetcode._0x3f_.data_struct.differential.one_dimensional.one_diff_0002;

import code_generation.utils.IoUtil;

/**
 * 1854. 人口最多的年份
 * <p>
 * 给你一个二维整数数组 logs ，其中每个 logs[i] = [birthi, deathi] 表示第 i 个人的出生和死亡年份。
 * 年份 x 的 人口 定义为这一年期间活着的人的数目。第 i 个人被计入年份 x 的人口需要满足：x 在闭区间 [birthi, deathi - 1] 内。注意，人不应当计入他们死亡当年的人口中。
 * 返回 人口最多 且 最早 的年份。
 * <p>
 * 示例 1：
 * 输入：logs = [[1993,1999],[2000,2010]]
 * 输出：1993
 * 解释：人口最多为 1 ，而 1993 是人口为 1 的最早年份。
 * <p>
 * 示例 2：
 * 输入：logs = [[1950,1961],[1960,1971],[1970,1981]]
 * 输出：1960
 * 解释：
 * 人口最多为 2 ，分别出现在 1960 和 1970 。
 * 其中最早年份是 1960 。
 * <p>
 * 提示：
 * 1 <= logs.length <= 100
 * 1950 <= birthi < deathi <= 2050
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/maximum-population-year
 * @title: 人口最多的年份
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "maximumPopulation", "in.txt");
    }


    public int maximumPopulation(int[][] logs) {

        int ans = 0;
        int pre = 0;
        int c = 1950;
        int[] diff = new int[105];
        int N = 0;
        for (int[] log : logs) {
            int a = log[0] - c;
            int b = log[1] - c;
            diff[a]++;
            diff[b]--;
            N = Math.max(N, b);
        }

        int mx = 0;
        for (int i = 0; i <= N; i++) {
            pre += diff[i];
            if (pre > mx) {
                mx = pre;
                ans = i;
            }
        }
//        System.out.println("max = " + mx);
        return ans + c;
    }


}