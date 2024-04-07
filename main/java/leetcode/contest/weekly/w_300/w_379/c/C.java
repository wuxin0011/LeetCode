package leetcode.contest.weekly.w_300.w_379.c;

import code_generation.utils.IoUtil;

import java.util.HashSet;
import java.util.Set;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   https://leetcode.cn/contest/weekly-contest-379/problems/maximum-size-of-a-set-after-removals
 * @title: 移除后集合的最多元素数
 */
public class C {

    public static void main(String[] args) {
        IoUtil.testUtil(C.class,"maximumSetSize","C.txt");
    }


    public int maximumSetSize(int[] nums1, int[] nums2) {

        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }

        int common = 0;
        for(int v : set1){
            if(set2.contains(v)) common++;
        }

        return 0;
    }

  

}