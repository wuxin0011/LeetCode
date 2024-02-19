package leetcode.contest.weekly.w_300.w_383.a;

import leetcode.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description: 边界上的蚂蚁
 */
public class A {
    public static void main(String[] args) {
        IoUtil.testUtil(A.class);
    }

    public int returnToBoundaryCount(int[] nums) {
        int tot = 0;
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
            if (sum == 0) {
                tot++;
            }
        }
        return tot;
    }
}
