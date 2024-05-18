package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;

import java.util.Arrays;
/**
 * @author: wuxin0011
 * @Description:
 * @url:   https://leetcode.cn/problems/minimum-time-to-complete-all-tasks
 * @title: 完成所有任务的最少时间
 */
public class Code_0068_2589 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0068_2589.class,"findMinimumTime","txt_file\\Code_0068_2589.txt");
    }


    public int findMinimumTime(int[][] tasks) {

        // 按照结束时间右端点排序
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);
        int ans = 0;
        int n = tasks.length;

        // 保存区间是否运行了
        boolean[] vis = new boolean[tasks[n - 1][1] + 1];


        for (int[] task : tasks) {
            int st = task[0], ed = task[1], need = task[2];

            // 检查这个区间之前是否运行了 根据贪心策略 ，如果之前运行了，可以并行运行
            // 这样能减少机器执行时间
            for (int k = st; k <= ed; k++) {
                if (vis[k]) {
                    need--;
                }
            }

            // 检查是否运行完毕 如果没有运行完毕需要往前找区间执行 由于这个在
            // 检查之前因此 这一步必须值之前区间没有任何机器运行的
            for (int k = ed; need > 0; k--) {
                if (!vis[k]) {
                    need--;
                    vis[k] = true;
                    ans++;
                }
            }
        }
        return ans;
    }

  

}