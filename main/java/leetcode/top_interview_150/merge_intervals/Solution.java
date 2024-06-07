package leetcode.top_interview_150.merge_intervals;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56. 合并区间
 * <p>
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * <p>
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 示例2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * <p>
 * 提示：
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^4
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/merge-intervals
 * @title: 合并区间
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "merge", "in.txt");
    }


    // 其他比较经典区间合并题目
    // https://leetcode.cn/contest/weekly-contest-400/problems/count-days-without-meetings


    public int[][] merge(int[][] intervals) {

        List<int[]> ls = new ArrayList<>();

        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int st = -1, ed = -1;
        int n = intervals.length;
        for (int[] interval : intervals) {
            int s = interval[0], e = interval[1];
            if (st == -1 && ed == -1) {
                st = s;
                ed = e;
            } else if (s > ed) {
                // 区间断开
                ls.add(new int[]{st, ed});
                st = s;
                ed = e;
            } else {
                ed = Math.max(ed, e);
            }
        }
        ls.add(new int[]{st,ed});
        int[][] ans = new int[ls.size()][2];
        for (int i = 0; i < ls.size(); i++) {
            ans[i] = ls.get(i);
        }
        return ans;
    }


}