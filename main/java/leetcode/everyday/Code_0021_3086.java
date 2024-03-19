package leetcode.everyday;

import code_generation.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description: 双指针模拟 ， 题目规定了中心点，就说基本说明了使用双指针
 * 如何左右移动呢 ？ 由于题目是找[l,r] 区间最小值*width，如果保持最小值不移动，应该往较大方向移动，宽度增加，ans 会变大
 * @url https://leetcode.cn/problems/maximum-score-of-a-good-subarray/description/
 * @title
 */
public class Code_0021_3086 {
    public static void main(String[] args) {
        IoUtil.testUtil(Code_0021_3086.class, IoUtil.DEFAULT_METHOD_NAME, "txt_file\\Code_0021_3086.txt");
    }


    public int maximumScore(int[] nums, int k) {
        int ans = nums[k];
        int n = nums.length;
        for (int l = k, mi = nums[k], r = k; l > 0 || r < n - 1; ) {
            if (r == n - 1 || l > 0 && nums[l - 1] > nums[r + 1]) { // left > right , left--
                l--;
                mi = Math.min(mi, nums[l]);
            } else {
                r++;
                mi = Math.min(mi, nums[r]);
            }
            ans = Math.max(ans, (r - l + 1) * mi);
        }
        return ans;
    }
}
