package leetcode.contest.biweekly.bi_100.bi_124.a;

import leetcode.utils.IoUtil;

/**
 * @author: wuxin0011
 * @Description: 相同分数的最大操作数目 I
 */
public class A {
    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"maxOperations");
    }

    public int maxOperations(int[] nums) {
        int cnt = 0;
        int pre = -1;
        for (int i = 0; i < nums.length; i += 2) {
            if (pre == -1) {
                pre = nums[i] + nums[i + 1];
                cnt++;
                continue;
            }
            if (i + 1 < nums.length && nums[i] + nums[i + 1] == pre) {
                cnt++;
                continue;
            }
            break;
        }
        return cnt;
    }
}
