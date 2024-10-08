package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;

/**
 * 1953. 你可以工作的最大周数
 * <p>
 * 给你n 个项目，编号从 0 到 n - 1 。
 * 同时给你一个整数数组 milestones ，其中每个 milestones[i] 表示第 i 个项目中的阶段任务数量。
 * 你可以按下面两个规则参与项目中的工作：
 * 每周，你将会完成 某一个 项目中的 恰好一个阶段任务。你每周都 必须 工作。
 * 在 连续的 两周中，你 不能 参与并完成同一个项目中的两个阶段任务。
 * 一旦所有项目中的全部阶段任务都完成，或者仅剩余一个阶段任务都会导致你违反上面的规则，那么你将停止工作 。注意，由于这些条件的限制，你可能无法完成所有阶段任务。
 * 返回在不违反上面规则的情况下你最多能工作多少周。
 * <p>
 * 示例 1：
 * 输入：milestones = [1,2,3]
 * 输出：6
 * 解释：一种可能的情形是：
 * - 第 1 周，你参与并完成项目 0 中的一个阶段任务。
 * - 第 2 周，你参与并完成项目 2 中的一个阶段任务。
 * - 第 3 周，你参与并完成项目 1 中的一个阶段任务。
 * - 第 4 周，你参与并完成项目 2 中的一个阶段任务。
 * - 第 5 周，你参与并完成项目 1 中的一个阶段任务。
 * - 第 6 周，你参与并完成项目 2 中的一个阶段任务。
 * 总周数是 6 。
 * <p>
 * 示例 2：
 * 输入：milestones = [5,2,1]
 * 输出：7
 * 解释：一种可能的情形是：
 * - 第 1 周，你参与并完成项目 0 中的一个阶段任务。
 * - 第 2 周，你参与并完成项目 1 中的一个阶段任务。
 * - 第 3 周，你参与并完成项目 0 中的一个阶段任务。
 * - 第 4 周，你参与并完成项目 1 中的一个阶段任务。
 * - 第 5 周，你参与并完成项目 0 中的一个阶段任务。
 * - 第 6 周，你参与并完成项目 2 中的一个阶段任务。
 * - 第 7 周，你参与并完成项目 0 中的一个阶段任务。
 * 总周数是 7 。
 * 注意，你不能在第 8 周参与完成项目 0 中的最后一个阶段任务，因为这会违反规则。
 * 因此，项目 0 中会有一个阶段任务维持未完成状态。
 * <p>
 * 提示：
 * n == milestones.length
 * 1 <= n <= 10^5
 * 1 <= milestones[i] <= 10^9
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/maximum-number-of-weeks-for-which-you-can-work
 * @title: 你可以工作的最大周数
 */
public class Code_0066_1953 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0066_1953.class, "numberOfWeeks", "txt_file\\Code_0066_1953.txt");
    }


    // 分析
    // 相当于找一个最大数字作为挡板
    // 然后不同数字分别填写到这些挡板之中
    // 比如
    // 1 2 3
    // =>  3 | 3 | 3
    // 6 - 3 = 4
    // tot -= mx
    // tot + 1 > mx : 那么可以随便填写
    // 如果 mx > tot + 1 ，说明任务不够分配 2 * tot + 1
    public long numberOfWeeks(int[] milestones) {
        long mx = 0;
        long tot = 0;
        for (int task : milestones) {
            tot += task;
            if (mx < task) {
                mx = task;
            }
        }
        tot -= mx;
        //  1 3 1 3 2 3
        //  tot -= mx
        // mx > tot + 1

        return mx > tot + 1 ? (2 * tot + 1) : (mx + tot);
    }


}