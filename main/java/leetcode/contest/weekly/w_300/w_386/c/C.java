package leetcode.contest.weekly.w_300.w_386.c;

import leetcode.utils.IoUtil;

import java.util.Arrays;

/**
 * @author: wuxin0011
 * @Description:
 * @url https://leetcode.cn/problems/earliest-second-to-mark-indices-i/
 */
public class C {
    public static void main(String[] args) {
        IoUtil.testUtil(C.class, "earliestSecondToMarkIndices","in.txt");
    }


    // 请你返回范围 [1, m] 中的一个整数，表示最优操作下，标记 nums 中 所有 下标的 最早秒数 ，如果无法标记所有下标，返回 -1
    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        int n = nums.length;
        int m = changeIndices.length;
        if (n > m) {
            return -1;
        }

        int[] lastT = new int[n];
        int left = n - 1, right = m + 1;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (check(nums, changeIndices, lastT, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right > m ? -1 : right;
    }

    private boolean check(int[] nums, int[] changeIndices, int[] lastT, int mx) {
        Arrays.fill(lastT, -1);
        for (int t = 0; t < mx; t++) {
            lastT[changeIndices[t] - 1] = t;
        }
        for (int t : lastT) {
            if (t < 0) { // 有课程没有考试时间
                return false;
            }
        }

        int cnt = 0;
        for (int i = 0; i < mx; i++) {
            int idx = changeIndices[i] - 1;
            if (i == lastT[idx]) { // 考试
                if (nums[idx] > cnt) { // 没时间复习
                    return false;
                }
                cnt -= nums[idx]; // 复习这门课程
            } else {
                cnt++; // 留着后面用
            }
        }
        return true;
    }
}
