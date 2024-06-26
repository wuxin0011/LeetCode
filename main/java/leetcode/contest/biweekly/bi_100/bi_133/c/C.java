package leetcode.contest.biweekly.bi_100.bi_133.c;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/biweekly-contest-133/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-ii
 * @title: 使二进制数组全部等于 1 的最少操作次数 II
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "minOperations", "C.txt");
    }


    public int minOperations(int[] nums) {
        int cnt = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                continue;
            }
            cnt++;
            while (i < n && nums[i] == 0) {
                i++;
            }
            if (i < n && nums[i] == 1) {
                cnt++;
            }

        }
        return cnt;
    }


}