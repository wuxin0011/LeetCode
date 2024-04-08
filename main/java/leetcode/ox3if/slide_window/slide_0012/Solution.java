package leetcode.ox3if.slide_window.slide_0012;

import code_generation.utils.IoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 2653. 滑动子数组的美丽值
 * 给你一个长度为 n的整数数组nums，请你求出每个长度为k的子数组的 美丽值。一个子数组的 美丽值定义为：如果子数组中第 x小整数是 负数，那么美丽值为第 x小的数，否则美丽值为 0。请你返回一个包含n - k + 1个整数的数组，依次表示数组中从第一个下标开始，每个长度为k的子数组的美丽值。子数组指的是数组中一段连续 非空的元素序列。
 * 示例 1：
 * 输入：nums = [1,-1,-3,-2,3], k = 3, x = 2
 * 输出：[-1,-2,-2]
 * 解释：总共有 3 个 k = 3 的子数组。第一个子数组是 [1, -1, -3] ，第二小的数是负数 -1 。第二个子数组是 [-1, -3, -2] ，第二小的数是负数 -2 。第三个子数组是 [-3, -2, 3]，第二小的数是负数 -2 。
 * 示例 2：
 * 输入：nums = [-1,-2,-3,-4,-5], k = 2, x = 2
 * 输出：[-1,-2,-3,-4]
 * 解释：总共有 4 个 k = 2 的子数组。[-1, -2] 中第二小的数是负数 -1 。[-2, -3] 中第二小的数是负数 -2 。[-3, -4] 中第二小的数是负数 -3 。[-4, -5] 中第二小的数是负数 -4 。
 * * 示例 3：
 * 输入：nums = [-3,1,2,-3,0,-3], k = 2, x = 1
 * 输出：[-3,0,-3,-3,-3]
 * 解释：总共有 5 个 k = 2 的子数组。[-3, 1] 中最小的数是负数 -3 。[1, 2] 中最小的数不是负数，所以美丽值为 0 。[2, -3] 中最小的数是负数 -3 。[-3, 0] 中最小的数是负数 -3 。[0, -3] 中最小的数是负数 -3 。
 * 提示：
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= k <= n
 * 1 <= x <= k
 * -50<= nums[i] <= 50
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/sliding-subarray-beauty
 * @title: sliding-subarray-beauty
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "getSubarrayBeauty", "in.txt");
    }


    public int[] getSubarrayBeauty(int[] nums, int k, int x) {


        int n = nums.length;
        int[] ans = new int[n - k + 1];
        if (x > k) {
            return ans;
        }
        int[] map = new int[101];
        int min = -50;

        for (int i = 0; i < n; i++) {
            if (i >= k) {
                map[nums[i - k] - min]--;
            }
            map[nums[i] - min]++;

            if (i >= k - 1) {

                int t = x;
                int val = 0;
                for (int j = 0; j < map.length; j++) {
                    val = j + min;
                    if (map[j] == 0) {
                        continue;
                    }
                    if(t<=map[j]){
                        break;
                    }else{
                        t -= map[j];
                    }
                }

                int out = i - k + 1;
                ans[out] = Math.min(val, 0);
            }
        }

        return ans;
    }


}