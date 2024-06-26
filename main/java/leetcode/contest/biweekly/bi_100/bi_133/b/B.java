package leetcode.contest.biweekly.bi_100.bi_133.b;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-133/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-i
 * @title: 使二进制数组全部等于 1 的最少操作次数 I
 */
public class B {

    public static void main(String[] args) {
        IoUtil.testUtil(B.class, "minOperations", "B.txt");
    }


    public int minOperations(int[] nums) {
        int c = 0;
        for (int num : nums) {
            if (num == 0) {
                c++;
            }
        }
        if (c == 0) {
            return 0;
        }
        int n = nums.length;
        int cnt = 0;
        for (int i = 0; i < n - 2; i++) {
            if (nums[i] == 1) {
                continue;
            }
            cnt++;
            for (int j = 0; j < 3; j++) {
                if (nums[i + j] == 0) {
                    nums[i + j] = 1;
                    c--;
                } else {
                    nums[i + j] = 0;
                    c++;
                }
            }
            if (c == 0) {
                break;
            }
        }
//        System.out.println(Arrays.toString(nums));
        return c == 0 ? cnt : -1;
    }


}