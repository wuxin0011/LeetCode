package leetcode.contest.weekly.w_400.w_441;

import code_generation.utils.IoUtil;
import code_generation.annotation.TestCaseGroup;
import java.util.*;
/**
 * @author: agitated-curranfnd
 * @Description:
 * @url:   <a href="https://leetcode.cn/contest/weekly-contest-441/problems/maximum-unique-subarray-sum-after-deletion">删除后的最大子数组元素和</a>
 * @title: 删除后的最大子数组元素和
 */
//@TestCaseGroup(start = 1,end = 0x3fff,use = true)
public class A {

    public static void main(String[] args) {
        IoUtil.testUtil(A.class,"maxSum","A.txt");
    }
     

    public int maxSum(int[] a) {
        int n = a.length;
        int inf = 0x3ffffff;
        int ans = -inf;
        for(int i = 0;i < n;i++) {
            ans = Math.max(ans,a[i]);
        }
        if(ans < 0) return ans;
        Set<Integer> set = new HashSet<>();
        for(int i = 0;i < n;i++) {
            if(a[i] > 0) {
                set.add(a[i]);
            }
        }
        int s = 0;
        for(int x : set) {
            s += x;
        }
        return Math.max(ans,s);
	}

  

}