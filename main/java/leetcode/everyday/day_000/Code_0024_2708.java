package leetcode.everyday.day_000;

import code_generation.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 * @url
 * @title
 */
public class Code_0024_2708 {
    public static void main(String[] args) {
        IoUtil.testUtil(Code_0024_2708.class, IoUtil.DEFAULT_METHOD_NAME, "txt_file\\Code_0024_2708.txt");
    }

    public long maxStrength(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        int tot = 0;
        int zero = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0) {
                tot++;
            }
            if (nums[i] == 0) {
                zero++;
            }
        }
        // System.out.println("zero = " + zero + ",tot = " + tot);
        if (zero + tot == n && tot <= 1) {
            return nums[n - 1];
        }

        long ans = 1;
        for (int i = 0; i < n; i++) {
            if (tot % 2 == 1 && i == tot - 1) {
                continue;
            }
            if (nums[i] == 0) {
                continue;
            }
            ans *= nums[i];
        }
        return ans;
    }
}
