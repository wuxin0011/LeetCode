package leetcode.ox3if.data_struct.differential.one_dimensional.one_diff_0000;

import code_generation.utils.IoUtil;

import java.util.List;
import java.util.Objects;

/**
 * 2848. 与车相交的点
 * <p>
 * 给你一个下标从 0 开始的二维整数数组 nums 表示汽车停放在数轴上的坐标。
 * 对于任意下标 i，nums[i] = [starti, endi] ，其中 starti 是第 i 辆车的起点，endi 是第 i 辆车的终点。
 * 返回数轴上被车 任意部分 覆盖的整数点的数目。
 * <p>
 * 示例 1：
 * 输入：nums = [[3,6],[1,5],[4,7]]
 * 输出：7
 * 解释：从 1 到 7 的所有点都至少与一辆车相交，因此答案为 7 。
 * <p>
 * 示例 2：
 * 输入：nums = [[1,3],[5,8]]
 * 输出：7
 * 解释：1、2、3、5、6、7、8 共计 7 个点满足至少与一辆车相交，因此答案为 7 。
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * nums[i].length == 2
 * 1 <= starti<= endi<= 100
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/points-that-intersect-with-cars
 * @title: 与车相交的点
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "numberOfPoints", "in.txt");
        IoUtil.testUtil(Solution.class, "numberOfPoints1", "in.txt");
    }



    // 差分数组做法
    public int numberOfPoints(List<List<Integer>> nums) {

        int ans = 0;
        int max = 102;
        int[] diff = new int[max];
        int r = 0;
        for (List<Integer> num : nums) {
            int a = num.get(0),b = num.get(1);
            diff[a]++;
            diff[b+1]--;
            r = Math.max(r,b);
        }

        int pre = 0;
        for (int i = 0; i <= r; i++) {
            pre += diff[i];
            if(pre > 0) {
                ans++;
            }
        }
        return ans;
    }


    // 区间做法
    public int numberOfPoints1(List<List<Integer>> nums) {

        int ans = 0;

        nums.sort((a, b) -> Objects.equals(a.get(0), b.get(0)) ? a.get(1) - b.get(1) : a.get(0) - b.get(0));


        int st = -1;
        int ed = -1;
        for (List<Integer> num : nums) {
            int a = num.get(0), b = num.get(1);
            // 第一次遇到
            if (st == -1 && ed == -1) {
                st = a;
                ed = b;
            } else if (a > ed) {
                // 区间不连续 更新区间同时 统计答案
                ans += ed - st + 1;
                st = a;
                ed = b;
            } else {
                // 区间重叠 更新右端点
                ed = Math.max(ed,b);
            }
        }

        ans += ed - st + 1;
        return ans;
    }



}