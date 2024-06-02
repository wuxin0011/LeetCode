package leetcode.contest.weekly.w_400.w_400.b;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 *
 * 100311. 无需开会的工作日
 *
 *
 *               给你一个正整数 days，表示员工可工作的总天数（从第 1 天开始）。
 * 另给你一个二维数组 meetings，长度为 n，其中 meetings[i] = [start_i, end_i] 表示第 i 次会议的开始和结束天数（包含首尾）。
 * 返回员工可工作且没有安排会议的天数。
 * 注意：会议时间可能会有重叠。
 *
 * 示例 1：
 * 输入：days = 10, meetings = [[5,7],[1,3],[9,10]]
 * 输出：2
 * 解释：
 * 第 4 天和第 8 天没有安排会议。
 *
 * 示例 2：
 * 输入：days = 5, meetings = [[2,4],[1,3]]
 * 输出：1
 * 解释：
 * 第 5 天没有安排会议。
 *
 * 示例 3：
 * 输入：days = 6, meetings = [[1,6]]
 * 输出：0
 * 解释：
 * 所有工作日都安排了会议。
 *
 * 提示：
 * 	1 <= days <= 10^9
 * 	1 <= meetings.length <= 10^5
 * 	meetings[i].length == 2
 * 	1 <= meetings[i][0] <= meetings[i][1] <= days
 *
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-400/problems/count-days-without-meetings
 * @title: 无需开会的工作日
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "countDays", "B.txt");
    }


    public int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int n = meetings.length;
        int ed = meetings[0][1];
        int ans = (meetings[0][0] - 1);
        for (int i = 0; i < n; ) {
            int j = i + 1;
            while (j < n && (meetings[i][0] == meetings[j][0] && ed >= meetings[j][1])) {
                j++;
            }
            if (j < n) {
                if (meetings[j][0] > ed) {
                    ans += (meetings[j][0] - ed - 1);
                }
                ed = Math.max(meetings[j][1], ed);

            }

            i = j;

        }
        ans += days - ed;

        return ans;
    }


}