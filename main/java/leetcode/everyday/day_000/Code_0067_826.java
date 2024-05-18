package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * 826. 安排工作以达到最大收益
 * <p>
 * 你有 n个工作和 m 个工人。
 * 给定三个数组：difficulty,profit和worker，其中:
 * difficulty[i]表示第 i 个工作的难度，profit[i] 表示第 i 个工作的收益。
 * worker[i] 是第 i 个工人的能力，即该工人只能完成难度小于等于 worker[i] 的工作。
 * 每个工人最多 只能安排 一个 工作，但是一个工作可以 完成多次 。
 * 举个例子，如果 3 个工人都尝试完成一份报酬为 $1 的同样工作，那么总收益为 $3。如果一个工人不能完成任何工作，他的收益为 $0 。
 * 返回 在把工人分配到工作岗位后，我们所能获得的最大利润。
 * <p>
 * 示例 1：
 * 输入: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
 * 输出: 100
 * 解释: 工人被分配的工作难度是 [4,4,6,6] ，分别获得 [20,20,30,30] 的收益。
 * <p>
 * 示例 2:
 * 输入: difficulty = [85,47,57], profit = [24,66,99], worker = [40,25,25]
 * 输出: 0
 * <p>
 * 提示:
 * n == difficulty.length
 * n == profit.length
 * m == worker.length
 * 1 <= n, m <= 10^4
 * 1 <= difficulty[i], profit[i], worker[i] <= 10^5
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/most-profit-assigning-work
 * @title: 安排工作以达到最大收益
 */
public class Code_0067_826 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0067_826.class, "maxProfitAssignment", "txt_file\\Code_0067_826.txt");
        IoUtil.testUtil(Code_0067_826.class, "maxProfitAssignment2", "txt_file\\Code_0067_826.txt");
    }


    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        // 难度   difficulty
        // 收益   profit
        //    worker
        int n = profit.length;
        int[][] h = new int[n][2];
        for (int i = 0; i < n; i++) {
            h[i][0] = difficulty[i];
            h[i][1] = profit[i];
        }

        // 根据收益排序 从 大 -> 小
        Arrays.sort(h, ((a, b) -> b[1] - a[1]));

        // 工人工作能力 -> 小 ->大
        Arrays.sort(worker);

        int m = worker.length;

        int cost = 0;

        for (int i = 0, j = m - 1; i < n && j >= 0; i++) {
            while (j >= 0 && worker[j] >= h[i][0]) {
                cost += h[i][1];
                j--;
            }
        }
        return cost;
    }


    public int maxProfitAssignment2(int[] difficulty, int[] profit, int[] worker) {
        // 难度   difficulty
        // 收益   profit
        //    worker
        int n = profit.length;

        Integer[] ids = new Integer[n];

        // 初始化ID
        Arrays.setAll(ids, (id) -> id);

        // 根据收益排序 从 大 -> 小
        Arrays.sort(ids, ((a, b) -> profit[b] - profit[a]));

        // 工人工作能力 -> 小 ->大
        Arrays.sort(worker);

        int m = worker.length;

        int cost = 0;

        for (int i = 0, j = m - 1; i < n && j >= 0; i++) {
            while (j >= 0 && worker[j] >= difficulty[ids[i]]) {
                cost += profit[ids[i]];
                j--;
            }
        }
        return cost;
    }


}