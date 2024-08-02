package leetcode._0x3f_.slide_window.slide_0011;

import code_generation.utils.IoUtil;


/**
 * 2134. 最少交换次数来组合所有的 1 II
 * <p>
 * 交换 定义为选中一个数组中的两个 互不相同 的位置并交换二者的值。环形 数组是一个数组，可以认为 第一个 元素和 最后一个 元素 相邻 。给你一个 二进制环形 数组 nums ，返回在 任意位置 将数组中的所有 1 聚集在一起需要的最少交换次数。
 * <p>
 * 示例 1：输入：nums = [0,1,0,1,1,0,0]输出：1解释：这里列出一些能够将所有 1 聚集在一起的方案：[0,0,1,1,1,0,0] 交换 1 次。[0,1,1,1,0,0,0] 交换 1 次。[1,1,0,0,0,0,1] 交换 2 次（利用数组的环形特性）。无法在交换 0 次的情况下将数组中的所有 1 聚集在一起。因此，需要的最少交换次数为 1 。
 * <p>
 * 示例 2：输入：nums = [0,1,1,1,0,0,1,1,0]输出：2解释：这里列出一些能够将所有 1 聚集在一起的方案：[1,1,1,0,0,0,0,1,1] 交换 2 次（利用数组的环形特性）。[1,1,1,1,1,0,0,0,0] 交换 2 次。无法在交换 0 次或 1 次的情况下将数组中的所有 1 聚集在一起。因此，需要的最少交换次数为 2 。
 * <p>
 * 示例 3：输入：nums = [1,1,0,0,1]输出：0解释：得益于数组的环形特性，所有的 1 已经聚集在一起。因此，需要的最少交换次数为 0 。
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= nums.length <= 105
 * nums[i] 为 0 或者 1
 *
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/minimum-swaps-to-group-all-1s-together-ii
 * @title: minimum-swaps-to-group-all-1s-together-ii
 */
public class Solution {

    public static void main(String[] args) {
        IoUtil.testUtil(Solution.class, "minSwaps", "in.txt");
    }


    public int minSwaps(int[] nums) {
        int cnt = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) cnt++;
        }
        int ans = cnt;
        int k = cnt,need = 0;
        for (int i = 0; i <= 2*n; i++) {
            if(i>=k && nums[(i-k)%n]==0) need--;

            if(nums[i%n]==0) need++;

            if(i>=k-1){
                ans = Math.min(ans,need);
            }

            if(ans == 0) break;
        }
        return ans;
    }


}