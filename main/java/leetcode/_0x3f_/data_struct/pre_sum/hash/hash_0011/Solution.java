package leetcode._0x3f_.data_struct.pre_sum.hash.hash_0011;

import code_generation.utils.IoUtil;

/**
 * 1124. 表现良好的最长时间段
 * <p>
 * 给你一份工作时间表hours，上面记录着某一位员工每天的工作小时数。
 * 我们认为当员工一天中的工作小时数大于8 小时的时候，那么这一天就是「劳累的一天」。
 * 所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。
 * 请你返回「表现良好时间段」的最大长度。
 * <p>
 * 示例 1：
 * 输入：hours = [9,9,6,0,6,6,9]
 * 输出：3
 * 解释：最长的表现良好时间段是 [9,9,6]。
 * <p>
 * 示例 2：
 * 输入：hours = [6,6,6]
 * 输出：0
 * <p>
 * 提示：
 * 1 <= hours.length <= 10^4
 * 0 <= hours[i] <= 16
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/longest-well-performing-interval
 * @title: 表现良好的最长时间段
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "longestWPI", "in.txt");
    }


    public int longestWPI(int[] hours) {
        int n = hours.length;
        int ans = 0;
//        map.put(-1,-1);
        int x = 0;
        int l = 0;
        for (int i = 0; i < n; i++) {
            x += (hours[i] >= 8 ? 1 : -1);
            if (x > 0) {
                ans = Math.max(i - l + 1, ans);
            } else {
                l = i;
                x = 0;
            }
        }
        return ans;
    }


}