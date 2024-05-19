package leetcode.contest.weekly.w_300.w_398.a;

import code_generation.utils.IoUtil;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/weekly-contest-398/problems/special-array-i
 * @title: 特殊数组 I
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"isArraySpecial","A.txt");
    }
     

    public boolean isArraySpecial(int[] nums) {

        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] % 2 == nums[i] % 2) {
                return false;
            }
        }

        return true;
    }

  

}