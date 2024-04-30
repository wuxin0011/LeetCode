package leetcode.contest.weekly.w_300.w_395.a;

import code_generation.utils.IoUtil;
import java.util.*;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/weekly-contest-395/problems/find-the-integer-added-to-array-i
 * @title: 找出与数组相加的整数I
 */
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"addedInteger","A.txt");
    }
     

    public int addedInteger(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        return  nums2[0] - nums1[0];
	}

  

}