package leetcode._0x3f_.data_struct.heap.base.Solution_0011;

import code_generation.utils.IoUtil;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 2406. 将区间分为最少组数
 * <p>
 * 给你一个二维整数数组intervals，其中intervals[i] = [lefti, righti]表示 闭区间[lefti, righti]。
 * 你需要将intervals 划分为一个或者多个区间组，每个区间 只属于一个组，且同一个组中任意两个区间 不相交。
 * 请你返回 最少需要划分成多少个组。
 * 如果两个区间覆盖的范围有重叠（即至少有一个公共数字），那么我们称这两个区间是 相交的。比方说区间[1, 5] 和[5, 8]相交。
 * <p>
 * 示例 1：
 * 输入：intervals = [[5,10],[6,8],[1,5],[2,3],[1,10]]
 * 输出：3
 * 解释：我们可以将区间划分为如下的区间组：
 * - 第 1 组：[1, 5] ，[6, 8] 。
 * - 第 2 组：[2, 3] ，[5, 10] 。
 * - 第 3 组：[1, 10] 。
 * 可以证明无法将区间划分为少于 3 个组。
 * <p>
 * 示例 2：
 * 输入：intervals = [[1,3],[5,6],[8,10],[11,13]]
 * 输出：1
 * 解释：所有区间互不相交，所以我们可以把它们全部放在一个组内。
 * <p>
 * 提示：
 * 1 <= intervals.length <= 10^5
 * intervals[i].length == 2
 * 1 <= lefti <= righti <= 10^6
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/divide-intervals-into-minimum-number-of-groups
 * @title: 将区间分为最少组数
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "minGroups", "in.txt");
    }


    public int minGroups(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int[] p : intervals) {
            if(!pq.isEmpty() && pq.peek() < p[0]) {
                pq.poll();
            }
            pq.offer(p[1]);
        }
        return pq.size();
    }



}