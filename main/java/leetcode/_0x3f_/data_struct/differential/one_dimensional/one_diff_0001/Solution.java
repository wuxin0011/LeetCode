package leetcode._0x3f_.data_struct.differential.one_dimensional.one_diff_0001;

import code_generation.utils.IoUtil;

/**
 * 1893. 检查是否区域内所有整数都被覆盖
 * <p>
 * 给你一个二维整数数组ranges和两个整数left和right。
 * 每个ranges[i] = [starti, endi]表示一个从starti到endi的闭区间。
 * 如果闭区间[left, right]内每个整数都被ranges中至少一个区间覆盖，那么请你返回true，否则返回false。
 * 已知区间 ranges[i] = [starti, endi] ，如果整数 x 满足 starti <= x <= endi，那么我们称整数x被覆盖了。
 * <p>
 * 示例 1：
 * 输入：ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
 * 输出：true
 * 解释：2 到 5 的每个整数都被覆盖了：
 * - 2 被第一个区间覆盖。
 * - 3 和 4 被第二个区间覆盖。
 * - 5 被第三个区间覆盖。
 * <p>
 * 示例 2：
 * 输入：ranges = [[1,10],[10,20]], left = 21, right = 21
 * 输出：false
 * 解释：21 没有被任何一个区间覆盖。
 * <p>
 * 提示：
 * 1 <= ranges.length <= 50
 * 1 <= starti <= endi <= 50
 * 1 <= left <= right <= 50
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/check-if-all-the-integers-in-a-range-are-covered
 * @title: 检查是否区域内所有整数都被覆盖
 */
public class Solution {


    static void test() {
        int N = 52;

    }


    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "isCovered", "in.txt");
        IoUtil.testUtil(Solution.class, "isCovered1", "in.txt");
    }


    public boolean isCovered(int[][] ranges, int left, int right) {

        int mx = 52;
        int[] diff = new int[mx];
        for (int[] range : ranges) {
            diff[range[0]]++;
            diff[range[1] + 1]--;
        }
        int pre = 0;
        for (int i = 1; i <= 50; i++) {
            pre += diff[i];
            if (i >= left && i <= right && pre <= 0) {
                return false;
            }
        }

        return true;
    }

    public boolean isCovered1(int[][] ranges, int left, int right) {

        boolean[] vis = new boolean[51];

        for (int[] range : ranges) {
            for (int i = range[0]; i <= range[1]; i++) {
                vis[i] = true;
            }
        }
        for (int i = left; i <= right; i++) {
            if (!vis[i]) {
                return false;
            }
        }

        return true;
    }




}