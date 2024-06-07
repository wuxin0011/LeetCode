package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 *
 * 2831. 找出最长等值子数组
 *
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
 * 如果子数组中所有元素都相等，则认为子数组是一个 等值子数组 。注意，空数组是 等值子数组 。
 * 从 nums 中删除最多 k 个元素后，返回可能的最长等值子数组的长度。
 * 子数组 是数组中一个连续且可能为空的元素序列。
 *
 * 示例 1：
 * 输入：nums = [1,3,2,3,1,3], k = 3
 * 输出：3
 * 解释：最优的方案是删除下标 2 和下标 4 的元素。
 * 删除后，nums 等于 [1, 3, 3, 3] 。
 * 最长等值子数组从 i = 1 开始到 j = 3 结束，长度等于 3 。
 * 可以证明无法创建更长的等值子数组。
 *
 * 示例 2：
 * 输入：nums = [1,1,2,2,1,1], k = 2
 * 输出：4
 * 解释：最优的方案是删除下标 2 和下标 3 的元素。
 * 删除后，nums 等于 [1, 1, 1, 1] 。
 * 数组自身就是等值子数组，长度等于 4 。
 * 可以证明无法创建更长的等值子数组。
 *
 * 提示：
 * 	1 <= nums.length <= 10^5
 * 	1 <= nums[i] <= nums.length
 * 	0 <= k <= nums.length
 *
 * @author: wuxin0011
 * @Description: hash + 滑动窗口
 * @url: https://leetcode.cn/problems/find-the-longest-equal-subarray
 * @title: 找出最长等值子数组
 */
public class Code_0071_2831 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0071_2831.class, "longestEqualSubarray", "txt_file\\Code_0071_2831.txt");
    }


    public int longestEqualSubarray(List<Integer> nums, int k) {

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < nums.size(); i++) {
            int val = nums.get(i);
            List<Integer> ids = map.get(val);
            if (ids == null) {
                ids = new ArrayList<>();
                ids.add(i);
                map.put(val, ids);
            } else {
                ids.add(i);
            }
        }

        int ans = 0;

        for (Map.Entry<Integer, List<Integer>> item : map.entrySet()) {
            List<Integer> ids = item.getValue();
            // 这个减枝非常重要
            if (ans > ids.size()) {
                continue;
            }
            // 滑动窗口
            for (int l = 0, r = 0, del = 0; r < ids.size(); r++) {

                // 统计删除
                // ids.get(r) - ids.get(r - 1) 是两个相邻索引间隔多少
                // -1 是因为 如果 索引为 4 、 3 那么 是连续 间隔就是 0 无需删除 因此需要 - 1
                if (r > 0) {
                    del += (ids.get(r) - ids.get(r - 1) - 1);
                }

                while (del > k) {
                    if (l + 1 <= r) {
                        del -= (ids.get(l + 1) - ids.get(l) - 1);
                    }
                    l++;
                }
                ans = Math.max(ans,r - l + 1);
            }
        }
        return ans;
    }


}