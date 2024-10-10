package leetcode.contest.weekly.w_400.w_418.a;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-418/problems/maximum-possible-number-by-binary-concatenation
 * @title: 连接二进制表示可形成的最大数值
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "maxGoodNumber", "A.txt");
    }


    public int maxGoodNumber(int[] nums) {
        int ans = 0;
        ans = Math.max(ans, calc(nums[0], nums[1], nums[2]));
        ans = Math.max(ans, calc(nums[0], nums[2], nums[1]));
        ans = Math.max(ans, calc(nums[1], nums[0], nums[2]));
        ans = Math.max(ans, calc(nums[1], nums[2], nums[0]));
        ans = Math.max(ans, calc(nums[2], nums[1], nums[0]));
        ans = Math.max(ans, calc(nums[2], nums[0], nums[1]));
        return ans;
    }

    static int calc(int a, int b, int c) {
        String s = Integer.toBinaryString(a) + Integer.toBinaryString(b) + Integer.toBinaryString(c);
        int ans = 0;
        for (int i = s.length() - 1, x = 0; i >= 0; i--) {
            x = 1 << (s.length() - i - 1);
            if (s.charAt(i) == '1') {
                ans += x;
            }
        }
        return ans;
    }


}