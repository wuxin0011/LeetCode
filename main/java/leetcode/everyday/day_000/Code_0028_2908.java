package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;
/**
 * @author: wuxin0011
 * @Description:
 * @url: https://leetcode.cn/problems/minimum-sum-of-mountain-triplets-i
 * @title: minimum-sum-of-mountain-triplets-i
 */
public class Code_0028_2908 {

    public static void main(String[] args) {
        IoUtil.testUtil(Code_0028_2908.class, "minimumSum", "txt_file\\Code_0028_2908.txt");
    }


    public int minimumSum(int[] nums) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[j] > nums[i] && nums[j] > nums[k] && (nums[i] + nums[j] + nums[k]) < ans) {
                        ans = nums[i] + nums[j] + nums[k];
                    }
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans ;
    }

}