package leetcode.contest.weekly.w_300.w_399.a;

import code_generation.utils.IoUtil;

/**
 * @author: agitated-curranfnd
 * @Description:
 * @url: https://leetcode.cn/contest/weekly-contest-399/problems/find-the-number-of-good-pairs-i
 * @title: 优质数对的总数 I
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class, "numberOfPairs", "A.txt");
    }


    public int numberOfPairs(int[] nums1, int[] nums2, int k) {
        int cnt = 0;
        for (int n1 : nums1) {
            for (int n2 : nums2) {
                if (k == 0 || n2 == 0) {
                    cnt++;
                    continue;
                }
                if (n1 % (n2 * k) == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }


}