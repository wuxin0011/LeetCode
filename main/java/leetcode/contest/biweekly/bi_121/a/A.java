package leetcode.contest.biweekly.bi_121.a;

import leetcode.utils.IoUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: wuxin0011
 * @Description:
 * @url https://leetcode.cn/problems/smallest-missing-integer-greater-than-sequential-prefix-sum/description/
 */
public class A {
    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "missingInteger", "in.txt");
    }

    public int missingInteger(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        int tot = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1] + 1) {
                break;
            }
            tot += nums[i];
        }
        while (set.contains(tot)) tot++;

        return tot;
    }
}
