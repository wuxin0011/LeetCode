package leetcode._0x3f_.data_struct.pre_sum.hash.hash_0012;

import code_generation.utils.IoUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 2488. 统计中位数为 K 的子数组
 * <p>
 * 给你一个长度为 n 的数组 nums ，该数组由从 1 到 n 的 不同 整数组成。
 * 另给你一个正整数 k 。
 * 统计并返回 nums 中的 中位数 等于 k 的非空子数组的数目。
 * 注意：
 * 数组的中位数是按 递增 顺序排列后位于 中间 的那个元素，如果数组长度为偶数，则中位数是位于中间靠 左 的那个元素。
 * <p>
 * 例如，[2,3,1,4] 的中位数是 2 ，[8,4,3,5,1] 的中位数是 4 。
 * <p>
 * <p>
 * 子数组是数组中的一个连续部分。
 * <p>
 * 示例 1：
 * 输入：nums = [3,2,1,4,5], k = 4
 * 输出：3
 * 解释：中位数等于 4 的子数组有：[4]、[4,5] 和 [1,4,5] 。
 * <p>
 * 示例 2：
 * 输入：nums = [2,3,1], k = 3
 * 输出：1
 * 解释：[3] 是唯一一个中位数等于 3 的子数组。
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 10^5
 * 1 <= nums[i], k <= n
 * nums 中的整数互不相同
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/count-subarrays-with-median-k
 * @title: count-subarrays-with-median-k
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "countSubarrays", "in.txt");
    }


    // k 为中位数
    // 奇数
    // 小于K的个数 = 大于K的个数
    // => 左边小于 + 右边小于 = 左边大于 + 右边大于
    // => 左边小于 - 左边大于 = 右边大于 - 右边小于
    // 定义： 1      -1        1        -1

    // 偶数 [2,3,4,5] => k = 3
    // 小于k的个数 + 1 = 大于个数
    // 总的个数 = 奇数 + 偶数


    public int countSubarrays(int[] a, int k) {
        int ans = 0;
        int pos = -1;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            if (a[i] == k) {
                pos = i;
                break;
            }
        }
        if (pos == -1) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int s = 0;
        map.put(0,1); // 单独也算一个 ！！！ 这是一种常用的技巧 ！
        for (int i = pos - 1; i >= 0; i--) {
            s += a[i] > k ? -1 : 1;
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        s = 0;
        ans += map.getOrDefault(0, 0) + map.getOrDefault(-1, 0);
        for (int i = pos + 1; i < n; i++) {
            s += a[i] > k ? 1 : -1;
            ans += map.getOrDefault(s, 0) + map.getOrDefault(s - 1, 0);
        }
        return ans;
    }


}